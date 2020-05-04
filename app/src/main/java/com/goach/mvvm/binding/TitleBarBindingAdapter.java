package com.goach.mvvm.binding;

import com.goach.mvvm.vm.widget.TitleBarViewModel;
import com.goach.mvvm.widget.TitleBar;
import androidx.databinding.BindingAdapter;

/**
 * author: Goach.zhong
 * Date: 2020-05-1 10:20
 * Des: TitleBar 绑定Vm属性
 */
public class TitleBarBindingAdapter {
    @BindingAdapter("bindVm")
    public static void bindVm(TitleBar view, TitleBarViewModel vm){
        view.setVm(vm);
    }
}
