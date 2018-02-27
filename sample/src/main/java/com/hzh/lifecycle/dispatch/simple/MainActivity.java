package com.hzh.lifecycle.dispatch.simple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hzh.lifecycle.dispatch.DelegateFragmentFinder;
import com.hzh.lifecycle.dispatch.base.LifecycleActivity;
import com.hzh.lifecycle.dispatch.simple.widget.ActivityLayoutViewHolder;

public class MainActivity extends LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout rootLayout = findViewById(R.id.rootLayout);
        Button jumpBtn = findViewById(R.id.jumpSecond);
        //将View添加到Activity
        final ActivityLayoutViewHolder holder = new ActivityLayoutViewHolder(this);
        rootLayout.addView(holder.getRoot());
        jumpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        DelegateFragmentFinder.getInstance().startDelegate(this, TestDelegateFragment.class);
    }
}