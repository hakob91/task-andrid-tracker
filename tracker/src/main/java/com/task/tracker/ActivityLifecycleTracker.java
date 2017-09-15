package com.task.tracker;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

class ActivityLifecycleTracker implements Application.ActivityLifecycleCallbacks
{
    private final Logger logger;
    private final TouchEventTracker callback;
    private boolean checkVpnService = true;

    ActivityLifecycleTracker(Logger logger)
    {
        this.logger = logger;
        callback = new TouchEventTracker(logger);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle)
    {
        Window window = activity.getWindow();
        callback.setAndroidCallback(window.getCallback());
        window.setCallback(callback);
        if (checkVpnService)
        {
            checkVpnService = false;
            NetworkTrackerActivity.prepareVpnService(activity);
        }
        logger.print("onActivityCreated: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStarted(Activity activity)
    {
        logger.print("onActivityStarted: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityResumed(Activity activity)
    {
        logger.print("onActivityResumed: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityPaused(Activity activity)
    {
        logger.print("onActivityPaused: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStopped(Activity activity)
    {
        logger.print("onActivityStopped: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle)
    {
        logger.print("onActivitySaveInstanceState: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityDestroyed(Activity activity)
    {
        logger.print("onActivityDestroyed: " + activity.getClass().getSimpleName());
    }
}
