package com.test.newsappjetpack.presentation.ui.onboarding.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.newsappjetpack.domain.usecases.AppEntryUseCases
import com.test.newsappjetpack.domain.usecases.SaveAppEntryUseCase
import com.test.newsappjetpack.presentation.ui.onboarding.models.OnBoardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel
@Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntryUseCase.invoke()
        }
    }
}