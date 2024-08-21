package dev.zotov.prod_app.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import dev.zotov.prod_app.R

@Composable
fun TextField(
    text: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    multiline: Boolean = false,
) {
    OutlinedTextField(
        value = text,
        onValueChange = onChange,
        placeholder = {
            if (placeholder != null) {
                Text(placeholder)
            }
        },
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color(0xFFD0D5DD),
            focusedContainerColor = Color.White,
            focusedIndicatorColor = Color(0xFF444CE7),
            cursorColor = Color(0xFF444CE7),
        ),
        minLines =  if (multiline) 3 else 1,
        maxLines = if (multiline) 8 else 1,
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun PasswordTextField(
    text: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
) {
    var showPassword by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = onChange,
        placeholder = {
            if (placeholder != null) {
                Text(placeholder)
            }
        },
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color(0xFFD0D5DD),
            focusedContainerColor = Color.White,
            focusedIndicatorColor = Color(0xFF444CE7),
            cursorColor = Color(0xFF444CE7),
        ),
        visualTransformation = if (!showPassword) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            ScaleTap(onClick = { showPassword = !showPassword }) {
                AnimatedContent(targetState = showPassword, label = "") {
                    if (it) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_eye_slash),
                            contentDescription = "show",
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.icon_eye),
                            contentDescription = "hide",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        },
        maxLines = 1,
        shape = RoundedCornerShape(8.dp)
    )
}