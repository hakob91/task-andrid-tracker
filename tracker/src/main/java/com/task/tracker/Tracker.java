package com.task.tracker;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public final class Tracker
{
    final static Logger logger = new Logger()
    {
        @Override
        public void print(String message)
        {
            Log.i("TrackerLog", message);
        }
    };

    public static void init(Context context)
    {
        Application app = (Application) context.getApplicationContext();

        final ActivityLifecycleTracker callback = new ActivityLifecycleTracker(logger);
        app.registerActivityLifecycleCallbacks(callback);

        logger.print("init");
    }
}
