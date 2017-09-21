package com.task.trackerapp;

import android.app.Application;
import android.util.Log;

import com.task.tracker.Tracker;


public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
		Tracker.init(this);
    }
}
