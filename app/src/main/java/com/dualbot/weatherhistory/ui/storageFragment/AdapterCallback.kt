package com.dualbot.weatherhistory.ui.storageFragment

import com.dualbot.weatherhistory.domain.model.ExpandableTreeWeatherData

interface AdapterCallback {

    fun listChanged(newList :List<ExpandableTreeWeatherData>, oldList :List<ExpandableTreeWeatherData>)
}