package com.example.gamerecords.apiworkers

import com.android.volley.Request
import com.example.gamerecords.utils.GlobalVariables

class CountriesApiWorker {
    fun getAll(successCallbackFunction: (String) -> Unit) {
        val httpMethod = Request.Method.GET
        val url = "http://151.248.113.116:8090/countries/getAll"
        val httpWorker = GlobalVariables.instance.httpWorker
        httpWorker.makeStringRequestWithoutBody(
            httpMethod,
            url,
            successCallbackFunction
        )
    }
}