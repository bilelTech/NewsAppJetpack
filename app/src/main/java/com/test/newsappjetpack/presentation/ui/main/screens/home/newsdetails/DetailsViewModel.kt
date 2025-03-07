package com.test.newsappjetpack.presentation.ui.main.screens.home.newsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.newsappjetpack.data.utils.Utils.convertStringToLocalDateTime
import com.test.newsappjetpack.domain.models.News
import com.test.newsappjetpack.domain.models.Source
import com.test.newsappjetpack.domain.usecases.AddBookMarksUseCase
import com.test.newsappjetpack.domain.usecases.CheckBookMarksUseCase
import com.test.newsappjetpack.domain.usecases.RemoveBookMarksUseCase
import com.test.newsappjetpack.presentation.models.NewsUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val addBookMarksUseCase: AddBookMarksUseCase,
    private val checkBookMarksUseCase: CheckBookMarksUseCase,
    private val removeBookMarksUseCase: RemoveBookMarksUseCase
) :
    ViewModel() {

    private val _isBookMarkState: MutableStateFlow<Boolean> =
        MutableStateFlow(value = false)
    val isBookMarkState: StateFlow<Boolean> get() = _isBookMarkState


    fun addBookMark(newsUI: NewsUI) {
        viewModelScope.launch {
            addBookMarksUseCase.invoke(
                News(
                    author = newsUI.author,
                    content = newsUI.content,
                    description = newsUI.description,
                    publishedAt = convertStringToLocalDateTime(newsUI.publishedAt),
                    source = Source(id = newsUI.source.id, name = newsUI.source.name),
                    title = newsUI.title,
                    url = newsUI.url,
                    urlToImage = newsUI.urlToImage
                )
            )
        }
    }

    fun deleteBookMark(newsUI: NewsUI) {
        viewModelScope.launch {
            removeBookMarksUseCase.invoke(
                News(
                    author = newsUI.author,
                    content = newsUI.content,
                    description = newsUI.description,
                    publishedAt = convertStringToLocalDateTime(newsUI.publishedAt),
                    source = Source(id = newsUI.source.id, name = newsUI.source.name),
                    title = newsUI.title,
                    url = newsUI.url,
                    urlToImage = newsUI.urlToImage
                )
            )
        }
    }

    fun checkBookMark(newsUI: NewsUI) {
        viewModelScope.launch {
            checkBookMarksUseCase.invoke(newsUI.url)
                .distinctUntilChanged()
                .collect {
                    _isBookMarkState.update { _ ->
                        it
                    }
                }

        }
    }
}