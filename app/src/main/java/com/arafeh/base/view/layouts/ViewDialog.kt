@file:Suppress("UNUSED_EXPRESSION")

package com.arafeh.base.view.layouts

import android.content.Context
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.arafeh.base.R
import com.arafeh.base.internal.core.BaseLinearLayout
import kotlinx.android.synthetic.main.layout_dialog_view.view.*

/**
 * Created by Arafeh on 7/15/2018.
 */
class ViewDialog : BaseLinearLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        btExit.setOnClickListener { animate(false) }
    }

    class ButtonInit(val content: Int, val onClick: (View) -> Unit)

    fun title(title: String): ViewDialog {
        this.tvTitle.visibility = View.VISIBLE
        this.tvTitle.text = title
        return this
    }

    fun message(message: Int): ViewDialog {
        this.tvMessage.visibility = View.VISIBLE
        this.tvMessage.text = context.getText(message)
        return this
    }

    fun positive(content: Int, onClick: (ViewDialog) -> Unit): ViewDialog {
        btPositive.visibility = View.VISIBLE
        btPositive.text = context.getText(content)
        btPositive.setOnClickListener { onClick(it as ViewDialog) }
        return this
    }

    fun negative(content: Int, onClick: (ViewDialog) -> Unit): ViewDialog {
        btNegative.visibility = View.VISIBLE
        btNegative.text = context.getText(content)
        btNegative.setOnClickListener { onClick(it as ViewDialog) }
        return this

    }

    fun neutral(content: Int, onClick: (ViewDialog) -> Unit): ViewDialog {
        btNeutral.visibility = View.VISIBLE
        btNeutral.text = context.getText(content)
        btNeutral.setOnClickListener { onClick(it as ViewDialog) }
        return this
    }

    fun show(animate: Boolean = true) {
        if (animate) animate(true)
        else this.visibility = View.VISIBLE
    }

    fun dismiss(animate: Boolean = true) {
        if (animate) animate(false)
        else this.visibility = View.GONE
    }

    fun animate(show: Boolean = true, onAnimationEnd: (() -> Unit)? = null) {
        if (show && visibility == View.VISIBLE || !show && visibility == View.GONE) return
        val animation = AnimationUtils.loadAnimation(context, if (show) R.anim.slide_in else R.anim.slide_out)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                this@ViewDialog.visibility = if (show) View.VISIBLE else View.GONE
                onAnimationEnd?.invoke()
            }
        })
        this.startAnimation(animation)
    }

    override fun layout(): Int {
        return R.layout.layout_dialog_view
    }

    fun initialize(title: String, positive: ButtonInit, negative: ButtonInit): ViewDialog {
        this.title(title)
        this.positive(positive.content, positive.onClick)
        this.negative(negative.content, negative.onClick)
        return this;
    }
}


