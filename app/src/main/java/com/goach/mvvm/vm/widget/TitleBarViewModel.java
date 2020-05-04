package com.goach.mvvm.vm.widget;

import com.goach.base.BaseViewModel;
import com.goach.base.binding.VisibleObservableField;

import androidx.databinding.ObservableField;

/**
 * author: Goach.zhong
 * Date: 2020-04-27 11:04
 * Des:头部 VM
 **/
public class TitleBarViewModel extends BaseViewModel {
    public ObservableField<Integer> statusBarHeight = new ObservableField<>();
    public VisibleObservableField backVisible = new VisibleObservableField();
    public ObservableField<String> pageTitle = new ObservableField<>();
    private ObservableField<String> endBtnTxt = new ObservableField<>();
}

