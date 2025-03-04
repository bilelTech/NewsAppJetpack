package com.test.newsappjetpack.presentation.ui.main.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.newsappjetpack.domain.usecases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.test.newsappjetpack.presentation.models.NewsUI
import com.test.newsappjetpack.utils.mapToNewsUI
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {


    private val _newsState: MutableStateFlow<PagingData<NewsUI>> =
        MutableStateFlow(value = PagingData.empty())
    val newsState: StateFlow<PagingData<NewsUI>> get() = _newsState

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            getNewsUseCase.invoke()
                .distinctUntilChanged()
                .map { newsPagingData ->
                    newsPagingData.map {
                        it.mapToNewsUI()
                    }
                }
                .cachedIn(viewModelScope)
                .collect {
                    _newsState.update { _ ->
                        it
                    }
                }
        }
    }
}