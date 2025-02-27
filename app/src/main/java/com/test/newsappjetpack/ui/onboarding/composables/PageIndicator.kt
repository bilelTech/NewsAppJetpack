package com.test.newsappjetpack.ui.onboarding.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.newsappjetpack.R
import com.test.newsappjetpack.ui.onboarding.models.Page
import com.test.newsappjetpack.ui.theme.BlueGray
import com.test.newsappjetpack.ui.theme.NewsAppJetpackTheme
import com.test.newsappjetpack.ui.theme.WhiteGray


@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pagesSize: Int,
    selectedPage: Int,
    selectedColor: Color = BlueGray,
    unselectedColor: Color = WhiteGray
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(times = pagesSize) { page ->
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun PageIndicatorPreview() {
    NewsAppJetpackTheme {
        val pages = listOf(repeat(3) { i ->
            Page(
                title = "title$i",
                description = "description$i",
                image = R.drawable.onbordimage1
            )
        })

        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        PageIndicator(
            modifier = Modifier.width(52.dp),
            pagesSize = pages.size,
            selectedPage = pagerState.currentPage
        )
    }

}