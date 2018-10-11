package com.arafeh.base.view.fragments.mainreport


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arafeh.base.R
import com.arafeh.base.internal.core.BaseFragment
import com.arafeh.base.internal.core.FragmentLifecycle
import java.util.ArrayList


class MainMapFragment : BaseFragment() {
    private val presenter = MainReportsPresenter()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_main_report, container, false)
    }

    override fun lifecycles(): ArrayList<FragmentLifecycle> {
        return arrayListOf()
    }


}
