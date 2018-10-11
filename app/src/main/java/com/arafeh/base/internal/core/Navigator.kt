package com.arafeh.base.internal.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Arafeh on 7/12/2018.
 */
@Singleton
class Navigator @Inject
constructor(private val context: Context) {

    fun navigate(to: Class<out Activity>, parameters: Bundle? = null) {
        val intent = Intent(context, to)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (parameters != null) intent.putExtras(parameters)
        context.startActivity(intent)
    }

}
