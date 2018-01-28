package com.suit.techtest1.Extension

import java.util.*

/**
 * Created by Daniel on 1/27/2018.
 */

fun Date.currentDay(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_MONTH)
}