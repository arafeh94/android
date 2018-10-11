package com.arafeh.base.view.activities.splash

import android.Manifest
import android.os.Handler
import android.os.Looper
import com.arafeh.base.App
import com.arafeh.base.R
import com.arafeh.base.internal.core.ActivityLifecycle
import com.arafeh.base.internal.core.BaseActivity
import com.arafeh.base.internal.core.KnownInvokedEvents
import com.arafeh.base.view.activities.main.MainActivity
import com.arafeh.base.view.dialogs.TextDialog
import com.master.permissionhelper.PermissionHelper

/**
 * Created by Arafeh on 7/16/2018.
 */

class AnimationLoader(var activity: SplashScreenActivity) : ActivityLifecycle(activity) {
    override fun onPostCreate() {


    }
}

class ViewLoader(var activity: SplashScreenActivity) : ActivityLifecycle(activity) {
    override fun onPostCreate() {

    }
}

class DelayedNext(var activity: SplashScreenActivity) : ActivityLifecycle(activity) {
    override fun onInvoke(tag: String, params: Array<out Any>?) {
        if (tag != KnownInvokedEvents.ALL_PERMISSIONS_GRANTED) return
        Handler(Looper.getMainLooper()).postDelayed({
            App.navigator().navigate(MainActivity::class.java)
            activity.finish()
        }, 4000)
    }
}

class PermissionRequest(var activity: BaseActivity) : ActivityLifecycle(activity) {
    val permissionHelper: PermissionHelper by lazy {
        val permissionHelper = PermissionHelper(activity, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))
        permissionHelper.denied {
            when (App.preferences().count("tarajje")) {
                1 -> {
                    TextDialog().contentResId(R.string.on_permission_denied).onDismissCallback {
                        permissionHelper.requestAll { onPermissionsGranted() }
                    }.show(activity)
                }
                else -> activity.finish()
            }
        }
        permissionHelper
    }

    val onPermissionsGranted = {
        activity.invokeLifecycle(KnownInvokedEvents.ALL_PERMISSIONS_GRANTED)
    }

    override fun onStart() {
        permissionHelper.requestAll {
            onPermissionsGranted()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}

class PresenterEventsResponder(activity: SplashScreenActivity) : ActivityLifecycle(activity) {

}