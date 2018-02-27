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

    /**
     * 当完全具有焦点时设置为true，避免存在在onResume还没有执行完时，添加监听，造成onStop后续的生命周期被回调
     */
    private boolean isVisibleToUser = true;

    @Override
    public void addListener(ActivityLifecycleListener listener) {
        if (lifecycleListeners.contains(listener)) {
            return;
        }
        lifecycleListeners.add(listener);
        if (isCreated) {
            listener.onCreate();
        }
        if (!isCreated) {
            listener.onDestroy();
        }
        if (isStarted) {
            listener.onStart();
        }
        if (!isVisibleToUser) {
            if (!isStarted) {
                listener.onStop();
            }
            if (isResumed) {
                listener.onResume();
            }
            if (!isResumed) {
                listener.onPause();
            }
        }
    }

    @Override
    public void removeListener(ActivityLifecycleListener listener) {
        if (lifecycleListeners.size() > 0 && lifecycleListeners.contains(listener)) {
            lifecycleListeners.remove(listener);
        }
    }

    @Override
    public void removeAllListener() {
        if (lifecycleListeners.size() > 0) {
            lifecycleListeners.clear();
        }
    }

    @Override
    public boolean containListener(ActivityLifecycleListener listener) {
        if (lifecycleListeners.size() <= 0) {
            return false;
        }
        return lifecycleListeners.contains(listener);
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
        isVisibleToUser = true;
        for (ActivityLifecycleListener listener : lifecycleListeners) {
            listener.onResume();
        }
    }

    public void onPause() {
        isResumed = false;
        isVisibleToUser = false;
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
