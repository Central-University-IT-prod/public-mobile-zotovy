package dev.zotov.prod_app.modules.tasks.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.zotov.prod_app.R
import dev.zotov.prod_app.components.Header
import dev.zotov.prod_app.components.ScaleTap

@Composable
fun TaskListHeader(onAddTap: () -> Unit) {
    Header {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.width(56.dp).height(56.dp))

            Text(
                text = "Мои активности",
                style = TextStyle(
                    color = Color(0xFF1D2939),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                )
            )

            ScaleTap(
                onClick = onAddTap,
                modifier = Modifier
                    .width(56.dp)
                    .height(56.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_add),
                    contentDescription = "add",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(24.dp),
                )
            }
        }
    }
}