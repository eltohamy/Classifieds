package com.dubizzle.classifieds.utility

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object Constants {
    const val TIME_OUT_SECONDS: Long = 120
    const val SPLASH_TIME: Long = 2500
    const val ACCEPT = "Accept"
    const val APPLICATION_JSON = "application/json"

    @SuppressLint("SimpleDateFormat")
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS")
    val dateFormat1 = SimpleDateFormat("dd MMM, yyyy")
}