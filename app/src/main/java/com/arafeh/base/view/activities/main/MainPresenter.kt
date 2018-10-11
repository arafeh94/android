package com.arafeh.base.view.activities.main

import com.arafeh.base.App
import com.arafeh.base.core.protocols.BaseEvents
import com.arafeh.base.data.om.Report
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Arafeh on 7/15/2018.
 */
class MainPresenter(val events: Events) {

    interface Events : BaseEvents<Report>

    fun submitReport(report: Report) {
        App.remote().report(report).observeOn(AndroidSchedulers.mainThread()).subscribe({
            events.loading(false)
            events.onSuccess(report)
        }, {
            events.loading(false)
            events.onFailed(report, it)
        })
    }

}