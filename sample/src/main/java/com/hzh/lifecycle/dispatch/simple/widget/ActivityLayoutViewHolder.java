package com.hzh.lifecycle.dispatch.simple.widget;

import android.content.Context;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hzh.lifecycle.dispatch.lifecycle.ActivityLifecycle;
import com.hzh.lifecycle.dispatch.listener.ActivityLifecycleListener;
import com.hzh.lifecycle.dispatch.simple.MainActivity;
import com.hzh.lifecycle.dispatch.simple.R;

/**
 * Package: com.hzh.lifecycle.dispatch.simple.widget
 * FileName: ActivityLayoutViewHolder
 * Date: on 2017/11/9  上午11:09
 * Auther: zihe
 * Descirbe: TODO
 * Email: hezihao@linghit.com
 */

public class ActivityLayoutViewHolder {
    private static final String TAG = ActivityLayoutViewHolder.class.getSimpleName();
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
            Log.i(TAG, "-start-----------------activity-----------------start-");
            Log.i(TAG, "activity lifecycle ::: onCreate");
        }

        @Override
        public void onStart() {
            Log.i(TAG, "activity lifecycle ::: onStart");
        }

        @Override
        public void onResume() {
            Log.i(TAG, "activity lifecycle ::: onResume");
        }

        @Override
        public void onPause() {
            Log.i(TAG, "activity lifecycle ::: onPause");
        }

        @Override
        public void onStop() {
            Log.i(TAG, "activity lifecycle ::: onStop");
        }

        @Override
        public void onDestroy() {
            Log.i(TAG, "activity lifecycle ::: onDestroy");
            Log.i(TAG, "-end-----------------activity-----------------end-");
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
