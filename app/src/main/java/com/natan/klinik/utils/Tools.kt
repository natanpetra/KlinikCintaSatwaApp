package com.natan.klinik.utils

import android.content.Context

object Tools {
    var URL_IMAGE: String = "https://madiun.buatsoftware.com/uploads/"
    fun dp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }
}
