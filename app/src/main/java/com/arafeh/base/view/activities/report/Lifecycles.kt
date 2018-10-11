package com.arafeh.base.view.activities.report

import com.arafeh.base.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.arafeh.base.core.ReportMarker
import com.arafeh.base.internal.core.ActivityLifecycle
import com.arafeh.base.view.dialogs.ReportTypeDialog
import com.arafeh.base.view.fragments.map.ExtendedMapFragment
import com.arafeh.base.view.layouts.ViewDialog
import kotlinx.android.synthetic.main.activity_report.*

/**
 * Created by Arafeh on 7/16/2018.
 */
class MapDragCleaner(private val activity: ReportsActivity) : ActivityLifecycle(activity) {
    override fun onInvoke(tag: String, params: Array<out Any>?) {
        if (tag != ExtendedMapFragment.MAP_READY) return
        val googleMap = params!![0] as GoogleMap
        googleMap.setOnCameraMoveStartedListener { activity.dialogView.animate(false) }

    }
}


class OnMapReady(private val activity: ReportsActivity) : ActivityLifecycle(activity) {
    override fun onInvoke(tag: String, params: Array<out Any>?) {
        if (tag != ExtendedMapFragment.MAP_READY) return
        val map = params!![0] as GoogleMap
        map.takeIf { activity.presenter.allowAddNewRecords }?.setOnMapClickListener { latlng ->
            activity.presenter.requestBuilder().lat = latlng.latitude
            activity.presenter.requestBuilder().lng = latlng.longitude
            ReportTypeDialog().setOnReportRequest({
                activity.presenter.requestBuilder().type = it
                activity.presenter.proceedRequest()
            }).show(activity)
        }
    }
}

class IntentReportLoader(private val activity: ReportsActivity) : ActivityLifecycle(activity) {
    override fun onInvoke(tag: String, params: Array<out Any>?) {
        if (tag != ExtendedMapFragment.MAP_READY) return
        val map = params!![0] as GoogleMap
        activity.presenter.reports.forEach { ReportMarker(it).draw(map) }
        activity.presenter.reports.firstOrNull()?.let {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.lat, it.lng), 14f))
        }
    }
}

class BottomButtonDialogAnimation(private val activity: ReportsActivity) : ActivityLifecycle(activity) {
    override fun onStart() {
        activity.dialogView.initialize("hello to our apps",
                ViewDialog.ButtonInit(R.string.got_it, {}),
                ViewDialog.ButtonInit(R.string.exit, {})
        ).animate(true)
    }
}
