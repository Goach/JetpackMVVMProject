package com.goach.base.binding;

import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.BindingAdapter;

/**
 * author: Goach.zhong
 * Date: 2020-04-27 13:45
 * Des:View 属性的 Adapter
 **/
public class WidgetBindingAdapter {
    @BindingAdapter("android:layout_width")
    public static void layoutWidth(View view,int width){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        view.setLayoutParams(params);
    }
    @BindingAdapter("android:layout_height")
    public static void layoutHeight(View view,int height){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
        view.setLayoutParams(params);
    }
    @BindingAdapter("android:visibility")
    public static void visibility(View view,@ViewVisible int visible){
        view.setVisibility(visible);
    }
}
