package com.test.newsappjetpack.presentation.ui.onboarding.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.test.newsappjetpack.R

@Composable
fun NewsTextButton(
    text: String,
    color: Color = colorResource(R.color.color_button_onboarding),
    onClick: () -> Unit,
) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            fontSize = 16.sp,
            color = color
        )
    }
}