package com.arafeh.base.internal.core

import android.app.*
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Arafeh on 7/16/2018.
 */
abstract class BaseDialogFragment : DialogFragment() {
    private var onDismiss: (BaseDialogFragment) -> Unit = {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return if (layout() == null) {
            super.onCreateView(inflater, container, savedInstanceState)
        } else {
            inflater.inflate(layout()!!, container, false)
        }
    }


    fun show(activity: Activity): BaseDialogFragment {
        super.show(activity.fragmentManager, this::class.java.name)
        return this
    }

    fun onDismissCallback(onDismiss: (BaseDialogFragment) -> Unit): BaseDialogFragment {
        this.onDismiss = onDismiss
        return this
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        onDismiss(this)
    }

    open fun layout(): Int? {
        return null
    }


}