package com.goach.mvvm.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goach.base.BaseFragment;
import com.goach.mvvm.R;
import com.goach.mvvm.databinding.FragmentMeBinding;
import com.goach.mvvm.vm.widget.TitleBarViewModel;


/**
 * author: Goach.zhong
 * Date: 2020-05-04 9:53
 * Des:我的
 **/
public class MeFragment extends BaseFragment<FragmentMeBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {
        super.initData();
        TitleBarViewModel mTitleBarViewModel = mViewModelProviderUtils.getFragmentVMProvider(this).get(TitleBarViewModel.class);
        mViewDataBinding.setVm(mTitleBarViewModel);
        mTitleBarViewModel.backVisible.set(View.GONE);
        mTitleBarViewModel.pageTitle.set("我的");
    }
}
