package com.goach.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

/**
 * author: Goach.zhong
 * Date: 2020-04-23 11:28
 * Des:RecyclerView item 多样式的 BaseAdapter
 **/
public abstract class BaseMultiRecyclerAdapter<B extends ViewDataBinding,D>{
    public BaseRecyclerAdapter<B,D> mRealAdapter;
    protected abstract boolean isForViewType(Object data);
    protected abstract int getLayoutId();
    private View getLayoutView(){
        return null;
    }

    /**
     * 到这里，就可以创建每个样式自己的ViewHolder
     */
    BaseRecyclerAdapter.BaseRecyclerViewHolder<B> onCreateViewHolder(BaseRecyclerAdapter<B,D> parentAdapter,
                                                                     @NonNull ViewGroup parent, int viewType) {
        mRealAdapter = parentAdapter;
        if(getLayoutId() > 0 ){
            return new BaseRecyclerAdapter.BaseRecyclerViewHolder<>(LayoutInflater.from(parent.getContext())
                    .inflate(getLayoutId(),parent,false));
        }
        if(getLayoutView() == null){
            throw new IllegalArgumentException("You need to Override getLayoutId() or getLayoutView()");
        }
        return new BaseRecyclerAdapter.BaseRecyclerViewHolder<>(getLayoutView());
    }
    public void onBindViewHolder(BaseRecyclerAdapter<B,D> parentAdapter,
                                 BaseRecyclerAdapter.BaseRecyclerViewHolder<B> holder,
                                 int position) {
    }
}
