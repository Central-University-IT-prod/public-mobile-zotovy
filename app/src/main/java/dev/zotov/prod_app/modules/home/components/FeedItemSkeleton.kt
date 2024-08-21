package dev.zotov.prod_app.modules.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FeedItemSkeleton() {
    val modifier =
        Modifier.background(Color.Black.copy(alpha = 0.12f), shape = RoundedCornerShape(4.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(7.dp)
            .height(94.dp),
    ) {
        Box(
            modifier = modifier
                .width(120.dp)
                .fillMaxHeight()
        )
        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier
                    .width(126.dp)
                    .height(22.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier
                    .width(200.dp)
                    .height(18.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier
                    .width(64.dp)
                    .height(18.dp)
            )
        }
    }
}