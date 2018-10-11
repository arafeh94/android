package com.arafeh.base.internal.core

import java.util.*
import javax.inject.Inject

/**
 * Created by Arafeh on 7/16/2018.
 */
class RunCounter @Inject constructor(var preferences: AppPreferences) {
    companion object {
        val SAVE_PATH = "run_counter"
    }

    val counter: CounterInfo

    init {
        counter = preferences.get(SAVE_PATH, CounterInfo::class.java, CounterInfo(0, Date()))
        counter.count += 1
        counter.lastRun = Date()
        preferences.put(SAVE_PATH, counter)
    }


    data class CounterInfo(var count: Int, var lastRun: Date)
}