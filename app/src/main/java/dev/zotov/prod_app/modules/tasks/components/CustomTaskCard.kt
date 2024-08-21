package dev.zotov.prod_app.modules.tasks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.zotov.prod_app.data.models.CustomTask
import dev.zotov.prod_app.components.ScaleTap
import java.time.format.DateTimeFormatter

@Composable
fun CustomTaskCard(task: CustomTask, onClick: () -> Unit) {
    ScaleTap(onClick = onClick) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .border(1.dp, Color(0xFFEAEAEA), shape = RoundedCornerShape(12.dp))
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Column {
                Text(
                    text = task.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color(0xFF1D2939)
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = task.note,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFF475467),
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Text(
                text = task.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color(0xFF667085),
                    textAlign = TextAlign.End,
                ),
            )
        }
    }
}