package com.task.tracker;

import android.content.Intent;
import android.net.VpnService;
import android.os.ParcelFileDescriptor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;


public class NetworkTrackerService extends VpnService
{
    private final VpnService.Builder vpnBuilder = new Builder();
    private final Logger logger = Tracker.logger;
    private Thread thread;
    private ParcelFileDescriptor parcelFileDescriptor;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    parcelFileDescriptor = vpnBuilder
                            .setMtu(1500)
                            .setSession("NetworkTrackerService")
                            .addAddress("192.168.0.1", 24)
                            .addDnsServer("8.8.8.8")
                            .addRoute("0.0.0.0", 0)
                            .establish();

                    DatagramChannel tunnel = DatagramChannel.open();

                    protect(tunnel.socket());

                    tunnel.connect(new InetSocketAddress("127.0.0.1", 8087));
                    tunnel.configureBlocking(false);

                    ByteBuffer buffer = ByteBuffer.allocate(32768);

                    FileInputStream in = new FileInputStream(
                            parcelFileDescriptor.getFileDescriptor());
                    FileOutputStream out = new FileOutputStream(
                            parcelFileDescriptor.getFileDescriptor());

                    int length;
                    while (true)
                    {
                        length = in.read(buffer.array());
                        if (length > 0)
                        {
                            debugPacket(buffer);

                            buffer.limit(length);
                            tunnel.write(buffer);
                            buffer.clear();
                        }

                        length = tunnel.read(buffer);
                        if (length > 0)
                        {
                            logger.print("out: " + length);

                            out.write(buffer.array(), 0, length);
                            buffer.clear();
                        }

                        Thread.sleep(100);
                    }
                }
                catch (InterruptedException | IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    if (parcelFileDescriptor != null)
                    {
                        try
                        {
                            parcelFileDescriptor.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        parcelFileDescriptor = null;
                    }
                }
            }
        });
        thread.start();

        logger.print("NetworkTrackerService start");
        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        if (thread != null)
        {
            thread.interrupt();
        }
        super.onDestroy();
    }

    private void debugPacket(ByteBuffer packet)
    {
        int buffer = packet.get();
        int version;
        int headerLength;
        version = buffer >> 4;
        headerLength = buffer & 0x0F;
        headerLength *= 4;

        logger.print("IP Version:" + version);
        logger.print("Header Length:" + headerLength);

        buffer = packet.get();      //DSCP + EN
        buffer = packet.getChar();  //Total Length

        logger.print("Total Length:" + buffer);

        buffer = packet.getChar();  //Identification
        buffer = packet.getChar();  //Flags + Fragment Offset
        buffer = packet.get();      //Time to Live
        buffer = packet.get();      //Protocol

        logger.print("Protocol:" + buffer);

        String sourceIP = "";
        buffer = packet.get();  //Source IP 1st Octet
        sourceIP += buffer;
        sourceIP += ".";

        buffer = packet.get();  //Source IP 2nd Octet
        sourceIP += buffer;
        sourceIP += ".";

        buffer = packet.get();  //Source IP 3rd Octet
        sourceIP += buffer;
        sourceIP += ".";

        buffer = packet.get();  //Source IP 4th Octet
        sourceIP += buffer;

        logger.print("Source IP: " + sourceIP);

        String destIP = "";
        buffer = packet.get();  //Destination IP 1st Octet
        destIP += buffer;
        destIP += ".";

        buffer = packet.get();  //Destination IP 2nd Octet
        destIP += buffer;
        destIP += ".";

        buffer = packet.get();  //Destination IP 3rd Octet
        destIP += buffer;
        destIP += ".";

        buffer = packet.get();  //Destination IP 4th Octet
        destIP += buffer;

        logger.print("Destination IP: " + destIP);
    }
}
