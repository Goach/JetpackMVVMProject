package com.goach.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.goach.base.utils.ViewModelProviderUtils;

/**
 * author: Goach.zhong
 * Date: 2020-04-21 10:53
 * Des:BaseActivity
 **/
public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {
    public ViewModelProviderUtils mViewModelProviderUtils;
    protected B mViewDataBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getLayoutId() > 0 ){
            mViewDataBinding = DataBindingUtil.setContentView(this,getLayoutId());
        }else if(getLayoutView() != null) {
            View bindView = getLayoutView();
            setContentView(bindView);
            mViewDataBinding = DataBindingUtil.bind(bindView);
        }
        mViewModelProviderUtils = new ViewModelProviderUtils();
        initViewModel();
        initData();
    }
    protected abstract int getLayoutId();
    protected View getLayoutView(){
        return null;
    }
    protected void initViewModel(){
    }
    protected void initData(){
    }
}
