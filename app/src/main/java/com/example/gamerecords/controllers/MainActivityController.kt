package com.example.gamerecords.controllers

import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamerecords.R
import com.example.gamerecords.apiworkers.RecordsApiWorker
import com.example.gamerecords.dtos.response.RecordsListResponseDto
import com.example.gamerecords.views.MainActivity
import com.google.gson.Gson

class MainActivityController(
    private var mainActivity: MainActivity
) {
    private lateinit var recordsApiWorker: RecordsApiWorker

    private lateinit var recyclerViewActivityMain: RecyclerView
    private lateinit var buttonActivityMainLoadRecordsList: Button

    fun initialize() {
        recordsApiWorker = RecordsApiWorker()

        recyclerViewActivityMain = mainActivity.findViewById(R.id.recyclerViewActivityMain)

        buttonActivityMainLoadRecordsList =
            mainActivity.findViewById(R.id.buttonActivityMainLoadData)

        buttonActivityMainLoadRecordsList.setOnClickListener {
           loadRecordsList()
        }
    }

    fun loadRecordsList(){
        recordsApiWorker.getAll(::updateTextViewData)
    }

    private fun updateTextViewData(data: String) {
        var recordsResponseDto = Gson().fromJson(data, RecordsListResponseDto::class.java)

        recyclerViewActivityMain.layoutManager =
            LinearLayoutManager(mainActivity.applicationContext)

        recyclerViewActivityMain.adapter = RvAdapterMainActivity(recordsResponseDto.recordsList)
    }

}