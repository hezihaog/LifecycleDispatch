package com.hzh.lifecycle.dispatch.simple;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hzh.lifecycle.dispatch.BaseDelegateFragment;
import com.hzh.logger.L;

/**
 * Package: com.hzh.lifecycle.dispatch.simple
 * FileName: TestDelegateFragment
 * Date: on 2018/2/27  下午9:51
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public class TestDelegateFragment extends BaseDelegateFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        runTaskOnResume(new Runnable() {
            @Override
            public void run() {
                L.d("onResume");
            }
        });
        runTaskOnStart(new Runnable() {
            @Override
            public void run() {
                L.d("onStart");
            }
        });
        runTaskOnPause(new Runnable() {
            @Override
            public void run() {
                L.d("onPause");
            }
        });
    }
}