package com.goach.mvvm.vm;

import android.view.View;

import com.goach.base.BaseEventHandler;

/**
 * author: Goach.zhong
 * Date: 2020-04-21 15:13
 * Des:MainActivity 的点击事件方法,接口形式好管理防止方法冲突
 **/
public interface MainViewEvent extends BaseEventHandler {
    void clickHello(View v);
}
