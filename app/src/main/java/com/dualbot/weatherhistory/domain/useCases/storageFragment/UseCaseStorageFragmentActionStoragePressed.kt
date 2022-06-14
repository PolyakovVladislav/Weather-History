package com.dualbot.weatherhistory.domain.useCases.storageFragment

import com.dualbot.weatherhistory.domain.Repository
import com.dualbot.weatherhistory.domain.model.ExpandableTreeWeatherData
import com.dualbot.weatherhistory.domain.utils.Resource

class UseCaseStorageFragmentActionStoragePressed(private val repository: Repository) {

    suspend fun execute(): Resource<List<ExpandableTreeWeatherData>> = repository.loadWeatherFromDB()
}