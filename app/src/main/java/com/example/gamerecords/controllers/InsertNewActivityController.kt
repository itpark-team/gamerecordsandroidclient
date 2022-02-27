package com.example.gamerecords.controllers

import android.view.View
import android.widget.*
import com.example.gamerecords.R
import com.example.gamerecords.apiworkers.CountriesApiWorker
import com.example.gamerecords.apiworkers.RecordsApiWorker
import com.example.gamerecords.dtos.entity.CountryResponseDto
import com.example.gamerecords.dtos.request.RecordRequestDto
import com.example.gamerecords.dtos.response.CountriesListResponseDto
import com.example.gamerecords.utils.GlobalVariables
import com.example.gamerecords.views.InsertNewActivity
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class InsertNewActivityController(
    private var insertNewActivity: InsertNewActivity
) {
    private lateinit var recordsApiWorker: RecordsApiWorker
    private lateinit var countriesApiWorker: CountriesApiWorker

    private lateinit var selectedCountry: CountryResponseDto

    private lateinit var editTextActivityNewInsertNickname: EditText
    private lateinit var editTextActivityNewInsertScore: EditText
    private lateinit var editTextActivityNewInsertGame: EditText
    private lateinit var editTextActivityNewInsertDateTime: EditText

    private lateinit var buttonActivityNewInsertBack: Button
    private lateinit var buttonActivityNewInsertAdd: Button

    fun initialize() {
        recordsApiWorker = RecordsApiWorker()
        countriesApiWorker = CountriesApiWorker()
        countriesApiWorker.getAll(::updateSpinnerActivityNewInsertCountry)

        editTextActivityNewInsertNickname =
            insertNewActivity.findViewById(R.id.editTextActivityNewInsertNickname)
        editTextActivityNewInsertScore =
            insertNewActivity.findViewById(R.id.editTextActivityNewInsertScore)
        editTextActivityNewInsertGame =
            insertNewActivity.findViewById(R.id.editTextActivityNewInsertGame)
        editTextActivityNewInsertDateTime =
            insertNewActivity.findViewById(R.id.editTextActivityNewInsertDateTime)

        buttonActivityNewInsertBack =
            insertNewActivity.findViewById(R.id.buttonActivityNewInsertBack)
        buttonActivityNewInsertBack.setOnClickListener {
            insertNewActivity.finish()
        }

        buttonActivityNewInsertAdd = insertNewActivity.findViewById(R.id.buttonActivityNewInsertAdd)
        buttonActivityNewInsertAdd.setOnClickListener {
            insertOneRecord()
        }
    }

    private fun updateSpinnerActivityNewInsertCountry(data: String) {
        var countriesListResponseDto = Gson().fromJson(data, CountriesListResponseDto::class.java)

        var countriesList = countriesListResponseDto.countriesList

        var countriesNames: ArrayList<String> = ArrayList()
        for (country in countriesList) {
            countriesNames.add(country.name)
        }

        var spinner: Spinner = insertNewActivity.findViewById(R.id.spinnerActivityNewInsertCountry)

        val adapter = ArrayAdapter(
            GlobalVariables.instance.applicationContext,
            android.R.layout.simple_spinner_item,
            countriesNames
        )

        spinner.adapter = adapter

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                selectedCountry = countriesList[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    private fun insertOneRecord() {
        var nickname = editTextActivityNewInsertNickname.text.toString()
        var score = editTextActivityNewInsertScore.text.toString().toInt()
        var game = editTextActivityNewInsertGame.text.toString()

        var dateTime = editTextActivityNewInsertDateTime.text.toString()

        var dateFormatter = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
        var date = dateFormatter.parse(dateTime)

        var unixDateTime = date.time

        var countryId = selectedCountry.id

        var recordRequestDto = RecordRequestDto(nickname, score, game, unixDateTime, countryId)

        recordsApiWorker.insertOne(recordRequestDto, ::checkInsertOne)
    }

    private fun checkInsertOne(data: String) {
        Toast.makeText(
            GlobalVariables.instance.applicationContext,
            "Запись успешно добавлена",
            Toast.LENGTH_LONG
        ).show()
    }
}