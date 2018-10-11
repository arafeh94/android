package com.arafeh.base.view.activities.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.arafeh.base.App
import com.arafeh.base.R
import com.arafeh.base.data.om.Report
import com.arafeh.base.internal.core.ActivityLifecycle
import com.arafeh.base.view.activities.report.ReportsActivity
import com.arafeh.base.view.dialogs.TextDialog
import com.arafeh.base.view.fragments.mainreport.MainMapFragment
import com.arafeh.base.view.fragments.mainreport.MainReportsFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Arafeh on 7/16/2018.
 */
class WelcomeInit(private val mainActivity: MainActivity) : ActivityLifecycle(mainActivity) {
    override fun onPostCreate() {
        when (App.runCounter().count) {
            1 -> TextDialog().contentResId(R.string.welcome_message).show(mainActivity)
        }
    }
}

class PagerInit(var activity: MainActivity) : ActivityLifecycle(activity) {
    override fun onStart() {
        activity.pager.adapter = object : FragmentPagerAdapter(activity.supportFragmentManager) {
            var fragments = arrayOf(MainReportsFragment(), MainMapFragment())

            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getCount(): Int {
                return fragments.size
            }
        }
    }
}

class ActionBarInit(private var activity: MainActivity) : ActivityLifecycle(activity) {
    override fun onPostCreate() {
        val bar = activity.reportActionBar
        val presenter = activity.presenter

        bar.btReportRadar.setOnClickListener {
            App.location().location().oneFix().start {
                val report = Report(0, it.latitude, it.longitude, 0f, "", 1)
                presenter.submitReport(report)
            }
        }
        bar.btReportBarrier.setOnClickListener {
            App.location().location().oneFix().start {
                val report = Report(0, it.latitude, it.longitude, 0f, "", 2)
                presenter.submitReport(report)
            }
        }
        bar.btAdvancedReport.setOnClickListener {
            ReportsActivity.navigate(arrayListOf(), true)
        }
    }
}


