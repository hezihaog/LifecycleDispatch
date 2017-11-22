package com.hzh.lifecycle.dispatch.simple.widget;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.hzh.lifecycle.dispatch.lifecycle.ActivityLifecycle;
import com.hzh.lifecycle.dispatch.listener.ActivityLifecycleListener;
import com.hzh.lifecycle.dispatch.simple.MainActivity;
import com.hzh.lifecycle.dispatch.simple.R;

/**
 * Package: com.hzh.lifecycle.dispatch.simple.widget
 * FileName: ActivityLayoutViewHolder
 * Date: on 2017/11/9  上午11:09
 * Auther: zihe
 * Descirbe: Activity中持有View的类，动态创建View，并外部调用getLayout获取View
 * Email: hezihao@linghit.com
 */

public class ActivityLayoutViewHolder {
    private Context context;
    private final FrameLayout layout;

    public ActivityLayoutViewHolder(Context context) {
        this.context = context;
        layout = new FrameLayout(context);
        layout.setId(R.id.view_activity_holder_layout);
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.mipmap.ic_launcher_round);
        FrameLayout.LayoutParams params = new FrameLayout
                .LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(imageView, params);
    }

    private ActivityLifecycleListener lifecycleListener = new ActivityLifecycleListener() {

        @Override
        public void onCreate() {
            LogUtils.d("-start-----------------activity-----------------start-");
            LogUtils.d("activity lifecycle ::: onCreate");
        }

        @Override
        public void onStart() {
            LogUtils.d("activity lifecycle ::: onStart");
        }

        @Override
        public void onResume() {
            LogUtils.d("activity lifecycle ::: onResume");
        }

        @Override
        public void onPause() {
            LogUtils.d("activity lifecycle ::: onPause");
        }

        @Override
        public void onStop() {
            LogUtils.d("activity lifecycle ::: onStop");
        }

        @Override
        public void onDestroy() {
            LogUtils.d("activity lifecycle ::: onDestroy");
            LogUtils.d("-end-----------------activity-----------------end-");
        }
    };

    public FrameLayout getLayout() {
        return layout;
    }

    private MainActivity getActivity() {
        if (context instanceof MainActivity) {
            return (MainActivity) context;
        }
        return null;
    }

    public void addListener() {
        ActivityLifecycle lifecycle = getActivity().getLifecycle();
        if (lifecycle.containListener(lifecycleListener)) {
            return;
        }
        lifecycle.addListener(lifecycleListener);
    }

    public void removeListener() {
        getActivity().getLifecycle().removeListener(lifecycleListener);
    }

    public boolean isAddListened() {
        return getActivity().getLifecycle().containListener(lifecycleListener);
    }
}
