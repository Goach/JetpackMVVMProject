package com.goach.base.binding;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;

/**
 * author: Goach.zhong
 * Date: 2020-5-1 09:12
 * Des:View 的 visible
 **/
public class VisibleObservableField extends ObservableField<Integer> {
    @Override
    public void set(@ViewVisible Integer value) {
        super.set(value);
    }
    @ViewVisible
    @Nullable
    @Override
    public Integer get() {
        return super.get();
    }
}
