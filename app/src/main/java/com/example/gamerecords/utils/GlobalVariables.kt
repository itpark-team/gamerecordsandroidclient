package com.example.gamerecords.utils

import android.content.Context

class GlobalVariables {
    companion object {
        val instance = GlobalVariables()
    }

    lateinit var applicationContext: Context
    lateinit var httpWorker: HttpWorker
}