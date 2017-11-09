package com.hzh.lifecycle.dispatch.lifecycle;


import com.hzh.lifecycle.dispatch.listener.FragmentLifecycleListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Hezihao on 2017/7/10.
 * Fragment使用的Lifecycle
 */

public class FragmentLifecycle implements Lifecycle<FragmentLifecycleListener> {
    //读写分离，避免遍历的同时add进集合，抛出高并发异常。
    private final CopyOnWriteArrayList<FragmentLifecycleListener> lifecycleListeners = new CopyOnWriteArrayList<FragmentLifecycleListener>();
    private boolean isAttach;
    private boolean isStarted;
    private boolean isDestroyed;

    @Override
    public void addListener(FragmentLifecycleListener listener) {
        if (lifecycleListeners.contains(listener)) {
            return;
        }
        lifecycleListeners.add(listener);
        if (isAttach) {
            listener.onAttach();
        }
        if (!isAttach) {
            listener.onDetach();
        }
        if (isStarted) {
            listener.onStart();
        }
        if (!isStarted) {
            listener.onStop();
        }
        if (isDestroyed) {
            listener.onDestroy();
        }
    }

    @Override
    public void removeListener(FragmentLifecycleListener listener) {
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
    public boolean containListener(FragmentLifecycleListener listener) {
        if (lifecycleListeners.size() <= 0) {
            return false;
        }
        return lifecycleListeners.contains(listener);
    }

    @Override
    public List<FragmentLifecycleListener> getAllListener() {
        ArrayList<FragmentLifecycleListener> list = new ArrayList<FragmentLifecycleListener>();
        list.addAll(lifecycleListeners);
        return list;
    }

    public void onAttach() {
        isAttach = true;
        for (FragmentLifecycleListener listener : lifecycleListeners) {
            listener.onAttach();
        }
    }

    public void onStart() {
        isStarted = true;
        for (FragmentLifecycleListener listener : lifecycleListeners) {
            listener.onStart();
        }
    }

    public void onStop() {
        isStarted = false;
        for (FragmentLifecycleListener listener : lifecycleListeners) {
            listener.onStop();
        }
    }

    public void onDestroy() {
        isDestroyed = true;
        for (FragmentLifecycleListener listener : lifecycleListeners) {
            listener.onDestroy();
        }
    }

    public void onDetach() {
        isAttach = false;
        for (FragmentLifecycleListener listener : lifecycleListeners) {
            listener.onDetach();
        }
    }
}
