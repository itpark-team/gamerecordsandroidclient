package com.example.gamerecords.controllers

import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.gamerecords.R
import com.example.gamerecords.apiworkers.RecordsApiWorker
import com.example.gamerecords.dtos.entity.RecordResponseDto
import com.example.gamerecords.utils.GlobalVariables
import com.example.gamerecords.views.DetailsActivity
import com.example.gamerecords.views.UpdateActivity
import java.text.SimpleDateFormat
import java.util.*

class DetailsActivityController(private var detailsActivity: DetailsActivity) {

    private lateinit var buttonActivityDetailsBack: Button
    private lateinit var textViewActivityDetailsNickname: TextView
    private lateinit var textViewActivityDetailsScore: TextView
    private lateinit var textViewActivityDetailsGame: TextView
    private lateinit var textViewActivityDetailsDateTime: TextView
    private lateinit var textViewActivityDetailsCountry: TextView
    private lateinit var buttonActivityDetailsDelete: Button
    private lateinit var buttonActivityDetailsUpdate: Button

    private lateinit var recordsApiWorker: RecordsApiWorker

    private var globalVariables = GlobalVariables.instance

    fun initialize() {
        var record = globalVariables.currentRecord

        recordsApiWorker = RecordsApiWorker()

        textViewActivityDetailsNickname =
            detailsActivity.findViewById(R.id.textViewActivityDetailsNickname)
        textViewActivityDetailsScore =
            detailsActivity.findViewById(R.id.textViewActivityDetailsScore)
        textViewActivityDetailsGame = detailsActivity.findViewById(R.id.textViewActivityDetailsGame)
        textViewActivityDetailsDateTime =
            detailsActivity.findViewById(R.id.textViewActivityDetailsDateTime)
        textViewActivityDetailsCountry =
            detailsActivity.findViewById(R.id.textViewActivityDetailsCountry)

        buttonActivityDetailsBack = detailsActivity.findViewById(R.id.buttonActivityDetailsBack)
        buttonActivityDetailsDelete = detailsActivity.findViewById(R.id.buttonActivityDetailsDelete)
        buttonActivityDetailsUpdate = detailsActivity.findViewById(R.id.buttonActivityDetailsUpdate)

        textViewActivityDetailsNickname.text = record.nickname
        textViewActivityDetailsScore.text = record.score.toString()
        textViewActivityDetailsGame.text = record.game

        var dateFormatter = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
        var date = Date(record.unixDateTime)

        textViewActivityDetailsDateTime.text = dateFormatter.format(date)
        textViewActivityDetailsCountry.text = record.countryName.toString()

        buttonActivityDetailsBack.setOnClickListener {
            detailsActivity.finish()
        }

        buttonActivityDetailsDelete.setOnClickListener {
            recordsApiWorker.deleteById(record.id) {
                Toast.makeText(
                    globalVariables.applicationContext,
                    "Успешно удалено",
                    Toast.LENGTH_LONG
                ).show()

                //detailsActivity.finish()
            }
        }

        buttonActivityDetailsUpdate.setOnClickListener {
            var intent = Intent(globalVariables.applicationContext, UpdateActivity::class.java)
            globalVariables.applicationContext.startActivity(intent)
        }

    }
}