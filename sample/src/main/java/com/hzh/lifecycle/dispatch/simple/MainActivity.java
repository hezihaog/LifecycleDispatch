package com.hzh.lifecycle.dispatch.simple;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hzh.lifecycle.dispatch.base.LifecycleActivity;
import com.hzh.lifecycle.dispatch.simple.fragment.HomeFragment;
import com.hzh.lifecycle.dispatch.simple.widget.ActivityLayoutViewHolder;

public class MainActivity extends LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewGroup rootLayout = findViewById(R.id.rootLayout);
        final Button btnAddView = findViewById(R.id.addViewActivity);
        Button btnToggleFragment = findViewById(R.id.toggleFragment);
        final ActivityLayoutViewHolder holder = new ActivityLayoutViewHolder(this);
        btnAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAdded = holder.isAddListened();
                if (isAdded) {
                    holder.removeListener();
                    btnAddView.setText("add view - MainActivity");
                    rootLayout.removeView(holder.getLayout());
                } else {
                    holder.addListener();
                    btnAddView.setText("remove view - MainActivity");
                    rootLayout.addView(holder.getLayout(), new RelativeLayout.LayoutParams
                            (RelativeLayout.LayoutParams.WRAP_CONTENT
                                    , RelativeLayout.LayoutParams.WRAP_CONTENT));
                }
            }
        });
        btnToggleFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFragment();
            }
        });
        btnAddView.performClick();
        btnToggleFragment.performClick();
    }

    /**
     * 添加或者移除fragment
     */
    private void toggleFragment() {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(HomeFragment.class.getSimpleName());
        if (fragment == null) {
            manager.beginTransaction()
                    .add(R.id.container, new HomeFragment(), HomeFragment.class.getSimpleName())
                    .commit();
        } else {
            manager.beginTransaction().remove(fragment).commit();
        }
    }
}