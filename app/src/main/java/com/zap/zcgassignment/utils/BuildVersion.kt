package com.zap.zcgassignment.utils

import android.os.Build

object BuildVersion {
    fun getVersionSDKInt(): Int {
        return Build.VERSION.SDK_INT
    }
}