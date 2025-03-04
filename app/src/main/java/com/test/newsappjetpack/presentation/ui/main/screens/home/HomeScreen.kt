package com.test.newsappjetpack.presentation.ui.main.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.test.newsappjetpack.presentation.models.NewsUI
import com.test.newsappjetpack.presentation.ui.main.screens.home.components.NewsList
import com.test.newsappjetpack.presentation.ui.main.screens.home.components.SearchBar


@Composable
fun HomeScreen(
    news: LazyPagingItems<NewsUI>,
    navigateToDetails: (NewsUI) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .statusBarsPadding()
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        SearchBar(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = {

            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        NewsList(
            modifier = Modifier.padding(horizontal = 24.dp),
            news = news,
            onClick = {
                //TODO Navigate to Details Screen
                navigateToDetails(it)
            }
        )
    }
}