package com.hzh.lifecycle.dispatch.simple.widget;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hzh.lifecycle.dispatch.listener.FragmentLifecycleListener;
import com.hzh.lifecycle.dispatch.simple.R;
import com.hzh.lifecycle.dispatch.simple.SecondActivity;
import com.hzh.lifecycle.dispatch.simple.fragment.HomeFragment;
import com.hzh.logger.L;

/**
 * Package: com.hzh.lifecycle.dispatch.simple.widget
 * FileName: LayoutViewHolder
 * Date: on 2017/11/8  下午10:23
 * Auther: zihe
 * Descirbe: Fragment中持有View的类，动态创建View，并外部调用getRoot获取View
 * Email: hezihao@linghit.com
 */

public class FragmentLayoutViewHolder {
    private Context context;
    private FrameLayout layout;

    public FragmentLayoutViewHolder(Context context) {
        this.context = context;
        layout = new FrameLayout(context);
        layout.setId(R.id.view_fragment_holder_layout);
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.mipmap.ic_launcher);
        FrameLayout.LayoutParams params = new FrameLayout
                .LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(imageView, params);
        getFragment().getLifecycle().addListener(new FragmentLifecycleListener() {
            @Override
            public void onAttach() {
                L.d("-start-----------------fragment-----------------start-");
                L.d("fragment lifecycle ::: onAttach");
            }

            @Override
            public void onStart() {
                L.d("fragment lifecycle ::: onStart");
            }

            @Override
            public void onResume() {
                L.d("fragment lifecycle ::: onResume");
            }

            @Override
            public void onPause() {
                L.d("fragment lifecycle ::: onPause");
            }

            @Override
            public void onStop() {
                L.d("fragment lifecycle ::: onStop");
            }

            @Override
            public void onDestroy() {
                L.d("fragment lifecycle ::: onDestroy");
            }

            @Override
            public void onDetach() {
                L.d("fragment lifecycle ::: onDetach");
                L.d("-end-----------------fragment-----------------end-");
            }
        });
    }

    public FrameLayout getRoot() {
        return layout;
    }

    private HomeFragment getFragment() {
        if (context instanceof SecondActivity) {
            SecondActivity activity = (SecondActivity) context;
            return (HomeFragment) activity.getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName());
        }
        return null;
    }
}
