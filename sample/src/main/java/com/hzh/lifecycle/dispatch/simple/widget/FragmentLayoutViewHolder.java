package com.hzh.lifecycle.dispatch.simple.widget;

import android.content.Context;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hzh.lifecycle.dispatch.listener.FragmentLifecycleListener;
import com.hzh.lifecycle.dispatch.simple.MainActivity;
import com.hzh.lifecycle.dispatch.simple.R;
import com.hzh.lifecycle.dispatch.simple.fragment.HomeFragment;

/**
 * Package: com.hzh.lifecycle.dispatch.simple.widget
 * FileName: LayoutViewHolder
 * Date: on 2017/11/8  下午10:23
 * Auther: zihe
 * Descirbe: Fragment中持有View的类，动态创建View，并外部调用getLayout获取View
 * Email: hezihao@linghit.com
 */

public class FragmentLayoutViewHolder {
    private static final String TAG = FragmentLayoutViewHolder.class.getSimpleName();
    private Context context;
    private final FrameLayout layout;

    public FragmentLayoutViewHolder(Context context) {
        this.context = context;
        layout = new FrameLayout(context);
        layout.setId(R.id.view_fragment_holder_layout);
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.mipmap.ic_launcher);
        FrameLayout.LayoutParams params = new FrameLayout
                .LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(imageView, params);
    }

    private FragmentLifecycleListener lifecycleListener = new FragmentLifecycleListener() {
        @Override
        public void onAttach() {
            Log.i(TAG, "-start-----------------fragment-----------------start-");
            Log.i(TAG, "fragment lifecycle ::: onAttach");
        }

        @Override
        public void onStart() {
            Log.i(TAG, "fragment lifecycle ::: onStart");
        }

        @Override
        public void onStop() {
            Log.i(TAG, "fragment lifecycle ::: onStop");
        }

        @Override
        public void onDestroy() {
            Log.i(TAG, "fragment lifecycle ::: onDestroy");
        }

        @Override
        public void onDetach() {
            Log.i(TAG, "fragment lifecycle ::: onDetach");
            Log.i(TAG, "-end-----------------fragment-----------------end-");
        }
    };

    public FrameLayout getLayout() {
        return layout;
    }

    private HomeFragment getFragment() {
        if (context instanceof MainActivity) {
            MainActivity activity = (MainActivity) context;
            return (HomeFragment) activity.getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName());
        }
        return null;
    }

    public void onAttachView() {
        getFragment().getLifecycle().addListener(lifecycleListener);
    }

    public void onDetachView() {
        getFragment().getLifecycle().removeListener(lifecycleListener);
    }
}
