package com.task.trackerapp;

import android.os.Bundle;

public class SecondActivity extends BaseActivity
{

    @Override
    Class<?> nextActivityName()
    {
        return ThirdActivity.class;
    }

    @Override
    Class<?> prevActivityName()
    {
        return MainActivity.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
}
