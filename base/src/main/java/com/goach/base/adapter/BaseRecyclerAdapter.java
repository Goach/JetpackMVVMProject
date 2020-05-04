package com.goach.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * author: Goach.zhong
 * Date: 2020-04-23 10:32
 * Des:RecyclerView 的 BaseAdapter
 **/
public abstract class BaseRecyclerAdapter<B extends ViewDataBinding,D> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseRecyclerViewHolder<B>> {
    public List<D> mDataList;
    public BaseRecyclerAdapter(List<D> mDataList) {
        this.mDataList = mDataList;
    }
    public abstract int getLayoutId();
    private View getLayoutView(){
        return null;
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder<B> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(getLayoutId() > 0 ){
            return new BaseRecyclerAdapter.BaseRecyclerViewHolder<>(LayoutInflater.from(parent.getContext())
                    .inflate(getLayoutId(),parent,false));
        }
        if(getLayoutView() == null){
            throw new IllegalArgumentException("You need to Override getLayoutId() or getLayoutView()");
        }
        return new BaseRecyclerAdapter.BaseRecyclerViewHolder<>(getLayoutView());
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public static final class BaseRecyclerViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder{
        public B mViewDataBinding;
        BaseRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mViewDataBinding = DataBindingUtil.bind(itemView);
        }
    }
}
