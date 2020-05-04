package com.goach.mvvm.fragment;

import android.view.View;

import com.goach.base.BaseFragment;
import com.goach.mvvm.R;
import com.goach.mvvm.databinding.FragmentMediaBinding;
import com.goach.mvvm.vm.widget.TitleBarViewModel;


/**
 * author: Goach.zhong
 * Date: 2020-05-04 10:00
 * Des:西瓜视频
 **/
public class MediaFragment extends BaseFragment<FragmentMediaBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_media;
    }

    @Override
    protected void initData() {
        super.initData();
        TitleBarViewModel mTitleBarViewModel = mViewModelProviderUtils.getFragmentVMProvider(this).get(TitleBarViewModel.class);
        mViewDataBinding.setVm(mTitleBarViewModel);
        mTitleBarViewModel.backVisible.set(View.GONE);
        mTitleBarViewModel.pageTitle.set("西瓜视频");
    }
}
