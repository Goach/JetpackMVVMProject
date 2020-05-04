package com.goach.mvvm.fragment;

import android.view.View;

import com.goach.base.BaseFragment;
import com.goach.mvvm.R;
import com.goach.mvvm.databinding.FragmentHotBinding;
import com.goach.mvvm.vm.widget.TitleBarViewModel;


/**
 * author: Goach.zhong
 * Date: 2020-05-04 9:52
 * Des:热榜
 **/
public class HotFragment extends BaseFragment<FragmentHotBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initData() {
        super.initData();
        TitleBarViewModel mTitleBarViewModel = mViewModelProviderUtils.getFragmentVMProvider(this).get(TitleBarViewModel.class);
        mViewDataBinding.setVm(mTitleBarViewModel);
        mTitleBarViewModel.backVisible.set(View.GONE);
        mTitleBarViewModel.pageTitle.set("热榜");
    }
}
