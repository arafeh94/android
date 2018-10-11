package com.arafeh.base.internal.core

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem

/**
 * Created by Arafeh on 7/15/2018.
 */
@Suppress("UNUSED_PARAMETER")
abstract class ActivityLifecycle(activity: Activity) {
    open fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {}
    open fun onPostCreate() {}
    open fun onResume() {}
    open fun onStart() {}
    open fun onPause() {}
    open fun onDestroy() {}
    open fun onRestart() {}
    open fun onInvoke(tag: String, params: Array<out Any>?) {}
    open fun onSaveInstance(outState: Bundle?) {}
    open fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {}
    open fun onNewIntent(intent: Intent?) {}
    open fun onCreateOptionsMenu(menu: Menu?) {}
    open fun onAttachFragment(fragment: Fragment?) {}
    open fun onContextItemSelected(item: MenuItem?) {}
    open fun onContextMenuClosed(menu: Menu?) {}
    open fun onKeyMultiple(keyCode: Int, repeatCount: Int, event: KeyEvent?) {}
    open fun onKeyUp(keyCode: Int, event: KeyEvent?) {}
    open fun onKeyDown(keyCode: Int, event: KeyEvent?) {}
    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {}
    /**
     * @return return true of the component don't mind the main activity back press to be
     *      executed, otherwise return false to stop the main back press to be executed
     */
    open fun onBackPressed() = true
}

