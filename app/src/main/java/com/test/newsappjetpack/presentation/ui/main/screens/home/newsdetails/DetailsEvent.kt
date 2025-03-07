package com.test.newsappjetpack.presentation.ui.main.screens.home.newsdetails

sealed class DetailsEvent {

    object SaveNews : DetailsEvent()
    object DeleteBookMark : DetailsEvent()

}