package com.test.newsappjetpack.presentation.ui.dashboard.screens.home.newsdetails

sealed class DetailsEvent {

    object SaveNews : DetailsEvent()
    object DeleteBookMark : DetailsEvent()

}