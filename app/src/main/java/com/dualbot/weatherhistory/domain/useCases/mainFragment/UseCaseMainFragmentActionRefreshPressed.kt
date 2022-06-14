package com.dualbot.weatherhistory.domain.useCases.mainFragment

import com.dualbot.weatherhistory.domain.Repository
import com.dualbot.weatherhistory.domain.model.Weather
import com.dualbot.weatherhistory.domain.utils.Resource

class UseCaseMainFragmentActionRefreshPressed(private val repository: Repository) {

    suspend fun execute(): Resource<Weather> = repository.getWeather()
}