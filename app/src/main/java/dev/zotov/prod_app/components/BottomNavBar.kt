package dev.zotov.prod_app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.zotov.prod_app.R

@Composable
fun BottomNavBar(currentPage: NavPage, onChange: (page: NavPage) -> Unit) {
    Row(
        modifier = Modifier
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                shadowElevation = 10f
            }
            .background(
                Color(0xFFFFFFFF),
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp,
                )
            )
            .padding(top = 12.dp, bottom = 16.dp)
    ) {
        for (page in NavPage.entries) {
            NavBarItem(page = page, active = currentPage == page) { onChange(page) }
        }
    }
}

@Composable
private fun RowScope.NavBarItem(page: NavPage, active: Boolean, onTap: () -> Unit) {
    ScaleTap(onClick = onTap, modifier = Modifier.weight(1f)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = page.getResId(active)),
                contentDescription = page.getTitle(),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = page.getTitle(),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = active.let {
                        if (it) Color(0xFF444CE7)
                        else Color(0xFF393939)
                    }
                ),
                modifier = Modifier.padding(top = 6.dp)
            )
            Box(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .width(56.dp)
                    .height(2.dp)
                    .background(
                        color = active.let {
                            if (it) Color(0xFF444CE7)
                            else Color(0x00444CE7)
                        },
                        shape = RoundedCornerShape(1.dp)
                    )
            )
        }
    }
}


enum class NavPage {
    Home,
    MyActivities,
    Map,
    Profile;

    fun getResId(active: Boolean): Int {
        return if (active) getActiveResId()
        else getIdleResId()
    }

    private fun getIdleResId(): Int {
        return when (this) {
            Home -> R.drawable.icon_home
            MyActivities -> R.drawable.icon_activities
            Map -> R.drawable.icon_map
            Profile -> R.drawable.icon_profile
        }
    }

    private fun getActiveResId(): Int {
        return when (this) {
            Home -> R.drawable.icon_home_active
            MyActivities -> R.drawable.icon_activities_active
            Map -> R.drawable.icon_map_active
            Profile -> R.drawable.icon_profile_active
        }
    }

    fun getTitle(): String {
        return when (this) {
            Home -> "Главная"
            MyActivities -> "Мой досуг"
            Map -> "Карта"
            Profile -> "Профиль"
        }
    }
}

