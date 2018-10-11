package com.arafeh.base.internal.core

import android.view.View
import butterknife.ButterKnife

/**
 * Created by Arafeh on 7/14/2018.
 */
abstract class BaseViewHolder<T>(view: View, t: T) {
    init {
        ButterKnife.bind(this, view);
    }

    abstract fun bind();
    abstract fun reBind()
}