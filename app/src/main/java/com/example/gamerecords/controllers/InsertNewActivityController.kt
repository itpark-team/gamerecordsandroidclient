package com.example.gamerecords.controllers

import android.view.View
import android.widget.*
import com.example.gamerecords.R
import com.example.gamerecords.apiworkers.CountriesApiWorker
import com.example.gamerecords.apiworkers.RecordsApiWorker
import com.example.gamerecords.dtos.entity.CountryResponseDto
import com.example.gamerecords.dtos.response.CountriesListResponseDto
import com.example.gamerecords.utils.GlobalVariables
import com.example.gamerecords.views.InsertNewActivity
import com.google.gson.Gson

class InsertNewActivityController(
    private var insertNewActivity: InsertNewActivity
) {
    private lateinit var recordsApiWorker: RecordsApiWorker
    private lateinit var countriesApiWorker: CountriesApiWorker

    private lateinit var selectedCountry: CountryResponseDto

    private lateinit var editTextActivityNewInsertNickname:EditText
    private lateinit var editTextActivityNewInsertScore:EditText
    private lateinit var editTextActivityNewInsertGame:EditText
    private lateinit var editTextActivityNewInsertDateTime:EditText

    private lateinit var buttonActivityNewInsertBack:Button
    private lateinit var buttonActivityNewInsertAdd:Button

    fun initialize() {
        countriesApiWorker = CountriesApiWorker()
        countriesApiWorker.getAll(::updateSpinnerActivityNewInsertCountry)

        editTextActivityNewInsertNickname = insertNewActivity.findViewById(R.id.editTextActivityNewInsertNickname)
        editTextActivityNewInsertScore = insertNewActivity.findViewById(R.id.editTextActivityNewInsertScore)
        editTextActivityNewInsertGame = insertNewActivity.findViewById(R.id.editTextActivityNewInsertGame)
        editTextActivityNewInsertDateTime = insertNewActivity.findViewById(R.id.editTextActivityNewInsertDateTime)

        buttonActivityNewInsertBack = insertNewActivity.findViewById(R.id.buttonActivityNewInsertBack)
        buttonActivityNewInsertBack.setOnClickListener {
            insertNewActivity.finish()
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
}