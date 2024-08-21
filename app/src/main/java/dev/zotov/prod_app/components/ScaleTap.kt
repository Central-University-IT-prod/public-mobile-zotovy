package dev.zotov.prod_app.components

import android.view.MotionEvent
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInteropFilter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScaleTap(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(
        if (selected.value) 0.97f else 1f,
        label = "",
        animationSpec = tween(durationMillis = 250, easing = Ease)
    )

    Box(
        modifier = modifier
            .scale(scale.value)
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        selected.value = true
                    }

                    MotionEvent.ACTION_UP -> {
                        onClick()
                        selected.value = false
                    }
                }
                true
            },
        content = content
    )
}
