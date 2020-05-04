package com.goach.mvvm.page;

import com.goach.base.adapter.BaseMultiRecyclerAdapter;
import com.goach.base.adapter.BaseRecyclerAdapter;
import com.goach.mvvm.R;
import com.goach.mvvm.databinding.HomeListItemBinding;

/**
 * author: Goach.zhong
 * Date: 2020-04-23 15:04
 * Des:首页广告 Adapter
 **/
public class HomeAdAdapter extends BaseMultiRecyclerAdapter<HomeListItemBinding,String> {
    @Override
    protected boolean isForViewType(Object data) {
        return data instanceof String;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_list_item;
    }
    @Override
    public void onBindViewHolder(BaseRecyclerAdapter<HomeListItemBinding, String> parentAdapter, BaseRecyclerAdapter.BaseRecyclerViewHolder<HomeListItemBinding> holder, int position) {
        super.onBindViewHolder(parentAdapter, holder, position);
        holder.mViewDataBinding.executePendingBindings();
    }
}
