package com.arafeh.base.view.fragments.mainreport


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arafeh.base.App
import com.arafeh.base.R
import com.arafeh.base.internal.core.BaseFragment
import com.arafeh.base.internal.core.FragmentLifecycle
import com.arafeh.base.view.activities.report.ReportsActivity
import kotlinx.android.synthetic.main.fragment_main_report.*
import java.util.ArrayList


class MainReportsFragment : BaseFragment() {
    val presenter: MainReportsPresenter by lazy {
        MainReportsPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_main_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.showAll.setOnClickListener { App.navigator().navigate(ReportsActivity::class.java) }
    }

    override fun lifecycles(): ArrayList<FragmentLifecycle> {
        return arrayListOf(AdapterInit(this))
    }

}
