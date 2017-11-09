package com.hzh.lifecycle.dispatch.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.hzh.lifecycle.dispatch.lifecycle.ActivityLifecycle;

/**
 * Package: com.hzh.lifecycle.dispatch.base
 * FileName: LifecycleActivity
 * Date: on 2017/11/9  上午11:48
 * Auther: zihe
 * Descirbe: TODO
 * Email: hezihao@linghit.com
 */

public class LifecycleActivity extends FragmentActivity {
    private ActivityLifecycle lifecycle;

    public LifecycleActivity() {
        lifecycle = new ActivityLifecycle();
    }

    public ActivityLifecycle getLifecycle() {
        if (lifecycle == null) {
            lifecycle = new ActivityLifecycle();
        }
        return lifecycle;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (lifecycle != null) {
            lifecycle.onCreate();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (lifecycle != null) {
            lifecycle.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (lifecycle != null) {
            lifecycle.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (lifecycle != null) {
            lifecycle.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (lifecycle != null) {
            lifecycle.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (lifecycle != null) {
            lifecycle.onDestroy();
        }
    }
}
