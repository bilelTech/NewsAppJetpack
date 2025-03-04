package com.test.newsappjetpack.presentation.ui.main.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.test.newsappjetpack.presentation.models.NewsUI

@Composable
fun NewsList(
    modifier: Modifier = Modifier,
    news: LazyPagingItems<NewsUI>,
    onClick:(NewsUI) -> Unit
) {
    val handlePagingResult = handlePagingResult(news)


    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(all = 6.dp)
        ) {
            items(
                count = news.itemCount,
            ) {
                news[it]?.let { newsItem ->
                    NewsItemCard(newsUI = newsItem, onClick = {onClick(newsItem)})
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(news: LazyPagingItems<NewsUI>): Boolean {
    val loadState = news.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            ErrorScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)){
        repeat(10){
            NewsCardShimmerEffect(
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}