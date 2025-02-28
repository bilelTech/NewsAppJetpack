package com.test.newsappjetpack.presentation.ui.onboarding.models

sealed class OnBoardingEvent {
    object SaveAppEntry : OnBoardingEvent()
}