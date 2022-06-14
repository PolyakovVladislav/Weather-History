package com.dualbot.weatherhistory.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dualbot.weatherhistory.domain.useCases.mainFragment.UseCaseMainFragmentActionRefreshPressed
import com.dualbot.weatherhistory.domain.useCases.storageFragment.UseCaseStorageFragmentActionStoragePressed
import com.dualbot.weatherhistory.repository.RepositoryImplementation
import com.dualbot.weatherhistory.domain.utils.animations.ToolbarAnimations
import com.dualbot.weatherhistory.ui.mainFragment.MainFragmentViewModel
import com.dualbot.weatherhistory.ui.storageFragment.StorageViewModel

sealed class ViewModelFactory {

    data class MainFragmentViewModelFactory(
        val useCaseMainMainFragmentActionRefreshPressed: UseCaseMainFragmentActionRefreshPressed = UseCaseMainFragmentActionRefreshPressed(RepositoryImplementation())
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainFragmentViewModel(useCaseMainMainFragmentActionRefreshPressed) as T
        }
    }

    data class StorageViewModelFactory(
        val useCaseStorageFragmentActionStoragePressed: UseCaseStorageFragmentActionStoragePressed = UseCaseStorageFragmentActionStoragePressed(RepositoryImplementation())
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StorageViewModel(useCaseStorageFragmentActionStoragePressed) as T
        }
    }

    data class MainActivityModelFactory(
        val context: Context,
        val toolbarAnimations: ToolbarAnimations
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainActivityViewModel(context, toolbarAnimations) as T
        }
    }
}