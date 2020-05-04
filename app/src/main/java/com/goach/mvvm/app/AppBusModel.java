package com.goach.mvvm.app;

import androidx.lifecycle.MutableLiveData;
import com.goach.base.BaseAppBusModel;

/**
 * author: Goach.zhong
 * Date: 2020-04-26 14:37
 * Des:应用事件通知
 **/
public class AppBusModel extends BaseAppBusModel {
    public MutableLiveData<String> loginState = new MutableLiveData<>();

}
