package com.dualbot.weatherhistory.ui.storageFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dualbot.weatherhistory.domain.model.ExpandableTreeWeatherData
import com.dualbot.weatherhistory.domain.useCases.storageFragment.UseCaseStorageFragmentActionStoragePressed
import com.dualbot.weatherhistory.domain.utils.Resource
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class StorageViewModel(
    private val useCaseStorageFragmentActionStoragePressed: UseCaseStorageFragmentActionStoragePressed,
    ): ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job + CoroutineExceptionHandler { _, e -> throw e}

    private val _weatherList = MutableLiveData<Resource<List<ExpandableTreeWeatherData>>>()
    val weatherList: LiveData<Resource<List<ExpandableTreeWeatherData>>> = _weatherList

    init {
        loadData()
    }

    fun loadData() = launch {
        val result = useCaseStorageFragmentActionStoragePressed.execute()
        withContext(Dispatchers.Main) { _weatherList.value = result }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }
}