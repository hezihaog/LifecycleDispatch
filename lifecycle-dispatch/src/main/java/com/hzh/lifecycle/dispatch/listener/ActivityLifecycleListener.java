package com.hzh.lifecycle.dispatch.listener;

/**
 * Package: com.hzh.lifecycle.dispatch.listener
 * FileName: ActivityLifecycleListener
 * Date: on 2017/11/9  上午11:10
 * Auther: zihe
 * Descirbe: Activity的生命周期监听器
 * Email: hezihao@linghit.com
 */

public interface ActivityLifecycleListener extends LifecycleListener {
    /**
     * Activity onCreate对应方法
     */
    void onCreate();

    /**
     * Activity onStart对应方法
     */
    void onStart();

    /**
     * Activity onResume对应方法
     */
    void onResume();

    /**
     * Activity onPause对应方法
     */
    void onPause();

    /**
     * Activity onStop对应方法
     */
    void onStop();

    /**
     * Activity onDestroy对应方法
     */
    void onDestroy();
}
