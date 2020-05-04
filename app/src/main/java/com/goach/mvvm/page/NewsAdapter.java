package com.goach.mvvm.page;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.goach.base.adapter.BaseRecyclerAdapter;
import com.goach.mvvm.R;
import com.goach.mvvm.databinding.NewsListItemBinding;

import java.util.List;

/**
 * author: Goach.zhong
 * Date: 2020-04-23 14:44
 * Des:列表单个样式的使用案例
 **/
public class NewsAdapter extends BaseRecyclerAdapter<NewsListItemBinding,Object> {
    public NewsAdapter(List<Object> mDataList) {
        super(mDataList);
    }
    @Override
    public int getLayoutId() {
        return R.layout.news_list_item;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder<NewsListItemBinding> holder, int position) {

    }
}
