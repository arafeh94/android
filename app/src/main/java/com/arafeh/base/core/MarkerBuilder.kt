package com.arafeh.base.core

import com.arafeh.base.R
import com.arafeh.base.data.enum.ReportType
import com.arafeh.base.data.om.Report
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.arafeh.base.App
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.CircleOptions


/**
 * Created by Arafeh on 7/15/2018.
 */
class MarkerBuilder(private val report: Report) {
    fun addMarker(map: GoogleMap): Marker? {
        val markerOptions = MarkerOptions()
        markerOptions.position(LatLng(report.lat, report.lng))
        markerOptions.icon(icon(
                if (report.type == ReportType.RADAR) R.drawable.img_pin_radar
                else R.drawable.img_pin_defence, 120, 120))

        return map.addMarker(markerOptions)
    }

    fun addCircle(map: GoogleMap): Circle? {
        val radiusInMeters = 200.0
        val strokeColor = -0x10000
        val shadeColor = 0x44ff7043

        val circleOptions = CircleOptions().center(LatLng(report.lat, report.lng))
                .radius(radiusInMeters)
                .fillColor(shadeColor)
                .strokeColor(strokeColor)
                .strokeWidth(4f)

        return map.addCircle(circleOptions)
    }


    fun icon(iconRes: Int, width: Int, height: Int): BitmapDescriptor? {
        val imageBitmap = BitmapFactory.decodeResource(App.context().resources, iconRes)
        val bitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}