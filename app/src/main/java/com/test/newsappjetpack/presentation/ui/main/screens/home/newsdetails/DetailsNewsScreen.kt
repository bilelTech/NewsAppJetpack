package com.test.newsappjetpack.presentation.ui.main.screens.home.newsdetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.test.newsappjetpack.presentation.models.NewsUI

@Composable
fun DetailsNewsScreen(news: NewsUI,
                      navigateUp: () -> Unit) {
    Text(text = "Details News Screen ${news.title}")
}