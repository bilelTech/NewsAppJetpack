package com.test.newsappjetpack.presentation.ui.main.screens.home.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.test.newsappjetpack.R
import com.test.newsappjetpack.presentation.models.NewsUI
import com.test.newsappjetpack.presentation.models.Source
import com.test.newsappjetpack.presentation.ui.theme.NewsAppJetpackTheme

@Composable
fun NewsItemCard(
    modifier: Modifier = Modifier,
    newsUI: NewsUI,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick?.invoke() },

        ) {
        AsyncImage(
            modifier = Modifier
                .size(96.dp)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(newsUI.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = 3.dp)
                .height(96.dp)
        ) {
            Text(
                text = newsUI.title,
                style = MaterialTheme.typography.bodyMedium.copy(),
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = newsUI.source.name,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_date),
                    contentDescription = null,
                    modifier = Modifier.size(11.dp),
                    tint = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = newsUI.publishedAt ?: "",
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsCardPreview() {
    NewsAppJetpackTheme(dynamicColor = false) {
        NewsItemCard(
            newsUI = NewsUI(
                author = "Jean Crishtophe",
                content = "train eytzef sdzyeèt",
                description = "",
                publishedAt = "12/12/2022 12:34",
                title = "Her train broke down. Her phone died. And then she met her Saver in a",
                source = Source("",""),
                url = "",
                urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/11787/production/_124395517_bbcbreakingnewsgraphic.jpg"
            )
        )
    }
}