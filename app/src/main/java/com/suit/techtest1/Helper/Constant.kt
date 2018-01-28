package com.suit.techtest1.Helper

/**
 * Created by Daniel on 1/27/2018.
 */

class Constant {
    companion object {
        var EVENT_PRIMAR_KEY: Int = -1

        fun getPk(): Int {
            EVENT_PRIMAR_KEY++
            return EVENT_PRIMAR_KEY
        }
    }
}
