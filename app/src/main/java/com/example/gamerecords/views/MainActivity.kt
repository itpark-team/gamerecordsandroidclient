package com.example.gamerecords.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamerecords.R
import com.example.gamerecords.controllers.MainActivityController
import com.example.gamerecords.utils.GlobalVariables
import com.example.gamerecords.utils.HttpWorker

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var globalVariables = GlobalVariables.instance
        globalVariables.applicationContext = applicationContext
        globalVariables.httpWorker = HttpWorker(applicationContext)

        var mainActivityController = MainActivityController(this)
        mainActivityController.initialize()
    }
}