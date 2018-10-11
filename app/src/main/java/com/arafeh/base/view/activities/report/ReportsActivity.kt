package com.arafeh.base.view.activities.report

import android.os.Bundle
import com.google.android.gms.maps.*
import com.arafeh.base.App
import com.arafeh.base.R
import com.arafeh.base.core.ReportMarker
import com.arafeh.base.data.om.Report
import com.arafeh.base.internal.core.ActivityLifecycle
import com.arafeh.base.internal.core.BaseActivity
import java.util.ArrayList

class ReportsActivity : BaseActivity(), ReportsPresenter.Events {
    companion object {
        fun navigate(reports: ArrayList<Report> = arrayListOf(), allowAdd: Boolean) {
            val bundle = Bundle()
            bundle.putParcelableArrayList("reports", reports)
            bundle.putBoolean("allow-add", allowAdd)
            App.navigator().navigate(ReportsActivity::class.java, bundle)
        }
    }

    private lateinit var map: GoogleMap
    lateinit var presenter: ReportsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        presenter = ReportsPresenter(this, intent.extras)
    }


    override fun onSuccess(report: Report) {
        ReportMarker(report).draw(map)
    }

    override fun onError(error: Throwable) {
        App.logger().e(error, "error")
    }

    override fun loading(state: Boolean) {
        App.loadingDialog().change(this, state)
    }

    override fun lifecycles(): ArrayList<ActivityLifecycle> {
        return arrayListOf(
                OnMapReady(this),
                IntentReportLoader(this),
                BottomButtonDialogAnimation(this),
                MapDragCleaner(this)
        )
    }


}
