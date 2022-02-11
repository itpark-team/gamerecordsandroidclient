package com.example.gamerecords.controllers

import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.gamerecords.R
import com.example.gamerecords.apiworkers.RecordsApiWorker
import com.example.gamerecords.dtos.RecordResponseDto
import com.example.gamerecords.utils.GlobalVariables
import com.example.gamerecords.views.MainActivity
import com.google.gson.Gson

class MainActivityController(
    private var mainActivity: MainActivity
) {
    private lateinit var recordsApiWorker: RecordsApiWorker

    private lateinit var textViewData: TextView
    private lateinit var buttonLoadData: Button

    fun initialize() {
        recordsApiWorker = RecordsApiWorker()

        textViewData = mainActivity.findViewById(R.id.textViewData)

        buttonLoadData = mainActivity.findViewById(R.id.buttonLoadData)
        buttonLoadData.setOnClickListener {
            recordsApiWorker.getById(::updateTextViewData)
        }
    }

    private fun updateTextViewData(data: String) {
        var recordsResponseDto = Gson().fromJson(data,RecordResponseDto::class.java)

        textViewData.text = recordsResponseDto.convertToString()
    }

}