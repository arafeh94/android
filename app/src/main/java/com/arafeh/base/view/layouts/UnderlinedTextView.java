package com.arafeh.base.view.layouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.arafeh.base.R;
import com.arafeh.base.internal.core.BaseLinearLayout;

import butterknife.BindView;

/**
 * Created by Arafeh on 7/14/2018.
 */

public class UnderlinedTextView extends BaseLinearLayout {
    @BindView(R.id.text)
    public TextView textView;

    private String text;

    public UnderlinedTextView(Context context) {
        super(context);
    }

    public UnderlinedTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UnderlinedTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UnderlinedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initializeAttributes(TypedArray typedArray) {
        super.initializeAttributes(typedArray);
        text = typedArray.getString(R.styleable.UnderlinedTextView_text);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        if (text != null) textView.setText(text);
    }

    @Override
    public int[] styleable() {
        return R.styleable.UnderlinedTextView;
    }

    @Override
    public int layout() {
        return R.layout.layout_underlined_textview;
    }
}
