package com.arafeh.base.internal.core

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import java.util.ArrayList

/**
 * Created by Arafeh on 7/14/2018.
 */

@SuppressLint("Registered")
open class BaseFragment : Fragment() {
    val lifecycles: ArrayList<FragmentLifecycle>by lazy {
        lifecycles()
    }

    private var started: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycles.forEach { it.onCreate(savedInstanceState) }
    }

    open fun onPostCreate() {
        lifecycles.forEach { it.onPostCreate() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        lifecycles.forEach { it.onCreateView(inflater, container, savedInstanceState) }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycles.forEach { it.onViewCreated(view, savedInstanceState) }
    }

    protected fun invokeLifecycle(tag: String, vararg params: Any) {
        lifecycles.forEach { it.onInvoke(tag, params) }
    }

    protected fun invokeParentLifecycle(tag: String, vararg params: Any, includeSelf: Boolean = true) {
        if (includeSelf) invokeLifecycle2(tag, params)
        if (activity is BaseActivity) {
            (activity as BaseActivity).invokeLifecycle2(tag, params)
        }
    }

    private fun invokeLifecycle2(tag: String, params: Array<out Any>) {
        lifecycles.forEach { it.onInvoke(tag, params) }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        lifecycles.forEach { it.onAttach(context) }
    }

    override fun onDetach() {
        lifecycles.forEach { it.onDeAttach() }
        super.onDetach()
    }

    override fun onResume() {
        super.onResume()
        lifecycles.forEach { it.onResume() }
    }

    override fun onDestroy() {
        lifecycles.forEach { it.onDestroy() }
        super.onDestroy()
    }

    override fun onPause() {
        lifecycles.forEach { it.onPause() }
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
        if (!started) {
            started = true
            onPostCreate()
        }
        lifecycles.forEach { it.onStart() }
    }

    override fun onStop() {
        lifecycles.forEach { it.onStop() }
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        lifecycles.forEach { it.onSavedInstance(outState) }
    }

    protected open fun lifecycles(): ArrayList<FragmentLifecycle> {
        return ArrayList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        lifecycles.forEach { it.onActivityResult(requestCode, resultCode, data) }
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
        lifecycles.forEach { it.onAttachFragment(childFragment) }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        lifecycles.forEach { it.onCreateOptionsMenu(menu, inflater) }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        lifecycles.forEach { it.onCreateContextMenu(menu, v, menuInfo) }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        lifecycles.forEach { it.onRequestPermissionsResult(requestCode, permissions, grantResults) }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        lifecycles.forEach { it.onOptionsItemSelected(item) }
        return super.onOptionsItemSelected(item)
    }


}