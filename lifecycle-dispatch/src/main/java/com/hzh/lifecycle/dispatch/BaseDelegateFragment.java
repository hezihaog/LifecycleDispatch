package com.hzh.lifecycle.dispatch;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import com.hzh.lifecycle.dispatch.adapter.SimpleFragmentLifecycleAdapter;
import com.hzh.lifecycle.dispatch.base.IDelegateFragment;
import com.hzh.lifecycle.dispatch.base.LifecycleFragment;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Package: com.hzh.lifecycle.dispatch
 * FileName: DelegateFragment
 * Date: on 2018/2/16  下午10:33
 * Auther: zihe
 * Descirbe:委托Fragment类
 * Email: hezihao@linghit.com
 */

public class BaseDelegateFragment extends LifecycleFragment implements IDelegateFragment {
    private ConcurrentHashMap<Integer, Runnable> callbacks = new ConcurrentHashMap<Integer, Runnable>();
    private Handler mMainHandler;

    public static BaseDelegateFragment newInstance() {
        return new BaseDelegateFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        detachAndPopAllTask();
    }

    @Override
    public void runTaskOnStart(final Runnable task) {
        runTaskInLifecycle(task, Status.START);
    }

    @Override
    public void runTaskOnResume(final Runnable task) {
        runTaskInLifecycle(task, Status.RESUME);
    }

    @Override
    public void runTaskOnPause(final Runnable task) {
        runTaskInLifecycle(task, Status.PAUSE);
    }

    @Override
    public void runTaskOnStop(final Runnable task) {
        runTaskInLifecycle(task, Status.STOP);
    }

    @Override
    public void runTaskOnDestroy(final Runnable task) {
        runTaskInLifecycle(task, Status.DESTROY);
    }

    @Override
    public void runTaskOnDetach(final Runnable task) {
        runTaskInLifecycle(task, Status.DETACH);
    }

    @Override
    public void runTaskInLifecycle(final Runnable task, final Status status) {
        callbacks.put(task.hashCode(), task);
        this.getLifecycle().addListener(new SimpleFragmentLifecycleAdapter() {
            @Override
            public void onAttach() {
                super.onAttach();
                if (status.ordinal() == Status.ATTACH.ordinal()) {
                    runNow();
                }
            }

            @Override
            public void onStart() {
                super.onStart();
                if (status.ordinal() == Status.START.ordinal()) {
                    runNow();
                }
            }

            @Override
            public void onResume() {
                super.onResume();
                if (status.ordinal() == Status.RESUME.ordinal()) {
                    runNow();
                }
            }

            @Override
            public void onPause() {
                super.onPause();
                if (status.ordinal() == Status.PAUSE.ordinal()) {
                    runNow();
                }
            }

            @Override
            public void onStop() {
                super.onStop();
                if (status.ordinal() == Status.STOP.ordinal()) {
                    runNow();
                }
            }

            @Override
            public void onDestroy() {
                super.onDestroy();
                if (status.ordinal() == Status.DESTROY.ordinal()) {
                    runNow();
                }
            }

            @Override
            public void onDetach() {
                super.onDetach();
                if (status.ordinal() == Status.DESTROY.ordinal()) {
                    runNow();
                }
            }

            /**
             * 马上执行，并且移除监听，就是说例如即使下一次的onResume再次回调，也不会重复执行
             */
            private void runNow() {
                callbacks.get(task.hashCode()).run();
                getLifecycle().removeListener(this);
            }
        });
    }

    /**
     * 移除所有callback
     */
    @Override
    public void popAllTask() {
        if (callbacks != null && callbacks.size() > 0) {
            callbacks.clear();
        }
    }

    /**
     * 获取主线程的Handler
     */
    public Handler getMainHandler() {
        if (mMainHandler == null) {
            mMainHandler = new Handler(Looper.getMainLooper());
        }
        return mMainHandler;
    }

    @Override
    public void detachAndPopAllTask() {
        popAllTask();
        getLifecycle().removeAllListener();
    }

    @Override
    public void runOnUiThread(Runnable action) {
        runOnUiThread(action, -1);
    }

    @Override
    public void runOnUiThread(Runnable action, long delayMillis) {
        if (delayMillis <= 0) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                mMainHandler.post(action);
            } else {
                action.run();
            }
        } else {
            mMainHandler.postDelayed(action, delayMillis);
        }
    }
}