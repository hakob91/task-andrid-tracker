package com.task.trackerapp;

import android.os.Bundle;

public class ThirdActivity extends BaseActivity
{

    @Override
    Class<?> nextActivityName()
    {
        return null;
    }

    @Override
    Class<?> prevActivityName()
    {
        return SecondActivity.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
}
