package dev.zotov.prod_app.modules.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import dev.zotov.prod_app.data.models.User

@Composable
fun ProfileView(user: User, onLogout: () -> Unit) {
    Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 80.dp)) {
        Row(
            modifier = Modifier.padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(user.profilePicture),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(56.dp)
            )

            Column(modifier = Modifier.padding(start = 20.dp)) {
                Text(
                    text = user.name,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF344054)
                    ),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "@${user.username}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFF344054)
                    ),
                    modifier = Modifier.padding(bottom = 0.dp)
                )
            }
        }

        Property(
            label = "Email",
            value = user.email,
        )

        Property(
            label = "Телефон",
            value = user.phone,
        )

        Property(
            label = "Пол",
            value = if (user.gender == "female") "Женский" else "Мужской",
        )

        Logout(onClick = onLogout)
    }
}

@Composable
private fun Property(label: String, value: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF344054)
            ),
        )

        Text(
            text = value,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFF1D2939)
            ),
        )
    }
}

@Composable
private fun Logout(onClick: () -> Unit) {
    ClickableText(
        text = AnnotatedString("Выйти из аккаунта"),
        onClick = { onClick() },
        style = TextStyle(fontSize = 16.sp, color = Color(0xFFF04438))
    )
}