package dev.zotov.prod_app.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UnableToLoadText() {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                append("Не удалось загрузить ваше местоположение.\n\n")
            }
            append("Проверьте настройки GPS и разрешите приложению доступ к вашей геолокации.")
        },
        style = TextStyle(
            fontSize = 16.sp,
            color = Color(0xFF1D2939),
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp)
    )
}