package dev.zotov.prod_app.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFFFFFF),
    onPrimary = Color(0xFF000000),
    background = Color(0xFFF2F4F5),
)

@Composable
fun Prod_appTheme(content: @Composable () -> Unit) {
    MaterialTheme(
      colorScheme = LightColorScheme,
      content = content
    )
}