package com.example.gamerecords.apiworkers

import com.android.volley.Request
import com.example.gamerecords.dtos.request.RecordRequestDto
import com.example.gamerecords.utils.GlobalVariables
import com.google.gson.Gson

class RecordsApiWorker {
    fun getById(id: Int, successCallbackFunction: (String) -> Unit) {
        var httpMethod = Request.Method.GET
        var url = "http://151.248.113.116:8090/records/getById/$id"
        var httpWorker = GlobalVariables.instance.httpWorker
        httpWorker.makeStringRequestWithoutBody(
            httpMethod,
            url,
            successCallbackFunction
        )
    }

    fun getAll(successCallbackFunction: (String) -> Unit) {
        var httpMethod = Request.Method.GET
        var url = "http://151.248.113.116:8090/records/getAll"
        var httpWorker = GlobalVariables.instance.httpWorker
        httpWorker.makeStringRequestWithoutBody(
            httpMethod,
            url,
            successCallbackFunction
        )
    }

    fun deleteById(id: Int, successCallbackFunction: (String) -> Unit) {
        var httpMethod = Request.Method.DELETE
        var url = "http://151.248.113.116:8090/records/deleteById/$id"
        var httpWorker = GlobalVariables.instance.httpWorker
        httpWorker.makeStringRequestWithoutBody(
            httpMethod,
            url,
            successCallbackFunction
        )
    }

    fun insertOne(recordRequestDto: RecordRequestDto, successCallbackFunction: (String) -> Unit) {
        var httpMethod = Request.Method.POST
        var url = "http://151.248.113.116:8090/records/insertOne"
        var recordRequestDtoInJson = Gson().toJson(recordRequestDto)
        var httpWorker = GlobalVariables.instance.httpWorker
        httpWorker.makeStringRequestWithBody(
            httpMethod,
            url,
            successCallbackFunction,
            recordRequestDtoInJson
        )
    }

    fun updateById(id: Int, recordRequestDto: RecordRequestDto, successCallbackFunction: (String) -> Unit) {
        var httpMethod = Request.Method.PUT
        var url = "http://151.248.113.116:8090/records/updateById/$id"
        var recordRequestDtoInJson = Gson().toJson(recordRequestDto)
        var httpWorker = GlobalVariables.instance.httpWorker
        httpWorker.makeStringRequestWithBody(
            httpMethod,
            url,
            successCallbackFunction,
            recordRequestDtoInJson
        )
    }
}