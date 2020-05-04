package com.goach.mvvm.widget;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.goach.base.adapter.BaseMultiRecyclerAdapter;
import com.goach.base.adapter.BaseRecyclerAdapter;
import com.goach.base.adapter.MultiAdapterManager;
import com.goach.base.adapter.MultiRealRecyclerAdapter;
import com.goach.base.utils.DeviceUtil;
import com.goach.mvvm.R;
import com.goach.mvvm.app.AppMain;
import com.goach.mvvm.databinding.BottomTabAdBinding;
import com.goach.mvvm.databinding.BottomTabBinding;
import com.goach.mvvm.databinding.BottomTabRefreshBinding;
import com.goach.mvvm.vm.BottomViewEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author: Goach.zhong
 * Date: 2020-05-1 10:24
 * Des:MainViewModel
 **/
public class BottomTabLayout extends RecyclerView{
    public enum TAB_STATUS {
        REFRESH,
        AD,
        SELECTED,
        NORMAL,
    }
    private static final int MAX_NUM = 5;
    private List<Object> mDataList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private BottomTabAdapter mBottomTabAdapter;

    public BottomTabLayout(@NonNull Context context) {
        super(context);
        initView();
    }

    public BottomTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BottomTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LinearLayoutManager llLayoutManager = new LinearLayoutManager(getContext());
        llLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(llLayoutManager);
        MultiAdapterManager multiAdapterManager = new MultiAdapterManager();
        multiAdapterManager.addMultiAdapter(mBottomTabAdapter = new BottomTabAdapter());
        multiAdapterManager.addMultiAdapter(new BottomTabRefreshAdapter());
        multiAdapterManager.addMultiAdapter(new BottomTabAdAdapter());
        mAdapter = new MultiRealRecyclerAdapter(mDataList, multiAdapterManager);
        setAdapter(mAdapter);
    }

    public void setDataList(List<TabModel> list, BottomViewEvent bottomViewEvent) {
        mDataList.clear();
        mDataList.addAll(list);
        if(mBottomTabAdapter != null){
            mBottomTabAdapter.setBottomViewEvent(bottomViewEvent);
        }
        mAdapter.notifyDataSetChanged();
    }
    public void notifyRefreshTab(int pos,boolean isRefreshing) {
        if (pos < 0 || pos > mDataList.size()) {
            return;
        }
        for (int i = 0; i < mDataList.size(); i++) {
            TabModel tabModel = (TabModel) mDataList.get(i);
            if(pos == i){
                tabModel.setTabStatus(isRefreshing ? TAB_STATUS.REFRESH : TAB_STATUS.SELECTED);
            }else{
                tabModel.setTabStatus(TAB_STATUS.NORMAL);
            }
        }
        mAdapter.notifyDataSetChanged();
    }
    public void notifySelectTab(int pos) {
        if (pos < 0 || pos > mDataList.size()) {
            return;
        }
        for (int i = 0; i < mDataList.size(); i++) {
            TabModel tabModel = (TabModel) mDataList.get(i);
            if(pos == i){
                tabModel.setTabStatus(TAB_STATUS.SELECTED);
            }else{
                tabModel.setTabStatus(TAB_STATUS.NORMAL);
            }
        }
        mAdapter.notifyDataSetChanged();
    }
    public TabModel getTabModel(int pos){
        if (pos < 0 || pos > mDataList.size()) {
            return null;
        }
        return (TabModel) mDataList.get(pos);
    }

    private static final class BottomTabAdapter extends BaseMultiRecyclerAdapter<BottomTabBinding, TabModel> {
        private int mScreenWidth = DeviceUtil.getDeviceWidth();
        private BottomViewEvent mBottomViewEvent;

        @Override
        protected boolean isForViewType(Object data) {
            return data instanceof TabModel && (((TabModel) data).tabStatus == TAB_STATUS.NORMAL ||
                    ((TabModel) data).tabStatus == TAB_STATUS.SELECTED);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.bottom_tab;
        }

        public void setBottomViewEvent(BottomViewEvent mBottomViewEvent) {
            this.mBottomViewEvent = mBottomViewEvent;
        }

        @Override
        public void onBindViewHolder(BaseRecyclerAdapter<BottomTabBinding, TabModel> parentAdapter, BaseRecyclerAdapter.BaseRecyclerViewHolder<BottomTabBinding> holder, int position) {
            super.onBindViewHolder(parentAdapter, holder, position);
            TabModel tabModel = parentAdapter.mDataList.get(position);
            tabModel.position.set(position);
            holder.mViewDataBinding.setVm(tabModel);
            holder.mViewDataBinding.setEvent(mBottomViewEvent);
            holder.mViewDataBinding.executePendingBindings();
            holder.mViewDataBinding.tvTab.getLayoutParams().width = (int) (mScreenWidth * 1.0f / Math.min(MAX_NUM, parentAdapter.mDataList.size()));
            if (tabModel.getTabStatus() == TAB_STATUS.NORMAL) {
                holder.mViewDataBinding.ivTabIcon.setImageDrawable(tabModel.getNormalDrawable());
                holder.mViewDataBinding.tvTab.setTextColor(tabModel.getNormalTextColor());
            } else {
                holder.mViewDataBinding.ivTabIcon.setImageDrawable(tabModel.getSelectDrawable());
                holder.mViewDataBinding.tvTab.setTextColor(tabModel.getSelectTextColor());
            }
        }
    }

    private static final class BottomTabRefreshAdapter extends BaseMultiRecyclerAdapter<BottomTabRefreshBinding, TabModel> {
        private int mScreenWidth = DeviceUtil.getDeviceWidth();
        private Drawable refreshDrawable = new RefreshAnimDrawable(BitmapFactory.decodeResource(AppMain.getApp().getResources(),
                R.drawable.icon_refresh));

        @Override
        protected boolean isForViewType(Object data) {
            return data instanceof TabModel && ((TabModel) data).getTabStatus() == TAB_STATUS.REFRESH;
        }

        @Override
        protected int getLayoutId() {
            return R.layout.bottom_tab_refresh;
        }

        @Override
        public void onBindViewHolder(BaseRecyclerAdapter<BottomTabRefreshBinding, TabModel> parentAdapter, BaseRecyclerAdapter.BaseRecyclerViewHolder<BottomTabRefreshBinding> holder, int position) {
            super.onBindViewHolder(parentAdapter, holder, position);
            TabModel tabModel = parentAdapter.mDataList.get(position);
            holder.mViewDataBinding.setVm(tabModel);
            holder.mViewDataBinding.executePendingBindings();
            holder.mViewDataBinding.tvTab.getLayoutParams().width = (int) (mScreenWidth * 1.0f / Math.min(MAX_NUM, parentAdapter.mDataList.size()));
            holder.mViewDataBinding.tvTab.setTextColor(tabModel.getSelectTextColor());
            holder.mViewDataBinding.pbTab.setIndeterminateDrawable(refreshDrawable);
        }
    }

    private static final class BottomTabAdAdapter extends BaseMultiRecyclerAdapter<BottomTabAdBinding, TabModel> {
        private int mScreenWidth = DeviceUtil.getDeviceWidth();

        @Override
        protected boolean isForViewType(Object data) {
            return data instanceof TabModel && ((TabModel) data).getTabStatus() == TAB_STATUS.AD;
        }

        @Override
        protected int getLayoutId() {
            return R.layout.bottom_tab_ad;
        }

        @Override
        public void onBindViewHolder(BaseRecyclerAdapter<BottomTabAdBinding, TabModel> parentAdapter, BaseRecyclerAdapter.BaseRecyclerViewHolder<BottomTabAdBinding> holder, int position) {
            super.onBindViewHolder(parentAdapter, holder, position);
            TabModel tabModel = parentAdapter.mDataList.get(position);
            holder.mViewDataBinding.setVm(tabModel);
            holder.mViewDataBinding.executePendingBindings();
            holder.mViewDataBinding.getRoot().getLayoutParams().width = (int) (mScreenWidth * 1.0f / Math.min(MAX_NUM, parentAdapter.mDataList.size()));
        }
    }

    public static class TabModel implements Serializable {
        private TAB_STATUS tabStatus = TAB_STATUS.NORMAL;
        private Drawable normalDrawable;
        private Drawable selectDrawable;
        public ObservableField<CharSequence> tabTitle = new ObservableField<>();
        private int normalTextColor;
        private int selectTextColor;
        private Drawable adDrawable;
        private int messageNum;
        public ObservableField<Integer> position = new ObservableField<>();

        public TAB_STATUS getTabStatus() {
            return tabStatus;
        }

        public void setTabStatus(TAB_STATUS tabStatus) {
            this.tabStatus = tabStatus;
        }

        public Drawable getNormalDrawable() {
            return normalDrawable;
        }

        public void setNormalDrawable(Drawable normalDrawable) {
            this.normalDrawable = normalDrawable;
        }

        public Drawable getSelectDrawable() {
            return selectDrawable;
        }

        public void setSelectDrawable(Drawable selectDrawable) {
            this.selectDrawable = selectDrawable;
        }

        public int getNormalTextColor() {
            return normalTextColor;
        }

        public void setNormalTextColor(int normalTextColor) {
            this.normalTextColor = normalTextColor;
        }

        public int getSelectTextColor() {
            return selectTextColor;
        }

        public void setSelectTextColor(int selectTextColor) {
            this.selectTextColor = selectTextColor;
        }

        public Drawable getAdDrawable() {
            return adDrawable;
        }

        public void setAdDrawable(Drawable adDrawable) {
            this.adDrawable = adDrawable;
        }

        public int getMessageNum() {
            return messageNum;
        }

        public void setMessageNum(int messageNum) {
            this.messageNum = messageNum;
        }
    }
}
