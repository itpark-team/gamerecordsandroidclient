package com.example.gamerecords.controllers

import android.content.Intent
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamerecords.R
import com.example.gamerecords.adapters.RvAdapterMainActivity
import com.example.gamerecords.apiworkers.RecordsApiWorker
import com.example.gamerecords.dtos.response.RecordsListResponseDto
import com.example.gamerecords.utils.GlobalVariables
import com.example.gamerecords.views.InsertNewActivity
import com.example.gamerecords.views.MainActivity
import com.google.gson.Gson

class MainActivityController(
    private var mainActivity: MainActivity
) {
    private lateinit var recyclerViewActivityMain: RecyclerView
    private lateinit var buttonActivityMainLoadRecordsList: Button
    private lateinit var buttonActivityMainInsertNew: Button

    private lateinit var recordsApiWorker: RecordsApiWorker

    private var globalVariables = GlobalVariables.instance

    fun initialize() {
        recordsApiWorker = RecordsApiWorker()

        recyclerViewActivityMain = mainActivity.findViewById(R.id.recyclerViewActivityMain)

        buttonActivityMainLoadRecordsList =
            mainActivity.findViewById(R.id.buttonActivityMainLoadData)

        buttonActivityMainLoadRecordsList.setOnClickListener {
            loadRecordsList()
        }

        buttonActivityMainInsertNew = mainActivity.findViewById(R.id.buttonActivityMainInsertNew)

        buttonActivityMainInsertNew.setOnClickListener {
            var intent =
                Intent(globalVariables.applicationContext, InsertNewActivity::class.java)

            mainActivity.startActivity(intent)
        }
    }

    fun loadRecordsList() {
        recordsApiWorker.getAll(::updateRecyclerViewActivityMain)
    }

    private fun updateRecyclerViewActivityMain(data: String) {
        var recordsListResponseDto = Gson().fromJson(data, RecordsListResponseDto::class.java)

        recyclerViewActivityMain.layoutManager =
            LinearLayoutManager(mainActivity.applicationContext)

        recyclerViewActivityMain.adapter = RvAdapterMainActivity(recordsListResponseDto.recordsList)
    }

}