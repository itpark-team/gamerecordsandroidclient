package com.example.gamerecords.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gamerecords.R
import com.example.gamerecords.controllers.DetailsActivityController

class DetailsActivity : AppCompatActivity() {
    private lateinit var detailsActivityController: DetailsActivityController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        detailsActivityController = DetailsActivityController(this)
        detailsActivityController.initialize()
    }

    override fun onStart() {
        super.onStart()
        detailsActivityController.initialize()
    }

}