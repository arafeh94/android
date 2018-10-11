package com.arafeh.base.internal.helpers

import android.content.Context
import android.text.Spanned
import com.arafeh.base.App
import com.arafeh.base.internal.core.CompatUtils

/**
 * Created by Arafeh on 7/16/2018.
 */

class SpannableUtils {
    companion object {
        fun text(text: String?, color: String?): Spanned {
            return CompatUtils.fromHtml("<font color='$color'><b>$text<b></font>")
        }

        fun text(stringId: Int, colorId: Int, context: Context = App.context()): Spanned {
            return CompatUtils.fromHtml("<font color='${CompatUtils.hexColor(context, colorId)}'><b>${context.getString(stringId)}<b></font>")
        }
    }
}
