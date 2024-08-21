package dev.zotov.prod_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Button(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    ScaleTap(
        onClick = onClick,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(
                    Color(0xFF444CE7),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Text(
                text,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.White,
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}