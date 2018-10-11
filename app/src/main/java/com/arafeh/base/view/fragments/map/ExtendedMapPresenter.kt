package com.arafeh.base.view.fragments.map

import com.arafeh.base.App
import io.nlopez.smartlocation.geocoding.utils.LocationAddress

/**
 * Created by Arafeh on 7/15/2018.
 */
class ExtendedMapPresenter(var events: Events) {
    interface Events {
        fun loading(state: Boolean)
        fun onQueryResult(result: List<LocationAddress>)
    }

    fun mapSearchQuery(text: CharSequence?) {
        App.location().geocoding().direct(text.toString(), { _, result ->
            events.loading(false)
            events.onQueryResult(result)
        })
    }

}