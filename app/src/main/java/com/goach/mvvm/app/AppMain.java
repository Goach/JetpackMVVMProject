package com.goach.mvvm.app;

import android.app.Application;

import com.goach.base.BaseAppVMManager;

/**
 * author: Goach.zhong
 * Date: 2020-04-21 10:51
 * Des:Application
 **/
public class AppMain extends Application {
    public static AppBusModel appBusModel;
    private static Application app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        appBusModel = BaseAppVMManager.getInstance().attachApp(this)
                .getAppVMProvider().get(AppBusModel.class);

    }

    public static Application getApp() {
        return app;
    }
}
