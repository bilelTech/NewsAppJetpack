package com.test.newsappjetpack.ui.onboarding.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.test.newsappjetpack.R
import com.test.newsappjetpack.ui.onboarding.composables.NewsTextButton
import com.test.newsappjetpack.ui.onboarding.composables.OnBoardingPage
import com.test.newsappjetpack.ui.onboarding.composables.PageIndicator
import com.test.newsappjetpack.ui.onboarding.models.Page
import com.test.newsappjetpack.ui.theme.BlueGray
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    val pages = listOf(
        Page(
            title = stringResource(R.string.page1_title),
            description = stringResource(R.string.page1_desc),
            image = R.drawable.onbordimage1
        ),
        Page(
            title = stringResource(R.string.page2_title),
            description = stringResource(R.string.page2_desc),
            image = R.drawable.onbordimage2
        ),
        Page(
            title = stringResource(R.string.page3_title),
            description = stringResource(R.string.page3_desc),
            image = R.drawable.onbordimage3
        )
    )
    val isSystemInDarkMode = isSystemInDarkTheme()
    val systemUiColor = rememberSystemUiController()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    SideEffect {
        systemUiColor.setSystemBarsColor(
            color = BlueGray,
            darkIcons = isSystemInDarkMode
        )
    }
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // skip Button
            NewsTextButton(
                text = stringResource(R.string.skip),
                onClick = {
                    scope.launch {
                        //Navigate to the main screen and save a value in datastore preferences
                        Toast.makeText(context, "Go To Home Screen", Toast.LENGTH_SHORT).show()
                    }

                }
            )

            // INDICATOR
            PageIndicator(
                modifier = Modifier.width(52.dp),
                pagesSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            // NEXT button
            NewsTextButton(
                text = stringResource(R.string.next),
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage == 2) {
                            //Navigate to the main screen and save a value in datastore preferences
                            Toast.makeText(context, "Go To Home Screen", Toast.LENGTH_SHORT).show()
                        } else {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1
                            )
                        }
                    }
                }
            )


        }
    }
}