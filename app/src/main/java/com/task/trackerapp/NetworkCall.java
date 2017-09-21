package com.task.trackerapp;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkCall extends AsyncTask<String, Void, Void>
{
    @Override
    protected Void doInBackground(String... strings)
    {

        HttpURLConnection urlConnection = null;
        try
        {
            if (strings.length > 0)
            {
                URL url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
        }


        return null;
    }
}
