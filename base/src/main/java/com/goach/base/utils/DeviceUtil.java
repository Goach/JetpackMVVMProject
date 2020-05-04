package com.goach.base.utils;

import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;

import com.goach.base.BaseAppVMManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * author: Goach.zhong
 * Date: 2020-04-27 11:07
 * Des:设备相关信息
 **/
public class DeviceUtil {
    public static int getStatusBarHeight() {
        int resourceId = BaseAppVMManager.getInstance().getApp().getResources().getIdentifier("status_bar_height", "dimen", "android");
        return BaseAppVMManager.getInstance().getApp().getResources().getDimensionPixelSize(resourceId);
    }
    public static int getDeviceWidth(){
        return BaseAppVMManager.getInstance().getApp().getResources().getDisplayMetrics().widthPixels;
    }
    public static int getDeviceHeight(){
        return BaseAppVMManager.getInstance().getApp().getResources().getDisplayMetrics().heightPixels;
    }
    public static int dpToPx(float dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,BaseAppVMManager.getInstance()
                .getApp().getResources().getDisplayMetrics());
    }
    public static int spToPx(float dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,dp,BaseAppVMManager.getInstance()
                .getApp().getResources().getDisplayMetrics());
    }
    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     * @param window 需要设置的窗口
     * @param dark 是否把状态栏字体及图标颜色设置为深色
     * @return  boolean 成功执行返回true
     *
     */
    public static boolean flyMeStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meiZuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meiZuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meiZuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meiZuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     * @param window 需要设置的窗口
     * @param dark 是否把状态栏字体及图标颜色设置为深色
     * @return  boolean 成功执行返回true
     *
     */
    public static boolean miUiStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field  field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if(dark){
                    extraFlagField.invoke(window,darkModeFlag,darkModeFlag);//状态栏透明且黑色字体
                }else{
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result=true;
            }catch (Exception e){

            }
        }
        return result;
    }
}
