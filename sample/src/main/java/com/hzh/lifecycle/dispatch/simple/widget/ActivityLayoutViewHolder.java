package com.hzh.lifecycle.dispatch.simple.widget;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hzh.lifecycle.dispatch.listener.ActivityLifecycleListener;
import com.hzh.lifecycle.dispatch.simple.MainActivity;
import com.hzh.lifecycle.dispatch.simple.R;
import com.hzh.logger.L;


/**
 * Package: com.hzh.lifecycle.dispatch.simple.widget
 * FileName: ActivityLayoutViewHolder
 * Date: on 2017/11/9  上午11:09
 * Auther: zihe
 * Descirbe: Activity中持有View的类，动态创建View，并外部调用getRoot获取View
 * Email: hezihao@linghit.com
 */

public class ActivityLayoutViewHolder {
    private Context context;
    private FrameLayout layout;

    public ActivityLayoutViewHolder(Context context) {
        this.context = context;
        layout = new FrameLayout(context);
        layout.setId(R.id.view_activity_holder_layout);
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.mipmap.ic_launcher_round);
        FrameLayout.LayoutParams params = new FrameLayout
                .LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(imageView, params);

        getActivity().getLifecycle().addListener(new ActivityLifecycleListener() {

            @Override
            public void onCreate() {
                L.d("-start-----------------activity-----------------start-");
                L.d("activity lifecycle ::: onCreate");
            }

            @Override
            public void onStart() {
                L.d("activity lifecycle ::: onStart");
            }

            @Override
            public void onResume() {
                L.d("activity lifecycle ::: onResume");
            }

            @Override
            public void onPause() {
                L.d("activity lifecycle ::: onPause");
            }

            @Override
            public void onStop() {
                L.d("activity lifecycle ::: onStop");
            }

            @Override
            public void onDestroy() {
                L.d("activity lifecycle ::: onDestroy");
                L.d("-end-----------------activity-----------------end-");
            }
        });
    }

    public FrameLayout getRoot() {
        return layout;
    }

    private MainActivity getActivity() {
        if (context instanceof MainActivity) {
            return (MainActivity) context;
        }
        return null;
    }
}
