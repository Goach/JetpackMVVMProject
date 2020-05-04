package com.goach.mvvm.vm;

import com.goach.mvvm.vm.widget.TitleBarViewModel;

import androidx.databinding.ObservableField;

/**
 * author: Goach.zhong
 * Date: 2020-04-21 11:01
 * Des:MainViewModel
 **/
public class MainViewModel extends TitleBarViewModel {
    public ObservableField<String> hello = new ObservableField<>();
}
