package com.goach.mvvm.fragment;

import android.view.View;

import com.goach.base.BaseFragment;
import com.goach.mvvm.R;
import com.goach.mvvm.databinding.FragmentPlayBinding;
import com.goach.mvvm.vm.widget.TitleBarViewModel;


/**
 * author: Goach.zhong
 * Date: 2020-05-04 9:53
 * Des:放映厅
 **/
public class PlayFragment extends BaseFragment<FragmentPlayBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_play;
    }

    @Override
    protected void initData() {
        super.initData();
        TitleBarViewModel mTitleBarViewModel = mViewModelProviderUtils.getFragmentVMProvider(this).get(TitleBarViewModel.class);
        mViewDataBinding.setVm(mTitleBarViewModel);
        mTitleBarViewModel.backVisible.set(View.GONE);
        mTitleBarViewModel.pageTitle.set("放映厅");
    }
}
