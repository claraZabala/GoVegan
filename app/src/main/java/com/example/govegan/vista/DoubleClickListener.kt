package com.example.govegan.vista

import android.view.View

//classe per a gestionar el doble click en el calendari per a afegir un nou plat
class DoubleClickListener(
    /*
    * milisegons que donem de marge entre click i click per a que es consideri dobleclick
    */
    val doubleClickTimeLimitMills: Long = 500,
    val callback: Callback
) :
    View.OnClickListener {
    var lastClicked: Long = -1L

    override fun onClick(v: View?) {
        lastClicked = when {
            lastClicked == -1L -> {
                System.currentTimeMillis()
            }
            isDoubleClicked() -> {
                callback.doubleClicked()
                -1L
            }
            else -> {
                System.currentTimeMillis()
            }
        }
    }

    fun getTimeDiff(from: Long, to: Long): Long {
        return to - from
    }

    fun isDoubleClicked(): Boolean {
        return getTimeDiff(
            lastClicked,
            System.currentTimeMillis()
        ) <= doubleClickTimeLimitMills
    }

    interface Callback {
        fun doubleClicked()
    }
}