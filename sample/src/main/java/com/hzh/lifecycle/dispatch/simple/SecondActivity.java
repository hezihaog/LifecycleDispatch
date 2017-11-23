package com.hzh.lifecycle.dispatch.simple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.hzh.lifecycle.dispatch.simple.fragment.HomeFragment;

/**
 * Package: com.hzh.lifecycle.dispatch.simple
 * FileName: SecondActivity
 * Date: on 2017/11/23  上午11:25
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public class SecondActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(HomeFragment.class.getSimpleName());
        if (fragment == null) {
            manager.beginTransaction()
                    .add(R.id.container, new HomeFragment(), HomeFragment.class.getSimpleName())
                    .commit();
        }
    }
}
