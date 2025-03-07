package com.test.newsappjetpack.presentation.ui.main.screens.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.newsappjetpack.domain.usecases.GetBookMarksUseCase
import com.test.newsappjetpack.presentation.models.NewsUI
import com.test.newsappjetpack.utils.mapToNewsUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(private val getBookMarksUseCase: GetBookMarksUseCase): ViewModel() {


    private val _newsState: MutableStateFlow<List<NewsUI>> =
        MutableStateFlow(value = emptyList())
    val newsState: StateFlow<List<NewsUI>> get() = _newsState

    fun getBookMarks() {
        viewModelScope.launch {
            getBookMarksUseCase.invoke()
                .distinctUntilChanged()
                .map { newsPagingData ->
                    newsPagingData.map {
                        it.mapToNewsUI()
                    }
                }
                .collect {
                    _newsState.update { _ ->
                        it
                    }
                }
        }
    }
}