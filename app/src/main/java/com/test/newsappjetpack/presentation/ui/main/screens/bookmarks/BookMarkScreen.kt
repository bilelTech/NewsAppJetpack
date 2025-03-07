package com.test.newsappjetpack.presentation.ui.main.screens.bookmarks

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.test.newsappjetpack.presentation.models.NewsUI
import com.test.newsappjetpack.presentation.models.Source
import com.test.newsappjetpack.presentation.ui.main.screens.bookmarks.components.BookMarksList

@Composable
fun BookMarkScreen(
    news: List<NewsUI>,
    navigateToDetails: (NewsUI) -> Unit
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    var reloadKey by remember { mutableStateOf(0) }

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            reloadKey++  // Force recomposition
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .statusBarsPadding()
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        BookMarksList(
            modifier = Modifier.padding(horizontal = 24.dp),
            news = news,
            onClick = {
                //TODO Navigate to Details Screen
                navigateToDetails(it)
            }
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BookMarkScreenPreview() {
    val mockNewsList = listOf(
        NewsUI(
            author = "Justin Cristophe",
            title = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
            source = Source("", ""),
            description = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
            content = "We use cookies and data to Deliver and maintain Google services Track outages and protect against spam, fraud, and abuse Measure audience engagement and site statistics to unde… [+1131 chars]",
            publishedAt = "2023-06-16 22:24:33",
            url = "https://consent.google.com/ml?continue=https://news.google.com/rss/articles/CBMiaWh0dHBzOi8vY3J5cHRvc2F1cnVzLnRlY2gvY29pbmJhc2Utc2F5cy1hcHBsZS1ibG9ja2VkLWl0cy1sYXN0LWFwcC1yZWxlYXNlLW9uLW5mdHMtaW4td2FsbGV0LXJldXRlcnMtY29tL9IBAA?oc%3D5&gl=FR&hl=en-US&cm=2&pc=n&src=1",
            urlToImage = "https://media.wired.com/photos/6495d5e893ba5cd8bbdc95af/191:100/w_1280,c_limit/The-EU-Rules-Phone-Batteries-Must-Be-Replaceable-Gear-2BE6PRN.jpg"
        ), NewsUI(
            author = "Justin Cristophe",
            title = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
            source = Source("", ""),
            description = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
            content = "We use cookies and data to Deliver and maintain Google services Track outages and protect against spam, fraud, and abuse Measure audience engagement and site statistics to unde… [+1131 chars]",
            publishedAt = "2023-06-16 22:24:33",
            url = "https://consent.google.com/ml?continue=https://news.google.com/rss/articles/CBMiaWh0dHBzOi8vY3J5cHRvc2F1cnVzLnRlY2gvY29pbmJhc2Utc2F5cy1hcHBsZS1ibG9ja2VkLWl0cy1sYXN0LWFwcC1yZWxlYXNlLW9uLW5mdHMtaW4td2FsbGV0LXJldXRlcnMtY29tL9IBAA?oc%3D5&gl=FR&hl=en-US&cm=2&pc=n&src=1",
            urlToImage = "https://media.wired.com/photos/6495d5e893ba5cd8bbdc95af/191:100/w_1280,c_limit/The-EU-Rules-Phone-Batteries-Must-Be-Replaceable-Gear-2BE6PRN.jpg"
        )
    )
    BookMarkScreen(mockNewsList, {

    })
}