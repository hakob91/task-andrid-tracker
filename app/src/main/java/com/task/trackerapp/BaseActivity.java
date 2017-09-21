package com.task.trackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity
{
    abstract Class<?> nextActivityName();

    abstract Class<?> prevActivityName();

    public static String[] urls = {
            "https://docs.unity3d.com/Manual/MaterialsAccessingViaScript.html",
            "http://www.utorrent.com/prodnews?uid=ue1-c13639b0dcc9445ab12a8e288f4ca64d&v=3.5.1.43916",
            "https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows&code=IIC",
            "https://firebase.google.com/docs/perf-mon/get-started-android#known_issues",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        findViewById(R.id.next).setVisibility(
                nextActivityName() != null ? View.VISIBLE : View.GONE);
        findViewById(R.id.prev).setVisibility(
                prevActivityName() != null ? View.VISIBLE : View.GONE);


        for (int i = 0; i < 4; i++)
        {
            new NetworkCall()
                    .execute(urls[i]);
        }
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
