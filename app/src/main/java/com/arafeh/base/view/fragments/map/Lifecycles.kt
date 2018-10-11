package com.arafeh.base.view.fragments.map

import android.os.Bundle
import android.view.View
import com.arafeh.base.App
import com.arafeh.base.internal.core.FragmentLifecycle
import com.mancj.materialsearchbar.MaterialSearchBar
import kotlinx.android.synthetic.main.fragment_map_extended.*


/**
 * Created by Arafeh on 7/16/2018.
 */
class SearchBarInit(val fragment: ExtendedMapFragment) : FragmentLifecycle(fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragment.searchBar.lastSuggestions = loadSuggestions()
        fragment.searchBar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener {
            override fun onButtonClicked(buttonCode: Int) {}
            override fun onSearchStateChanged(enabled: Boolean) {}
            override fun onSearchConfirmed(text: CharSequence?) {
                fragment.presenter.mapSearchQuery(text)
            }
        })
    }

    override fun onStop() {
        val lastSuggestions = fragment.searchBar.lastSuggestions
        App.preferences().sharedPreferences.edit().putStringSet("last_map_suggestion", lastSuggestions.toMutableSet() as MutableSet<String>).apply()
    }


    private fun loadSuggestions(): List<String> {
        return App.preferences().sharedPreferences.getStringSet("last_map_suggestion", null)?.toMutableList() ?: mutableListOf()
    }
}
