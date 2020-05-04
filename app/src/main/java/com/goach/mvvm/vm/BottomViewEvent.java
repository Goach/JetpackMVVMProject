package com.goach.mvvm.vm;

import android.view.View;

import com.goach.base.BaseEventHandler;

/**
 * Goach All Rights Reserved
 * User: Goach
 * Email: goach0728@gmail.com
 * Des:底部按钮点击事件
 */
public interface BottomViewEvent extends BaseEventHandler {
    void clickTab(View view,int pos);
}
