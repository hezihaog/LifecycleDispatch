package com.hzh.lifecycle.dispatch.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.Fragment;

import com.hzh.lifecycle.dispatch.lifecycle.FragmentLifecycle;

/**
 * Created by Hezihao on 2017/7/10.
 */

public class LifecycleFragment extends Fragment {
    private FragmentLifecycle lifecycle;

    public LifecycleFragment() {
        this(new FragmentLifecycle());
    }

    @SuppressLint("ValidFragment")
    public LifecycleFragment(FragmentLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public FragmentLifecycle getLifecycle() {
        if (lifecycle == null) {
            lifecycle = new FragmentLifecycle();
        }
        return lifecycle;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (lifecycle != null) {
            lifecycle.onAttach();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (lifecycle != null) {
            lifecycle.onStart();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (lifecycle != null) {
            lifecycle.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (lifecycle != null) {
            lifecycle.onDestroy();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (lifecycle != null) {
            lifecycle.onDetach();
        }
    }
}
