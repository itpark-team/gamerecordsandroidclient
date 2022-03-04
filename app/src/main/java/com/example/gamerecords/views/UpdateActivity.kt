package com.example.gamerecords.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamerecords.R
import com.example.gamerecords.controllers.InsertNewActivityController
import com.example.gamerecords.controllers.UpdateActivityController

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        var updateActivityController = UpdateActivityController(this)
        updateActivityController.initialize()
    }
}