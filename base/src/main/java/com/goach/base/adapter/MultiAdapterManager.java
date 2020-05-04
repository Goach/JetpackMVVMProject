package com.goach.base.adapter;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.databinding.ViewDataBinding;

/**
 * author: Goach.zhong
 * Date: 2020-04-23 11:21
 * Des:多样式Adapter管理器
 **/
public final class MultiAdapterManager {
    private SparseArrayCompat<BaseMultiRecyclerAdapter> mMultiAdapterList = new SparseArrayCompat<>();
    public <B extends ViewDataBinding,D> void addMultiAdapter(BaseMultiRecyclerAdapter<B,D> adapter){
        mMultiAdapterList.put(viewTypeForAdapter(adapter),adapter);
    }
    public <B extends ViewDataBinding,D> void removeMultiAdapter(BaseMultiRecyclerAdapter<B,D> adapter){
        if(mMultiAdapterList.indexOfValue(adapter) >= 0){
            mMultiAdapterList.remove(viewTypeForAdapter(adapter));
        }
    }
    public void removeMultiAdapter(int viewType){
        if(mMultiAdapterList.indexOfKey(viewType) >= 0){
            mMultiAdapterList.remove(viewType);
        }
    }
    @SuppressWarnings("unchecked")
    <B extends ViewDataBinding,D> int getItemViewType(Object data){
        int size = mMultiAdapterList.size();
        for(int i = 0 ; i < size ; i++){
            BaseMultiRecyclerAdapter<B,D> adapter = mMultiAdapterList.valueAt(i);
            if(adapter.isForViewType(data)){
                return viewTypeForAdapter(adapter);
            }
        }
        throw new IllegalArgumentException("No BaseMultiRecyclerAdapter added that matches," +
                "You should addMultiAdapter it first");
    }
    private <B extends ViewDataBinding,D> int viewTypeForAdapter(BaseMultiRecyclerAdapter<B,D> adapter){
        return adapter.hashCode();
    }
    @SuppressWarnings("unchecked")
    @NonNull
    <B extends ViewDataBinding,D> BaseRecyclerAdapter.BaseRecyclerViewHolder<B> onCreateViewHolder(BaseRecyclerAdapter<B,D> parentAdapter, ViewGroup parent, int viewType) {
        BaseMultiRecyclerAdapter<B,D> baseAdapter = mMultiAdapterList.get(viewType);
        if(baseAdapter == null ){
            throw new IllegalArgumentException("No BaseMultiRecyclerAdapter added that matches," +
                    "You should addMultiAdapter it first");
        }
        return baseAdapter.onCreateViewHolder(parentAdapter,parent,viewType);
    }
    @SuppressWarnings("unchecked")
    <B extends ViewDataBinding,D> void onBindViewHolder(BaseRecyclerAdapter<B,D> parentAdapter, BaseRecyclerAdapter.BaseRecyclerViewHolder<B> holder, int position) {
        BaseMultiRecyclerAdapter<B,D> baseAdapter = mMultiAdapterList.get(holder.getItemViewType());
        if(baseAdapter != null){
            baseAdapter.onBindViewHolder(parentAdapter,holder,position);
        }
    }
}
