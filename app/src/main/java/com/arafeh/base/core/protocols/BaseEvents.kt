package com.arafeh.base.core.protocols

/**
 * Created by Arafeh on 7/18/2018.
 */

interface BaseEvents<in T> {
    fun loading(state: Boolean)

    fun onSuccess(data: T, tag: String = "p0")

    fun onFailed(data: T, error: Throwable? = null, tag: String = "p0")
}
