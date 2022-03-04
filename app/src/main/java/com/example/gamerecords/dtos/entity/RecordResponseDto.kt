package com.example.gamerecords.dtos.entity

class RecordResponseDto(
    var id: Int,
    var nickname: String,
    var score: Int,
    var game: String,
    var unixDateTime: Long,
    var countryName: String
)