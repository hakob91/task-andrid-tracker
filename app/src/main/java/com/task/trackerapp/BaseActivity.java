package com.task.trackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity
{
    abstract Class<?> nextActivityName();
    abstract Class<?> prevActivityName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        findViewById(R.id.next).setVisibility(
                nextActivityName() != null ? View.VISIBLE : View.GONE);
        findViewById(R.id.prev).setVisibility(
                prevActivityName() != null ? View.VISIBLE : View.GONE);
    }

    public void onNext(View view)
    {
        Intent i = new Intent(this, nextActivityName());
        startActivity(i);
    }

    public void onPrev(View view)
    {
        Intent i = new Intent(this, prevActivityName());
        startActivity(i);
    }
}
