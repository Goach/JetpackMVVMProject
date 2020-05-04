package com.goach.base.binding;

import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;

/**
 * author: Goach.zhong
 * Date: 2020-5-1 09:02
 * Des:View 的 visible
 **/
@IntDef({View.VISIBLE, INVISIBLE, GONE})
@Retention(RetentionPolicy.SOURCE)
public @interface ViewVisible {
}
