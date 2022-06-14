package com.dualbot.weatherhistory.domain.model

data class Weather(
    val date: String?,
    val weatherCharacterSvgCode: String?,
    val weatherCharacter: String?,
    val temperature: String?,
    val comfortTemperature: String?,
    val windSpeed: String?,
    val windDirection: String?,
    val pressure: String?,
    val wetness: String?,
    val gmActivity: String?,
    val waterTemperature: String?,
    val sunSummery: String?,
    val sunRise: String?,
    val sunFall: String?,
    val moonSvgCode: String?,
    val moonSummery: String?,
    val moonRise: String?,
    val moonFall: String?,
) {
}