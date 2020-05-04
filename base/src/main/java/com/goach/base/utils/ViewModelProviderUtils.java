package com.goach.base.utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

/**
 * author: Goach.zhong
 * Date: 2020-04-27 11:22
 * Des:获取各种ViewModelProvider
 **/
public class ViewModelProviderUtils {
    private ViewModelProvider mActivityVMProvider;
    private ViewModelProvider mFragmentVMProvider;
    /**
     * 获取 Activity ViewModelProvider
     */
    public ViewModelProvider getActivityVMProvider(AppCompatActivity owner) {
        if(mActivityVMProvider == null) mActivityVMProvider = new ViewModelProvider(owner);
        return mActivityVMProvider;
    }
    /**
     * 获取 Fragment ViewModelProvider
     */
    public ViewModelProvider getFragmentVMProvider(Fragment owner) {
        if(mFragmentVMProvider == null) mFragmentVMProvider = new ViewModelProvider(owner);
        return mFragmentVMProvider;
    }
}
