package com.example.gamerecords.dtos.entity

class RecordResponseDto(
    var id: Int,
    var nickname: String,
    var score: Int,
    var game: String,
    var unixDateTime: Long,
    var countryName: String
) {
    fun convertToString(): String {
        return "Nickame = $nickname \n" +
                "Score = $score \n" +
                "Game = $game \n" +
                "UnixDateTime = $unixDateTime \n" +
                "CountryName = $countryName";
    }
}