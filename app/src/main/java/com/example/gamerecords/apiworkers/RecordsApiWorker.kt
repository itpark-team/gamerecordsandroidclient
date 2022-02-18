package com.example.gamerecords.apiworkers

import com.android.volley.Request
import com.example.gamerecords.utils.GlobalVariables

class RecordsApiWorker {
    fun getById(id: Int, callbackFunction: (String) -> Unit) {
        val httpMethod = Request.Method.GET
        val url = "http://151.248.113.116:8080/records/getById/$id"
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

    fun deleteById(id: Int, callbackFunction: (String) -> Unit) {
        val httpMethod = Request.Method.DELETE
        val url = "http://151.248.113.116:8080/records/deleteById/$id"
        val httpWorker = GlobalVariables.instance.httpWorker
        httpWorker.makeStringRequestWithoutBody(
            httpMethod,
            url,
            callbackFunction
        )
    }
}