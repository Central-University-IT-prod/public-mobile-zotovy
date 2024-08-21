package dev.zotov.prod_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTextField(
    onChange: (value: LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    initialSelectedDateMillis: Long? = null
) {
    var openDateDialog by remember { mutableStateOf(false) }
    val state = rememberDatePickerState(initialSelectedDateMillis = initialSelectedDateMillis)

    if (openDateDialog) {
        DatePickerDialog(
            onDismissRequest = { openDateDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDateDialog = false
                        if (state.selectedDateMillis != null) {
                            val date = Instant.ofEpochMilli(state.selectedDateMillis!!)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                            onChange(date)
                        }
                    }
                ) {
                    Text(text = "Выбрать", style = TextStyle(color = Color.Black))
                }
            },
        ) {
            DatePicker(state = state)
        }
    }


    val date = if (state.selectedDateMillis != null) formatter.format(
        Instant.ofEpochMilli(state.selectedDateMillis!!).atZone(ZoneId.systemDefault())
            .toLocalDate()
    ) else "Выберите дату"

    Box(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .height(56.dp)
            .fillMaxWidth()
            .clickable {
                openDateDialog = true
            }
            .border(
                width = 1.dp,
                color = Color(0xFFD0D5DD),
                shape = RoundedCornerShape(8.dp),
            )
            .padding(vertical = 10.dp, horizontal = 14.dp)
    ) {
        Text(
            text = date,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFF101323),
            ),
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
}