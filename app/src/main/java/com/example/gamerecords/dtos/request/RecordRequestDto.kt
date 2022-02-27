package com.example.gamerecords.dtos.request

class RecordRequestDto(
    var nickname: String,
    var score: Int,
    var game: String,
    var unixDateTime: Long,
    var countryId: Int
)