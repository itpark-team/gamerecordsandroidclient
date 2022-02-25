package com.example.gamerecords.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gamerecords.R
import com.example.gamerecords.controllers.DetailsActivityController
import com.example.gamerecords.controllers.InsertNewActivityController

class InsertNewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_insert)

        var insertNewActivityController = InsertNewActivityController(this)
        insertNewActivityController.initialize()

    }
}