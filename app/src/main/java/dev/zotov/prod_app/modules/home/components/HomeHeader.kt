package dev.zotov.prod_app.modules.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import dev.zotov.prod_app.modules.home.HomeViewModel
import dev.zotov.prod_app.modules.shared.LocationState

@Composable
fun HomeHeader(viewModel: HomeViewModel) {
    val currentLocation by viewModel.currentLocation.collectAsState()

    Header {
        Row(
            modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (currentLocation is LocationState.Idle && (currentLocation as LocationState.Idle).locationName != null) {
                Image(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = (currentLocation as LocationState.Idle).locationName!!,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF1D2939)
                    )
                )
            }
        }
    }


}