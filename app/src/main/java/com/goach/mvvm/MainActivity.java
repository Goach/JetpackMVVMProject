package com.goach.mvvm;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.goach.base.BaseActivity;
import com.goach.mvvm.databinding.ActivityMainBinding;
import com.goach.mvvm.vm.BottomViewEvent;
import com.goach.mvvm.vm.MainViewModel;
import com.goach.mvvm.widget.BottomTabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

/**
 * author: Goach.zhong
 * Date: 2020-04-21 9:51
 * Des:主页
 **/
public class MainActivity extends BaseActivity<ActivityMainBinding> implements BottomViewEvent {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViewModel() {
        super.initViewModel();
        MainViewModel mMainViewModel = mViewModelProviderUtils.getActivityVMProvider(this).get(MainViewModel.class);
        mViewDataBinding.setVm(mMainViewModel);
        setTabData();
    }

    @Override
    public void initData() {
        super.initData();
    }

    private void setTabData() {
        List<BottomTabLayout.TabModel> mDataList = new ArrayList<>();
        BottomTabLayout.TabModel tabModel = new BottomTabLayout.TabModel();
        tabModel.setTabStatus(BottomTabLayout.TAB_STATUS.SELECTED);
        tabModel.setNormalDrawable(ContextCompat.getDrawable(this, R.drawable.icon_home));
        tabModel.setSelectDrawable(ContextCompat.getDrawable(this, R.drawable.icon_home_selected));
        tabModel.tabTitle.set("首页");
        tabModel.setNormalTextColor(ContextCompat.getColor(this, R.color.color_3b3b3b));
        tabModel.setSelectTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        mDataList.add(tabModel);
        BottomTabLayout.TabModel tabModel2 = new BottomTabLayout.TabModel();
        tabModel2.setTabStatus(BottomTabLayout.TAB_STATUS.NORMAL);
        tabModel2.setNormalDrawable(ContextCompat.getDrawable(this, R.drawable.icon_play));
        tabModel2.setSelectDrawable(ContextCompat.getDrawable(this, R.drawable.icon_play_selected));
        tabModel2.tabTitle.set("西瓜视频");
        tabModel2.setNormalTextColor(ContextCompat.getColor(this, R.color.color_3b3b3b));
        tabModel2.setSelectTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        mDataList.add(tabModel2);
        BottomTabLayout.TabModel tabModel3 = new BottomTabLayout.TabModel();
        tabModel3.setTabStatus(BottomTabLayout.TAB_STATUS.NORMAL);
        tabModel3.setNormalDrawable(ContextCompat.getDrawable(this, R.drawable.icon_search));
        tabModel3.setSelectDrawable(ContextCompat.getDrawable(this, R.drawable.icon_search_selected));
        tabModel3.tabTitle.set("热榜");
        tabModel3.setNormalTextColor(ContextCompat.getColor(this, R.color.color_3b3b3b));
        tabModel3.setSelectTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        mDataList.add(tabModel3);
        BottomTabLayout.TabModel tabModel4 = new BottomTabLayout.TabModel();
        tabModel4.setTabStatus(BottomTabLayout.TAB_STATUS.NORMAL);
        tabModel4.setNormalDrawable(ContextCompat.getDrawable(this, R.drawable.icon_movie));
        tabModel4.setSelectDrawable(ContextCompat.getDrawable(this, R.drawable.icon_movie_selected));
        tabModel4.tabTitle.set("放映厅");
        tabModel4.setNormalTextColor(ContextCompat.getColor(this, R.color.color_3b3b3b));
        tabModel4.setSelectTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        mDataList.add(tabModel4);
        BottomTabLayout.TabModel tabModel5 = new BottomTabLayout.TabModel();
        tabModel5.setTabStatus(BottomTabLayout.TAB_STATUS.NORMAL);
        tabModel5.setNormalDrawable(ContextCompat.getDrawable(this, R.drawable.icon_me));
        tabModel5.setSelectDrawable(ContextCompat.getDrawable(this, R.drawable.icon_me_selected));
        tabModel5.tabTitle.set("我的");
        tabModel5.setNormalTextColor(ContextCompat.getColor(this, R.color.color_3b3b3b));
        tabModel5.setSelectTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        mDataList.add(tabModel5);
        mViewDataBinding.btlTab.setDataList(mDataList, this);
    }

    @Override
    public void clickTab(final View view,final int pos) {
        final BottomTabLayout.TabModel clickTab = mViewDataBinding.btlTab.getTabModel(pos);
        if(clickTab != null && clickTab.getTabStatus() == BottomTabLayout.TAB_STATUS.SELECTED){
            mViewDataBinding.btlTab.notifyRefreshTab(pos,true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mViewDataBinding.btlTab.notifyRefreshTab(pos,false);
                }
            },2000);
            return;
        }
        mViewDataBinding.btlTab.notifySelectTab(pos);
        NavHostFragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        if (hostFragment == null) {
            return;
        }
        final NavController navController = hostFragment.getNavController();
        int redId = R.id.homeFragment;
        switch (pos) {
            case 1:
                redId = R.id.mediaFragment;
                break;
            case 2:
                redId = R.id.hotFragment;
                break;
            case 3:
                redId = R.id.playFragment;
                break;
            case 4:
                redId = R.id.meFragment;
                break;
        }
        onNavDestinationSelected(redId, navController);
        if(view instanceof ViewGroup && ((ViewGroup) view).getChildCount() > 0){
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f,0.7f,1.0f);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            float animValue = (float) valueAnimator.getAnimatedValue();
                            ((ViewGroup) view).getChildAt(0).setScaleX(animValue);
                            ((ViewGroup) view).getChildAt(0).setScaleY(animValue);
                        }
                    });
            valueAnimator.setDuration(200);
            valueAnimator.start();
        }
    }

    private void onNavDestinationSelected(int resId,
                                          @NonNull NavController navController) {
        NavOptions.Builder builder = new NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setEnterAnim(R.anim.nav_default_enter_anim)
                .setExitAnim(R.anim.nav_default_exit_anim)
                .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(R.anim.nav_default_pop_exit_anim);
        builder.setPopUpTo(findStartDestination(navController.getGraph()).getId(), false);
        NavOptions options = builder.build();
        try {
            navController.navigate(resId, null, options);
        } catch (IllegalArgumentException e) {
            Log.d("MainActivity", "navigate error" + e.getMessage());
        }
    }

    private NavDestination findStartDestination(@NonNull NavGraph graph) {
        NavDestination startDestination = graph;
        while (startDestination instanceof NavGraph) {
            NavGraph parent = (NavGraph) startDestination;
            startDestination = parent.findNode(parent.getStartDestination());
        }
        return startDestination;
    }
}
