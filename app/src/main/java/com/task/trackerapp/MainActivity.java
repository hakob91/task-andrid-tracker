package com.task.trackerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends BaseActivity
{

    @Override
    Class<?> nextActivityName()
    {
        return SecondActivity.class;
    }

    @Override
    Class<?> prevActivityName()
    {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
}
