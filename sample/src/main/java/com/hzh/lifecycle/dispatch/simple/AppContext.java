package com.hzh.lifecycle.dispatch.simple;

import android.app.Application;

import com.hzh.logger.L;


/**
 * Package: com.hzh.lifecycle.dispatch.simple
 * FileName: AppContext
 * Date: on 2017/11/22  下午10:27
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public class AppContext extends Application {
    private static boolean debugEnable = BuildConfig.DEBUG;
    private static AppContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        L.configAllowLog(debugEnable);
    }

    public static AppContext getInstance() {
        return instance;
    }
}
