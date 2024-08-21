package dev.zotov.prod_app.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ServerErrorText() {
    Text(
        text = "Не удается получить данные с сервера. Проверьте ваше подключение к интернету",
        style = TextStyle(
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF1D2939),
        ),
        modifier = Modifier.padding(20.dp)
    )
}