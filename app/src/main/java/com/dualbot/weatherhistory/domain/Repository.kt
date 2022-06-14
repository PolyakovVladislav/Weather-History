package com.dualbot.weatherhistory.domain

import com.dualbot.weatherhistory.domain.model.ExpandableTreeWeatherData
import com.dualbot.weatherhistory.domain.model.Weather
import com.dualbot.weatherhistory.domain.utils.Resource

interface Repository {

    suspend fun getWeather() : Resource<Weather>
    suspend fun loadWeatherFromDB(): Resource<List<ExpandableTreeWeatherData>>
}