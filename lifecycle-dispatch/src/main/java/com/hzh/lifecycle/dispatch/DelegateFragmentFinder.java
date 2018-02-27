package com.hzh.lifecycle.dispatch;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import java.lang.reflect.Constructor;

/**
 * Package: com.hzh.lifecycle.dispatch
 * FileName: DelegateFinder
 * Date: on 2018/2/17  下午3:42
 * Auther: zihe
 * Descirbe:代理查找者
 * Email: hezihao@linghit.com
 */

public class DelegateFragmentFinder implements Handler.Callback {
    private static final String TAG = DelegateFragmentFinder.class.getSimpleName();
    private static final String DELEGATE_FRAGMENT_TAG = "delegateFragmentTag";
    private static final int ID_REMOVE_FRAGMENT_MANAGER = 1;

    private final Handler handler;
    private final ArrayMap<FragmentManager, BaseDelegateFragment> pendingFragments = new ArrayMap<FragmentManager, BaseDelegateFragment>();

    private DelegateFragmentFinder() {
        handler = new Handler(Looper.getMainLooper(), this);
    }

    private static class Singleton {
        private static final DelegateFragmentFinder instance = new DelegateFragmentFinder();
    }

    public static DelegateFragmentFinder getInstance() {
        return Singleton.instance;
    }

    /**
     * 添加代理fragment
     */
    public BaseDelegateFragment startDelegate(FragmentActivity activity, Class<? extends BaseDelegateFragment> delegateClass) {
        if (activity != null && !activity.isFinishing()) {
            FragmentManager fm = activity.getSupportFragmentManager();
            BaseDelegateFragment current = (BaseDelegateFragment) fm.findFragmentByTag(DELEGATE_FRAGMENT_TAG);
            if (current == null) {
                //这里的缓存Fragment是由于fragment的事务机制，和Looper机制，
                // 当2次执行到该代码段时，beginTransaction可能还在执行完，findFragmentByTag时找不到的，
                // 这就有可能创建了多个frag的实例，所以加多一个缓存实例，当真正执行完beginTransaction，再将缓存移除。
                current = pendingFragments.get(fm);
                if (current == null) {
                    current = buildDelegate(delegateClass);
                    pendingFragments.put(fm, current);
                    fm.beginTransaction().add(current, DELEGATE_FRAGMENT_TAG).commitAllowingStateLoss();
                    handler.obtainMessage(ID_REMOVE_FRAGMENT_MANAGER, fm).sendToTarget();
                }
            }
            return current;
        }
        return null;
    }

    /**
     * 构造代理fragment
     *
     * @param delegateClass 代理fragment的class
     */
    private BaseDelegateFragment buildDelegate(Class<? extends BaseDelegateFragment> delegateClass) {
        if (delegateClass == null) {
            throw new NullPointerException("delegateClass must be not null");
        }
        BaseDelegateFragment delegateFragment = null;
        try {
            Constructor<? extends BaseDelegateFragment> constructor = delegateClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            delegateFragment = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return delegateFragment;
    }

    @Override
    public boolean handleMessage(Message message) {
        boolean handled = true;
        Object removed = null;
        Object key = null;
        switch (message.what) {
            case ID_REMOVE_FRAGMENT_MANAGER:
                FragmentManager fm = (FragmentManager) message.obj;
                key = fm;
                removed = pendingFragments.remove(fm);
                break;
            default:
                handled = false;
        }
        if (handled && removed == null) {
            Log.d(TAG, "移除permission delegate fragment 失败 ::: " + key);
        }
        return false;
    }
}