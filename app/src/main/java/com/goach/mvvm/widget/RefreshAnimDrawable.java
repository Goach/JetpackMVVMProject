package com.goach.mvvm.widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Goach All Rights Reserved
 * User: Goach
 * Email: goach0728@gmail.com
 * Des:ProgressBar无法控制加载速度，自定义Drawable实现
 */
public class RefreshAnimDrawable extends Drawable implements Animatable, Runnable {
    private boolean mRunning = false;
    private Paint mBitmapPaint;
    private Paint mBgPaint;
    private Bitmap mBitmap;
    private int degrees = 0;

    RefreshAnimDrawable(Bitmap bitmap) {
        this.mBitmap = bitmap;
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setColor(Color.RED);
        mBgPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
    }

    @Override
    public int getIntrinsicWidth() {
        return mBitmap.getWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return mBitmap.getHeight();
    }
    @Override
    public void draw(@NonNull Canvas canvas) {
        float radius = Math.max(getIntrinsicWidth(),getIntrinsicHeight()) * 1.0f / 2;
        canvas.drawCircle(radius,radius,radius,mBgPaint);
        canvas.save();
        canvas.rotate(degrees,radius,radius);
        canvas.drawBitmap(mBitmap,0,0,mBitmapPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mBitmapPaint.setAlpha(alpha);
        mBgPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mBitmapPaint.setColorFilter(colorFilter);
        mBgPaint.setColorFilter(colorFilter);
    }

    @Override
    public boolean setVisible(boolean visible, boolean restart) {
        boolean changed = super.setVisible(visible, restart);
        if (visible) {
            if (changed || restart) {
                nextFrame();
            }
        } else {
            unscheduleSelf(this);
        }
        return changed;
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void run() {
        invalidateSelf();
        nextFrame();
    }

    @Override
    public void start() {
        if (!mRunning) {
            mRunning = true;
            nextFrame();
        }
    }

    @Override
    public void stop() {
        unscheduleSelf(this);
        mRunning = false;
    }

    @Override
    public boolean isRunning() {
        return mRunning;
    }
    private void nextFrame() {
        degrees += 60;//一帧多大距离
        unscheduleSelf(this);
        scheduleSelf(this, SystemClock.uptimeMillis() + 60);//一帧多久
    }
}
