package com.arafeh.base.internal.helpers

import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import android.view.ViewAnimationUtils

/**
 * Created by Arafeh on 7/16/2018.
 */
class AnimationUtils {
    enum class RevealPos { MIDDLE, BOTTOM_LEF, BOTTOM_RIGHT, TOP_LEFT, TOP_RIGHT }
    companion object {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun reveal(view: View, revealPos: RevealPos) {
            var x: Int
            var y: Int
            var er: Float
            when (revealPos) {
                RevealPos.MIDDLE -> {
                    x = view.right / 2
                    y = view.top / 2
                    er = Math.hypot(view.width.toDouble(), view.height.toDouble()).toFloat()
                }
                RevealPos.BOTTOM_LEF -> {
                    x = view.left
                    y = view.bottom
                    er = Math.hypot(view.width.toDouble(), view.height.toDouble()).toFloat()
                }
                RevealPos.BOTTOM_RIGHT -> {
                    x = view.right
                    y = view.bottom
                    er = Math.hypot(view.width.toDouble(), view.height.toDouble()).toFloat()
                }
                RevealPos.TOP_LEFT -> {
                    x = view.left
                    y = view.top
                    er = Math.hypot(view.width.toDouble(), view.height.toDouble()).toFloat()
                }
                RevealPos.TOP_RIGHT -> {
                    x = view.right
                    y = view.top
                    er = Math.hypot(view.width.toDouble(), view.height.toDouble()).toFloat()
                }
            }
            val anim = ViewAnimationUtils.createCircularReveal(view, x, y, 0f, er.toFloat())
            view.visibility = View.VISIBLE
            anim.duration = 1000
            anim.start()
        }
    }
}