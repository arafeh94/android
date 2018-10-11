package com.arafeh.base.view.dialogs

import android.os.Bundle
import android.view.View
import com.arafeh.base.R
import com.arafeh.base.data.enum.ReportType
import com.arafeh.base.internal.core.BaseDialogFragment
import com.arafeh.base.view.layouts.ReportActionButton

/**
 * Created by Arafeh on 7/15/2018.
 */
class ReportTypeDialog : BaseDialogFragment() {
    private lateinit var onReportRequest: (type: Int) -> Unit

    override fun layout(): Int? {
        return R.layout.dialog_report_type
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actions = view.findViewById<ReportActionButton>(R.id.actions)
        actions.btReportBarrier.setOnClickListener { dismiss(); onReportRequest(ReportType.BARRIER) }
        actions.btReportRadar.setOnClickListener { dismiss(); onReportRequest(ReportType.RADAR) }
    }


    fun setOnReportRequest(onReportRequest: (type: Int) -> Unit): ReportTypeDialog {
        this.onReportRequest = onReportRequest
        return this;
    }

}