package com.dualbot.weatherhistory.ui.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dualbot.weatherhistory.domain.model.Weather
import com.dualbot.weatherhistory.domain.useCases.mainFragment.UseCaseMainFragmentActionRefreshPressed
import com.dualbot.weatherhistory.domain.utils.Resource
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainFragmentViewModel(
    private val useCaseMainMainFragmentActionRefreshPressed: UseCaseMainFragmentActionRefreshPressed,
) : ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job + CoroutineExceptionHandler {_, e -> throw e}

    private val _weather = MutableLiveData<Resource<Weather>>()
    val weather: LiveData<Resource<Weather>> = _weather

    fun loadWeather() {
            launch {
                withContext(Dispatchers.Main) { _weather.value = Resource.loading(null) }
                val result = useCaseMainMainFragmentActionRefreshPressed.execute()
                withContext(Dispatchers.Main) { _weather.value = result }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }
}