package com.hzh.lifecycle.dispatch.simple.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hzh.lifecycle.dispatch.base.LifecycleFragment;
import com.hzh.lifecycle.dispatch.simple.R;
import com.hzh.lifecycle.dispatch.simple.widget.FragmentLayoutViewHolder;

/**
 * Package: com.hzh.lifecycle.dispatch.simple.fragment
 * FileName: HomeFragment
 * Date: on 2017/11/8  下午7:43
 * Auther: zihe
 * Descirbe: TODO
 * Email: hezihao@linghit.com
 */

public class HomeFragment extends LifecycleFragment {
    private ViewGroup rootView;
    private FragmentLayoutViewHolder holder;
    private boolean isAdd = false;
    private RelativeLayout.LayoutParams params;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        holder = new FragmentLayoutViewHolder(getActivity());
        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        final Button btnAddView = view.findViewById(R.id.addViewFragment);
        btnAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAdd) {
                    rootView.addView(holder.getLayout(), params);
                    btnAddView.setText("remove view - fragment");
                    isAdd = true;
                } else {
                    isAdd = false;
                    rootView.removeView(holder.getLayout());
                    btnAddView.setText("add view - fragment");
                }
            }
        });
        rootView.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View parent, View child) {
                holder.onAttachView();
            }

            @Override
            public void onChildViewRemoved(View parent, View child) {
                holder.onDetachView();
            }
        });
        btnAddView.performClick();
    }
}
