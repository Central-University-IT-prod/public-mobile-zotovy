package dev.zotov.prod_app.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Spinner(modifier: Modifier = Modifier, size: Dp = 36.dp) {
    val transition = rememberInfiniteTransition(label = "Spinner")

    val arcStartAngle by transition.animateValue(
        0,
        360,
        Int.VectorConverter,
        infiniteRepeatable(
            animation = tween(
                durationMillis = 1100,
                easing = LinearEasing
            )
        ),
        label = "arc-spinner"
    )

    val stroke = with(LocalDensity.current) {
        Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
    }

    Canvas(
        modifier
            .progressSemantics()
            .size(size)
            .padding(1.5.dp)) {
        drawCircle(Color(0xFFC7D7FE), style = stroke)

        drawArc(
            Color(0xFF3538CD),
            startAngle = arcStartAngle.toFloat() - 90,
            sweepAngle = 90f,
            useCenter = false,
            style = stroke
        )
    }
}