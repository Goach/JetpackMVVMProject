package com.goach.base.adapter;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import java.util.List;

/**
 * author: Goach.zhong
 * Date: 2020-04-23 14:51
 * Des:RecyclerView 多样式 Adapter 最终由该类创建 RecyclerView.Adapter 给 RecyclerView
 * 这里通过 MultiAdapterManager 来进行派发相应的方法
 **/
public final class MultiRealRecyclerAdapter extends BaseRecyclerAdapter<ViewDataBinding,Object> {
    private MultiAdapterManager mAdapterManager;
    public MultiRealRecyclerAdapter(List<Object> mDataList, MultiAdapterManager adapterManager) {
        super(mDataList);
        this.mDataList = mDataList;
        if(adapterManager == null){
            throw new IllegalArgumentException("MultiAdapterManager cannot be null");
        }
        this.mAdapterManager = adapterManager;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mAdapterManager.getItemViewType(mDataList.get(position));
    }
    @NonNull
    @Override
    public BaseRecyclerViewHolder<ViewDataBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return mAdapterManager.onCreateViewHolder(this,parent,viewType);
    }
    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder<ViewDataBinding> holder, int position) {
        mAdapterManager.onBindViewHolder(this,holder,position);
    }
}
