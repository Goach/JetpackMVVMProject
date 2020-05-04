package com.goach.base;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

/**
 * author: Goach.zhong
 * Date: 2020-04-21 10:06
 * Des:BaseAppVMManager,全局的 ViewModelProvider
 **/
public class BaseAppVMManager implements ViewModelStoreOwner {
    private static Application mApp;
    private static volatile BaseAppVMManager mInstance;
    private ViewModelStore mAppVMStore;
    private ViewModelProvider.Factory mAppVMFactory;
    private BaseAppVMManager(){

    }
    public static BaseAppVMManager getInstance(){
        if(null == mInstance){
            synchronized (BaseAppVMManager.class){
                if(null == mInstance){
                    mInstance = new BaseAppVMManager();
                }
            }
        }
        return mInstance;
    }
    public BaseAppVMManager attachApp(Application app){
        mApp = app;
        return this;
    }
    public Application getApp(){
        return mApp;
    }
    public ViewModelProvider getAppVMProvider(){
        return new ViewModelProvider(this, getAppVMFactory());
    }
    private ViewModelProvider.Factory getAppVMFactory() {
        Application application = checkApplication(mApp);
        if (mAppVMFactory == null) {
            mAppVMFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        }
        return mAppVMFactory;
    }
    private Application checkApplication(Application application) {
        if (application == null) {
            throw new IllegalStateException("Your Application is not yet attached. " +
                    "Please Call BaseAppVMManager.getInstance().attach(this) in the onCreate method of Application.");
        }
        return application;
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        if(mAppVMStore == null)  mAppVMStore = new ViewModelStore();
        return mAppVMStore;
    }
}
