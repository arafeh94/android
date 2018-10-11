package com.arafeh.base.view.dialogs

import android.os.Bundle
import android.support.annotation.StringRes
import android.view.View
import com.arafeh.base.R
import com.arafeh.base.internal.core.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_text.*


/**
 * Created by Arafeh on 7/15/2018.
 */
class TextDialog : BaseDialogFragment() {
    private var contentResId: Int = R.string.dumb
    private var okResId: Int = R.string.got_id

    fun contentResId(@StringRes contentId: Int): TextDialog {
        this.contentResId = contentId
        return this
    }

    fun okResId(@StringRes okId: Int): TextDialog {
        this.okResId = okId
        return this
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.content.setText(contentResId)
        this.positive.setText(okResId)
        this.positive.setOnClickListener { dismiss() }
    }

    override fun layout(): Int? {
        return R.layout.dialog_text
    }

}