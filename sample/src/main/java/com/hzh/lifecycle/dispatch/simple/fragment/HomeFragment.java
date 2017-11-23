package com.hzh.lifecycle.dispatch.simple.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hzh.lifecycle.dispatch.base.LifecycleFragment;
import com.hzh.lifecycle.dispatch.simple.R;
import com.hzh.lifecycle.dispatch.simple.widget.FragmentLayoutViewHolder;

/**
 * Package: com.hzh.lifecycle.dispatch.simple.fragment
 * FileName: HomeFragment
 * Date: on 2017/11/8  下午7:43
 * Auther: zihe
 * Descirbe: 演示用的Fragment
 * Email: hezihao@linghit.com
 */

public class HomeFragment extends LifecycleFragment {
    private ViewGroup rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentLayoutViewHolder holder = new FragmentLayoutViewHolder(getActivity());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        rootView.addView(holder.getRoot(), params);
    }
}
