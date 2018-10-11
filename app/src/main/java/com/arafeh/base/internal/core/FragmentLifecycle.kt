package com.arafeh.base.internal.core

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

/**
 * Created by Arafeh on 7/15/2018.
 */
abstract class FragmentLifecycle(fragment: BaseFragment) {
    open fun onCreate(savedInstanceState: Bundle?) {}
    open fun onPostCreate() {}
    open fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {}
    open fun onViewCreated(view: View, savedInstanceState: Bundle?) {}
    open fun onResume() {}
    open fun onStart() {}
    open fun onPause() {}
    open fun onAttach(context: Context?) {}
    open fun onDeAttach() {}
    open fun onInvoke(tag: String, params: Array<out Any>?) {}
    open fun onDestroy() {}
    open fun onStop() {}
    open fun onSavedInstance(outState: Bundle) {}
    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {}
    open fun onAttachFragment(childFragment: Fragment?) {}
    open fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {}
    open fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {}
    open fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {}
    open fun onOptionsItemSelected(item: MenuItem?) {}
    /**
     * @return return true of the component don't mind the main activity back press to be
     *      executed, otherwise return false to stop the main back press to be executed
     */
    open fun onActivityBackPressed() = true

}