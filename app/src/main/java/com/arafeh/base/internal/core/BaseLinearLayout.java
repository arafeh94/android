package com.arafeh.base.internal.core;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import butterknife.ButterKnife;

/**
 * Created by Arafeh on 7/14/2018.
 */

public abstract class BaseLinearLayout extends LinearLayout {

    public BaseLinearLayout(Context context) {
        super(context);
        init(context, null);
    }

    public BaseLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BaseLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (styleable() != null && attrs != null)
            initializeAttributes(context.obtainStyledAttributes(attrs, styleable()));
        initializeView();
    }

    protected void initializeView() {
        LayoutInflater.from(getContext()).inflate(layout(), this);
        ButterKnife.bind(this);
    }

    protected void initializeAttributes(TypedArray typedArray) {

    }

    public int[] styleable() {
        return null;
    }

    public abstract int layout();
}
