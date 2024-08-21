package dev.zotov.prod_app.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Loader() {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 36.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Spinner(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}