package com.arafeh.base.view.activities.report

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.arafeh.base.data.om.Report

/**
 * Created by Arafeh on 7/15/2018.
 */
class ReportsPresenter(val events: ReportsPresenter.Events, intentBundle: Bundle) {
    inner class RequestCreator(var lat: Double? = null, var lng: Double? = null, var type: Int? = null)

    private var requestCreator: RequestCreator? = null

    var allowAddNewRecords: Boolean = intentBundle?.getBoolean("allow-add", true) ?: false
    var reports: ArrayList<Report>

    init {
        reports = intentBundle?.getParcelableArrayList("reports") ?: arrayListOf()
    }

    fun requestBuilder(): RequestCreator {
        return requestCreator ?: RequestCreator()
    }

    fun proceedRequest() {
        try {
            validateRequest()
            events.loading(true)
            Handler(Looper.getMainLooper()).postDelayed({
                events.onSuccess(Report(1, requestCreator!!.lat!!, requestCreator!!.lng!!, 1f, "123", requestCreator!!.type!!))
                events.loading(false)
                requestCreator = null
            }, 2000)
        } catch (e: Exception) {
            events.onError(e)
            requestCreator = null
        }
    }

    private fun validateRequest() {
        if (requestCreator == null) {
            throw RuntimeException("did you miss calling requestBuilder() before executing request")
        }
        if (requestCreator!!.lat == null || requestCreator!!.lng == null || requestCreator!!.type == null) {
            throw RuntimeException("missing request parameter")
        }
    }


    interface Events {
        fun onSuccess(report: Report)
        fun onError(error: Throwable)
        fun loading(state: Boolean)
    }


}