package com.arafeh.base.view.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.arafeh.base.App
import com.arafeh.base.R
import com.arafeh.base.internal.core.BaseFragment
import com.arafeh.base.internal.core.FragmentLifecycle
import io.nlopez.smartlocation.geocoding.utils.LocationAddress

import java.util.ArrayList


class ExtendedMapFragment : BaseFragment(), ExtendedMapPresenter.Events {
    companion object {
        val MAP_READY: String = "MAP_READY"
        val SEARCH_RESULT: String = "SEARCH_RESULT"
    }
    val presenter = ExtendedMapPresenter(this)
    lateinit var map: GoogleMap
    var mapFragment: SupportMapFragment? = null
        get() = childFragmentManager.findFragmentById(R.id.supportMapFragment) as SupportMapFragment
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_map_extended, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment?.getMapAsync {
            this.map = it
            invokeParentLifecycle(MAP_READY, map)
        }
    }

    override fun lifecycles(): ArrayList<FragmentLifecycle> {
        return arrayListOf(SearchBarInit(this))
    }

    override fun loading(state: Boolean) {
        App.loadingDialog().change(activity, state)
    }

    override fun onQueryResult(result: List<LocationAddress>) {
        invokeParentLifecycle(SEARCH_RESULT, result)
        if (result.isEmpty()) {
            App.toast("no location found")
        } else {
            val location = result.first().location
            this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 13f))
        }
    }


}