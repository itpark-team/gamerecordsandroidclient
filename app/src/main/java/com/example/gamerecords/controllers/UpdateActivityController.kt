package com.example.gamerecords.controllers

import android.view.View
import android.widget.*
import com.example.gamerecords.R
import com.example.gamerecords.apiworkers.CountriesApiWorker
import com.example.gamerecords.apiworkers.RecordsApiWorker
import com.example.gamerecords.dtos.entity.CountryResponseDto
import com.example.gamerecords.dtos.entity.RecordResponseDto
import com.example.gamerecords.dtos.request.RecordRequestDto
import com.example.gamerecords.dtos.response.CountriesListResponseDto
import com.example.gamerecords.utils.GlobalVariables
import com.example.gamerecords.views.UpdateActivity
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UpdateActivityController(
    private var updateActivity: UpdateActivity
) {
    private lateinit var editTextActivityUpdateNickname: EditText
    private lateinit var editTextActivityUpdateScore: EditText
    private lateinit var editTextActivityUpdateGame: EditText
    private lateinit var editTextActivityUpdateDateTime: EditText

    private lateinit var buttonActivityUpdateBack: Button
    private lateinit var buttonActivityUpdateUpdate: Button

    private lateinit var recordsApiWorker: RecordsApiWorker
    private lateinit var countriesApiWorker: CountriesApiWorker

    private lateinit var selectedCountry: CountryResponseDto

    private var globalVariables = GlobalVariables.instance
    private var record = globalVariables.currentRecord
    private lateinit var tempRecord: RecordResponseDto


    fun initialize() {
        recordsApiWorker = RecordsApiWorker()
        countriesApiWorker = CountriesApiWorker()
        countriesApiWorker.getAll(::updateSpinnerActivityNewInsertCountry)

        editTextActivityUpdateNickname =
            updateActivity.findViewById(R.id.editTextActivityUpdateNickname)
        editTextActivityUpdateScore =
            updateActivity.findViewById(R.id.editTextActivityUpdateScore)
        editTextActivityUpdateGame =
            updateActivity.findViewById(R.id.editTextActivityUpdateGame)
        editTextActivityUpdateDateTime =
            updateActivity.findViewById(R.id.editTextActivityUpdateDateTime)

        editTextActivityUpdateNickname.setText(record.nickname)
        editTextActivityUpdateScore.setText(record.score.toString())
        editTextActivityUpdateGame.setText(record.game)

        var dateFormatter = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
        var date = Date(record.unixDateTime)
        editTextActivityUpdateDateTime.setText(dateFormatter.format(date))

        buttonActivityUpdateBack =
            updateActivity.findViewById(R.id.buttonActivityUpdateBack)
        buttonActivityUpdateBack.setOnClickListener {
            updateActivity.finish()
        }

        buttonActivityUpdateUpdate = updateActivity.findViewById(R.id.buttonActivityUpdateUpdate)
        buttonActivityUpdateUpdate.setOnClickListener {
            updateRecordById()
        }
    }

    private fun updateSpinnerActivityNewInsertCountry(data: String) {
        var countriesListResponseDto = Gson().fromJson(data, CountriesListResponseDto::class.java)

        var countriesList = countriesListResponseDto.countriesList

        var countriesNames: ArrayList<String> = ArrayList()
        for (country in countriesList) {
            countriesNames.add(country.name)
        }

        var spinner: Spinner = updateActivity.findViewById(R.id.spinnerActivityUpdateCountry)

        val adapter = ArrayAdapter(
            globalVariables.applicationContext,
            android.R.layout.simple_spinner_item,
            countriesNames
        )

        spinner.adapter = adapter

        var countryIndex = countriesNames.indexOf(record.countryName)
        spinner.setSelection(countryIndex)

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

    private fun updateRecordById() {
        var nickname = editTextActivityUpdateNickname.text.toString()
        var score = editTextActivityUpdateScore.text.toString().toInt()
        var game = editTextActivityUpdateGame.text.toString()

        var dateTime = editTextActivityUpdateDateTime.text.toString()

        var dateFormatter = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
        var date = dateFormatter.parse(dateTime)

        var unixDateTime = date.time

        var countryId = selectedCountry.id

        var recordRequestDto = RecordRequestDto(nickname, score, game, unixDateTime, countryId)

        recordsApiWorker.updateById(record.id, recordRequestDto, ::checkUpdateById)

        tempRecord =
            RecordResponseDto(record.id, nickname, score, game, unixDateTime, selectedCountry.name)
    }

    private fun checkUpdateById(data: String) {
        globalVariables.currentRecord = tempRecord

        Toast.makeText(
            globalVariables.applicationContext,
            "Запись успешно обновлена",
            Toast.LENGTH_LONG
        ).show()
    }
}