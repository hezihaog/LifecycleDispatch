package com.hzh.lifecycle.dispatch.base;

/**
 * Package: com.hzh.lifecycle.dispatch.base
 * FileName: IDelegateFragment
 * Date: on 2018/2/16  下午10:53
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public interface IDelegateFragment {
    /**
     * 生命周期状态
     */
    enum Status {
        ATTACH(),
        START(),
        RESUME(),
        PAUSE(),
        STOP(),
        DESTROY(),
        DETACH()
    }

    /**
     * 添加一个任务，在onStart时执行
     */
    void runTaskOnStart(final Runnable task);

    void runTaskOnResume(final Runnable task);

    void runTaskOnPause(final Runnable task);

    void runTaskOnStop(final Runnable task);

    void runTaskOnDestroy(final Runnable task);

    void runTaskOnDetach(final Runnable task);

    /**
     * 添加一个任务在指定的生命周期中
     */
    void runTaskInLifecycle(final Runnable task, final Status status);

    /**
     * 弹出所有队列中的任务
     */
    void detachAndPopAllTask();

    /**
     * 弹出栈中的所有任务
     */
    void popAllTask();

    /**
     * 运行在主线程
     */
    void runOnUiThread(Runnable action);

    /**
     * 延迟一定毫秒的任务在主线程
     */
    void runOnUiThread(Runnable action, long delayMillis);
}