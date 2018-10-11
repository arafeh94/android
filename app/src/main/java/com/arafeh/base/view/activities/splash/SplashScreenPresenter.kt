package com.arafeh.base.view.activities.splash

import com.arafeh.base.App
import com.arafeh.base.core.exceptions.UnknownDeviceException
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Arafeh on 7/12/2018.
 */
class SplashScreenPresenter(var events: Events) {

    fun register() {
        val id = App.easy().id().gsfid
        if (id == EasyDeviceInfo.notFoundVal) {
            events.onRegistrationEnded(false, UnknownDeviceException())
        }
        var call = if (App.user().isGuest) App.remote().register(id) else App.remote().validate()
        events.loading(true)
        call.doOnSuccess { App.user().login(it) }.subscribeOn(AndroidSchedulers.mainThread()).subscribe({
            events.loading(false)
            events.onRegistrationEnded(true)
        }, {
            events.onRegistrationEnded(false, it)
        })
    }


    interface Events {
        fun loading(state: Boolean)
        fun onRegistrationEnded(success: Boolean, error: Throwable? = null)
    }
}