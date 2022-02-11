package com.example.gamerecords.apiworkers

import com.android.volley.Request
import com.example.gamerecords.utils.GlobalVariables

class RecordsApiWorker {
    fun getById(callbackFunction: (String) -> Unit) {
        val httpMethod = Request.Method.GET
        val url = "http://151.248.113.116:8080/records/getById/3"
        val httpWorker = GlobalVariables.instance.httpWorker
        httpWorker.makeStringRequestWithoutBody(
            httpMethod,
            url,
            callbackFunction
        )
    }

    fun getAll(callbackFunction: (String) -> Unit) {
        val httpMethod = Request.Method.GET
        val url = "http://151.248.113.116:8080/records/getAll"
        val httpWorker = GlobalVariables.instance.httpWorker
        httpWorker.makeStringRequestWithoutBody(
            httpMethod,
            url,
            callbackFunction
        )
    }
}