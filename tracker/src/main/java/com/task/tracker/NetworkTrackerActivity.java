package com.task.tracker;

import android.app.Activity;
import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;

public class NetworkTrackerActivity extends Activity
{
    private final int REQUEST_CODE_VPN_SERVICE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent intent = VpnService.prepare(getApplicationContext());
        if (intent != null)
        {
            startActivityForResult(intent, REQUEST_CODE_VPN_SERVICE);
        }
        else
        {
            onActivityResult(REQUEST_CODE_VPN_SERVICE, RESULT_OK, null);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode  == RESULT_OK && requestCode == REQUEST_CODE_VPN_SERVICE)
        {
            Intent intent = new Intent(this, NetworkTrackerService.class);
            startService(intent);
        }

        finish();
    }

    public static void prepareVpnService(Activity activity)
    {
        Intent intent = VpnService.prepare(activity.getApplicationContext());
        if (intent != null)
        {
            activity.startActivity(new Intent(activity, NetworkTrackerActivity.class));
        }
        else
        {
            intent = new Intent(activity, NetworkTrackerService.class);
            activity.startService(intent);
        }
    }
}
