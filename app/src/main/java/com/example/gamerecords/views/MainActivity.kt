package com.example.gamerecords.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamerecords.R
import com.example.gamerecords.controllers.MainActivityController
import com.example.gamerecords.utils.GlobalVariables
import com.example.gamerecords.utils.HttpWorker

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityController: MainActivityController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var globalVariables = GlobalVariables.instance
        globalVariables.applicationContext = applicationContext
        globalVariables.httpWorker = HttpWorker(applicationContext)

        mainActivityController = MainActivityController(this)
        mainActivityController.initialize()
    }

    override fun onStart() {
        super.onStart()
        mainActivityController.loadRecordsList()
    }
}