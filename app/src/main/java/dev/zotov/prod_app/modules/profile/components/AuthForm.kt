package dev.zotov.prod_app.modules.profile.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.zotov.prod_app.components.Button
import dev.zotov.prod_app.components.PasswordTextField
import dev.zotov.prod_app.components.TextField
import dev.zotov.prod_app.modules.profile.ProfileViewModel

@Composable
fun AuthForm(profileViewModel: ProfileViewModel) {
    var isSignup by remember { mutableStateOf(false) }
    val loginError = profileViewModel.loginError.collectAsState().value
    val signupError = profileViewModel.signupError.collectAsState().value

    AnimatedContent(targetState = isSignup, label = "") {
        if (it) {
            SignupForm(
                onSubmit = profileViewModel::signup,
                onLogin = { isSignup = false },
                error = signupError,
            )
        } else {
            LoginForm(
                onSubmit = profileViewModel::login,
                onSignup = { isSignup = true },
                error = loginError,
            )
        }
    }
}

@Composable
private fun LoginForm(onSubmit: (String, String) -> Unit, onSignup: () -> Unit, error: String?) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Вход",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF101323),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            text = username,
            onChange = { username = it },
            placeholder = "Username",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        PasswordTextField(
            text = password,
            onChange = { password = it },
            placeholder = "Пароль",
            modifier = Modifier.padding(bottom = 16.dp),
        )

        if (error != null) {
            Text(
                text = error,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFFD92D20),
                ),
                modifier = Modifier.padding(bottom = 16.dp),
            )
        }

        Button(
            text = "Войти",
            onClick = {
                onSubmit(username, password)
            },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ClickableText(
            text = buildAnnotatedString {
                append("Нет аккаунта? ")
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF444CE7),
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Зарегистрируйтесь")
                }
            },
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFF475467),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.padding(bottom = 20.dp),
            onClick = { onSignup() },
        )
    }

}

@Composable
private fun SignupForm(onSubmit: (String) -> Unit, onLogin: () -> Unit, error: String?) {
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Регистрация",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF101323),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        PasswordTextField(
            text = password,
            onChange = { password = it },
            placeholder = "Пароль",
            modifier = Modifier.padding(bottom = 16.dp),
        )

        if (error != null) {
            Text(
                text = error,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFFD92D20),
                ),
                modifier = Modifier.padding(bottom = 16.dp),
            )
        }

        Button(
            text = "Создать аккаунт",
            onClick = { onSubmit(password) },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ClickableText(
            text = buildAnnotatedString {
                append("Есть аккаунт? ")
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF444CE7),
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Войти")
                }
            },
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFF475467),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.padding(bottom = 20.dp),
            onClick = { onLogin() },
        )
    }

}