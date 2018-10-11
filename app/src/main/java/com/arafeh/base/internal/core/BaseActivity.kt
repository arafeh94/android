package com.arafeh.base.internal.core

import android.annotation.SuppressLint
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem

import com.arafeh.base.R

import java.util.ArrayList

import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by Arafeh on 7/14/2018.
 */

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    var toolbar: Toolbar? = null
        private set

    val lifecycles: ArrayList<ActivityLifecycle> by lazy {
        lifecycles()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        onCreateBase(savedInstanceState, persistentState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateBase(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        lifecycles.forEach { it -> it.onPostCreate() }
    }

    private fun onCreateBase(savedInstanceState: Bundle? = null, persistentState: PersistableBundle? = null) {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/OpenSans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
        toolbar = findViewById(R.id.toolbar)
        if (toolbar != null) setSupportActionBar(toolbar)

        lifecycles.forEach { it.onCreate(savedInstanceState, persistentState) }
    }

    fun invokeLifecycle(tag: String, vararg params: Any) {
        lifecycles.forEach { it.onInvoke(tag, params) }
    }

    fun invokeLifecycle2(tag: String, params: Array<out Any>) {
        lifecycles.forEach { it.onInvoke(tag, params) }
    }

    override fun onRestart() {
        super.onRestart()
        lifecycles.forEach { it.onRestart() }
    }

    override fun onResume() {
        super.onResume()
        lifecycles.forEach { it.onResume() }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycles.forEach { it.onDestroy() }
    }

    override fun onPause() {
        super.onPause()
        lifecycles.forEach { it.onPause() }
    }

    override fun onStart() {
        super.onStart()
        lifecycles.forEach { it.onStart() }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        lifecycles.forEach { it.onSaveInstance(outState) }
    }


    protected open fun lifecycles(): ArrayList<ActivityLifecycle> {
        return ArrayList()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        lifecycles.forEach { it.onRequestPermissionsResult(requestCode, permissions, grantResults) }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        lifecycles.forEach { it.onNewIntent(intent) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        lifecycles.forEach { it.onCreateOptionsMenu(menu) }
        return true
    }

    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)
        lifecycles.forEach { it.onAttachFragment(fragment) }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        lifecycles.forEach { it.onContextItemSelected(item) }
        return super.onContextItemSelected(item)
    }

    override fun onContextMenuClosed(menu: Menu?) {
        lifecycles.forEach { it.onContextMenuClosed(menu) }
        super.onContextMenuClosed(menu)
    }

    override fun onKeyMultiple(keyCode: Int, repeatCount: Int, event: KeyEvent?): Boolean {
        lifecycles.forEach { it.onKeyMultiple(keyCode, repeatCount, event) }
        return super.onKeyMultiple(keyCode, repeatCount, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        lifecycles.forEach { it.onKeyUp(keyCode, event) }
        return super.onKeyUp(keyCode, event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        lifecycles.forEach { it.onKeyDown(keyCode, event) }
        return super.onKeyDown(keyCode, event)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        lifecycles.forEach { it.onActivityResult(requestCode, resultCode, data) }
    }

    override fun onBackPressed() {
        var runMain = true
        lifecycles.forEach { runMain = it.onBackPressed().and(runMain) }
        supportFragmentManager.fragments.forEach {
            it.takeIf { it is BaseFragment }.run { it as BaseFragment }.lifecycles.forEach {
                runMain = it.onActivityBackPressed().and(runMain)
            }
        }
        if (runMain) super.onBackPressed()
    }
}
