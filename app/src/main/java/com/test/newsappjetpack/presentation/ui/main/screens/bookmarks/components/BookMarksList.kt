package com.test.newsappjetpack.presentation.ui.main.screens.bookmarks.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.newsappjetpack.presentation.models.NewsUI
import com.test.newsappjetpack.presentation.ui.main.screens.home.components.NewsItemCard

@Composable
fun BookMarksList(
    modifier: Modifier = Modifier,
    news: List<NewsUI>,
    onClick:(NewsUI) -> Unit
) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(all = 6.dp)
        ) {
            items(
                count = news.size,
            ) {
                news[it].let { newsItem ->
                    NewsItemCard(newsUI = newsItem, onClick = {onClick(newsItem)})
                }
            }
        }
}