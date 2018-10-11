package com.arafeh.base.view.fragments.mainreport

import android.widget.ArrayAdapter
import com.arafeh.base.data.om.Report
import com.arafeh.base.internal.core.FragmentLifecycle
import com.arafeh.base.view.adapters.ReportAdapter
import kotlinx.android.synthetic.main.fragment_main_report.*

/**
 * Created by Arafeh on 7/16/2018.
 */
class AdapterInit(private var fragment: MainReportsFragment) : FragmentLifecycle(fragment) {
    override fun onPostCreate() {
        fragment.reports.adapter = ReportAdapter(fragment.activity!!, fragment.presenter.reports, {})
        fragment.presenter.reports.add(Report(1, 34.434575, 35.836018, 0.0f, "samira", 1))
        fragment.presenter.reports.add(Report(1, 34.436062, 35.831126, 0.15f, "samira", 1))
        fragment.presenter.reports.add(Report(1, 34.434575, 35.836018, 0.35f, "samira", 2))
        fragment.presenter.reports.add(Report(1, 34.445919, 35.821761, 0.65f, "samira", 2))
        fragment.presenter.reports.add(Report(1, 34.445919, 35.821761, 0.78f, "samira", 2))
        fragment.presenter.reports.add(Report(1, 34.445919, 35.821761, 1f, "samira", 2))
        (fragment.reports.adapter as ArrayAdapter<*>).notifyDataSetChanged()
    }
}
