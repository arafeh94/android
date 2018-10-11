package com.arafeh.base.core

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.arafeh.base.data.om.Report

/**
 * Created by Arafeh on 7/15/2018.
 */

class ReportMarker(var report: Report) {
    var marker: Marker? = null
    var circle: Circle? = null

    fun draw(map: GoogleMap, animate: Boolean = false) {
        val builder = MarkerBuilder(report)
        marker = builder.addMarker(map)
        circle = builder.addCircle(map)
        if (animate) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(report.lat, report.lng), 15f))
        }

    }
}
