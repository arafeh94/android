package com.arafeh.base.view.activities.splash

import com.arafeh.base.R
import com.arafeh.base.internal.core.BaseActivity
import android.os.*
import com.arafeh.base.App
import com.arafeh.base.internal.core.ActivityLifecycle
import com.arafeh.base.view.activities.main.MainActivity
import com.arafeh.base.view.dialogs.TextDialog
import java.util.ArrayList


class SplashScreenActivity : BaseActivity() {
    val presenter: SplashScreenPresenter by lazy { SplashScreenPresenter(PresenterEventResponder()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        presenter.register()
    }

    override fun lifecycles(): ArrayList<ActivityLifecycle> {
        return arrayListOf(
                AnimationLoader(this),
                ViewLoader(this),
                PermissionRequest(this)
        )
    }

    inner class PresenterEventResponder : SplashScreenPresenter.Events {
        override fun loading(state: Boolean) {}

        override fun onRegistrationEnded(success: Boolean, error: Throwable?) {
            if (App.user().isGuest && !success) {
                App.toast("registration failed, please try again later")
            } else {
                App.navigator().navigate(MainActivity::class.java)
                finish()
            }
        }
    }


}
