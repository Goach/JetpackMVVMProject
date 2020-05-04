package com.goach.mvvm.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.goach.base.utils.DeviceUtil;
import com.goach.base.utils.ViewModelProviderUtils;
import com.goach.mvvm.R;
import com.goach.mvvm.databinding.WidgetTitleBarBinding;
import com.goach.mvvm.vm.widget.TitleBarViewModel;

/**
 * author: Goach.zhong
 * Date: 2020-04-21 15:30
 * Des:头部控件
 **/
public class TitleBar extends ConstraintLayout {
    private WidgetTitleBarBinding mWidgetTitleBarBinding;
    public TitleBar(Context context) {
        super(context);
        initView();
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView(){
        mWidgetTitleBarBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.widget_title_bar,this,true);
        setBackgroundColor(Color.WHITE);
        setStatusBarColor(Color.TRANSPARENT);
        setStatusBarTextDark(true, new Runnable() {
            @Override
            public void run() {
                setStatusBarColor(Color.GRAY);
            }
        });
    }
    public void setVm(TitleBarViewModel viewModel){
        viewModel.statusBarHeight.set(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ?
                DeviceUtil.getStatusBarHeight() : 0);//基本可适配刘海屏高度
        mWidgetTitleBarBinding.setVm(viewModel);
    }
    private void setStatusBarColor(int color){
        Window window = ((AppCompatActivity)getContext()).getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        mWidgetTitleBarBinding.viewStatusBar.setBackgroundColor(color);
    }
    public void setStatusBarTextDark(boolean isDark){
        setStatusBarTextDark(isDark,null);
    }
    public void setStatusBarTextDark(boolean isDark,Runnable failureRunnable){
        Window window = ((AppCompatActivity)getContext()).getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    (isDark ? View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR : View.SYSTEM_UI_FLAG_LAYOUT_STABLE));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            if (DeviceUtil.flyMeStatusBarLightMode(window, isDark)) {
                return;
            }
            if(DeviceUtil.miUiStatusBarLightMode(window, isDark)){
                return;
            }
            if(failureRunnable != null){//4.4以上&&6.0以下&&非flyMe非miUi的情况做一些操作
                failureRunnable.run();
            }
        }
    }
}
