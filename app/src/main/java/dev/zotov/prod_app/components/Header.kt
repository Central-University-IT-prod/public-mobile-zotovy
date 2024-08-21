package dev.zotov.prod_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun Header(content: @Composable BoxScope.() -> Unit) {
    val statusBarHeight = with(LocalDensity.current) {
        WindowInsets.statusBars
            .getTop(this)
            .toDp()
    }

    Box(
        modifier = Modifier
            .background(Color(0xFFFFFFFF))
            .drawBehind {
                val borderSize = 1.dp.toPx()
                drawLine(
                    color = Color(0xFFE7E6E8),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderSize,
                )
            }
            .fillMaxWidth()
            .height(56.dp + statusBarHeight)
            .padding(
                top = statusBarHeight,
            ),
        content = content
    )
}