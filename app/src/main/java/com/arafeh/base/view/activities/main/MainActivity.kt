package com.arafeh.base.view.activities.main

import android.os.Bundle
import com.arafeh.base.App
import com.arafeh.base.R
import com.arafeh.base.data.om.Report
import com.arafeh.base.internal.core.ActivityLifecycle
import com.arafeh.base.internal.core.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : BaseActivity(), MainPresenter.Events {
    val presenter = MainPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun lifecycles(): ArrayList<ActivityLifecycle> {
        return arrayListOf(
                ActionBarInit(this),
                PagerInit(this),
                WelcomeInit(this)
        )
    }

    override fun loading(state: Boolean) {
        App.loadingDialog().change(this, state)
    }

    override fun onSuccess(data: Report, tag: String) {
        App.toast("thank you :)")
    }

    override fun onFailed(data: Report, error: Throwable?, tag: String) {
        this.bottomDialog.message(R.string.error).positive(R.string.dismiss, { it.dismiss() })
                .negative(R.string.try_again, { presenter.submitReport(data) }).animate()
    }

}

