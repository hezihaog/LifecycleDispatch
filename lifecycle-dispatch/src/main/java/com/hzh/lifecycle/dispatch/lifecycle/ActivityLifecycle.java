package com.hzh.lifecycle.dispatch.lifecycle;

import com.hzh.lifecycle.dispatch.listener.ActivityLifecycleListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Package: com.hzh.lifecycle.dispatch.lifecycle
 * FileName: ActivityLifecycle
 * Date: on 2017/11/9  上午11:15
 * Auther: zihe
 * Descirbe: Activity使用的Lifecycle
 * Email: hezihao@linghit.com
 */

public class ActivityLifecycle implements Lifecycle<ActivityLifecycleListener> {
    private final CopyOnWriteArrayList<ActivityLifecycleListener> lifecycleListeners = new CopyOnWriteArrayList<ActivityLifecycleListener>();
    private boolean isCreated;
    private boolean isStarted;
    private boolean isResumed;

    @Override
    public void addListener(ActivityLifecycleListener listener) {
        if (lifecycleListeners.contains(listener)) {
            return;
        }
        lifecycleListeners.add(listener);
        if (isCreated) {
            listener.onCreate();
        } else {
            listener.onDestroy();
        }
        if (isStarted) {
            listener.onStart();
        } else {
            listener.onStop();
        }
        if (isResumed) {
            listener.onResume();
        } else {
            listener.onPause();
        }
    }

    @Override
    public void removeListener(ActivityLifecycleListener listener) {

    }

    @Override
    public void removeAllListener() {

    }

    @Override
    public boolean containListener(ActivityLifecycleListener listener) {
        return false;
    }

    @Override
    public List<ActivityLifecycleListener> getAllListener() {
        return null;
    }

    public void onCreate() {
        isCreated = true;
        for (ActivityLifecycleListener listener : lifecycleListeners) {
            listener.onCreate();
        }
    }

    public void onStart() {
        isStarted = true;
        for (ActivityLifecycleListener listener : lifecycleListeners) {
            listener.onStart();
        }
    }


    public void onResume() {
        isResumed = true;
        for (ActivityLifecycleListener listener : lifecycleListeners) {
            listener.onResume();
        }
    }

    public void onPause() {
        isResumed = false;
        for (ActivityLifecycleListener listener : lifecycleListeners) {
            listener.onPause();
        }
    }

    public void onStop() {
        isStarted = false;
        for (ActivityLifecycleListener listener : lifecycleListeners) {
            listener.onStop();
        }
    }

    public void onDestroy() {
        isCreated = false;
        for (ActivityLifecycleListener listener : lifecycleListeners) {
            listener.onDestroy();
        }
    }
}
