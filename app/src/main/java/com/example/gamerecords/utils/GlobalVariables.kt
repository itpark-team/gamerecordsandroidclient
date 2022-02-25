package com.example.gamerecords.utils

import android.content.Context
import com.example.gamerecords.dtos.entity.RecordResponseDto

class GlobalVariables {
    companion object {
        val instance = GlobalVariables()
    }

    lateinit var applicationContext: Context
    lateinit var httpWorker: HttpWorker
    lateinit var currentRecord: RecordResponseDto
}