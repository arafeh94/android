package com.arafeh.base.internal.core;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;

/**
 * Created by Arafeh on 7/16/2018.
 */

public class CompatUtils {
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    public static int color(Context context, @ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }

    public static String hexColor(Context context, @ColorRes int color) {
        return String.format("#%06X", (0xFFFFFF & color(context, color)));
    }
}
