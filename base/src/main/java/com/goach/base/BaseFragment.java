package com.goach.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.goach.base.utils.ViewModelProviderUtils;

/**
 * author: Goach.zhong
 * Date: 2020-04-21 09:42
 * Des:Fragment基类
 **/
public abstract class BaseFragment<B extends ViewDataBinding> extends Fragment {
    public ViewModelProviderUtils mViewModelProviderUtils;
    public B mViewDataBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View bindView = null;
        if(getLayoutId() > 0 ){
            bindView = LayoutInflater.from(getContext()).inflate(getLayoutId(),container,false);
        }else if(getLayoutView() != null) {
            bindView = getLayoutView();
        }
        if(bindView != null){
            mViewDataBinding = DataBindingUtil.bind(bindView);
        }
        mViewModelProviderUtils = new ViewModelProviderUtils();
        initViewModel();
        initData();
        return bindView;
    }

    protected abstract int getLayoutId();
    protected View getLayoutView(){
        return null;
    }
    protected void initViewModel(){
    }
    protected void initData(){
    }

    public <T extends BaseAppBusModel> T getBusModel(Class<T> modelClass){
        return BaseAppVMManager.getInstance().getAppVMProvider().get(modelClass);
    }
}
