package com.arafeh.base.view.layouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Button;

import com.arafeh.base.R;
import com.arafeh.base.internal.core.BaseLinearLayout;

import butterknife.BindView;

/**
 * Created by Arafeh on 7/14/2018.
 */

public class ReportActionButton extends BaseLinearLayout {
    private boolean advancedVisibility;

    @BindView(R.id.report_barrier)
    public Button btReportBarrier;

    @BindView(R.id.report_radar)
    public Button btReportRadar;

    @BindView(R.id.advanced_report)
    public Button btAdvancedReport;

    public ReportActionButton(Context context) {
        super(context);
    }

    public ReportActionButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ReportActionButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ReportActionButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initializeAttributes(TypedArray typedArray) {
        advancedVisibility = typedArray.getBoolean(R.styleable.ReportActionButton_advancedVisibility, true);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        if (!advancedVisibility) btAdvancedReport.setVisibility(GONE);
    }

    @Override
    public int[] styleable() {
        return R.styleable.ReportActionButton;
    }

    @Override
    public int layout() {
        return R.layout.layout_report_action_button;
    }
}
