package dev.zotov.prod_app.modules.venue.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import dev.zotov.prod_app.components.ScaleTap
import dev.zotov.prod_app.utils.shadow

@Composable
fun VenuePhotos(bestPhoto: String?, photos: List<String>) {
    Column(modifier = Modifier.padding(top = 20.dp)) {
        if (bestPhoto != null) {
            MainPhoto(bestPhoto)
        }

        if (photos.isNotEmpty()) {
            Spacer(modifier = Modifier.height(20.dp))
            Carousel(photos)
        }
    }
}

@Composable
private fun MainPhoto(url: String) {
    var showDialog by remember { mutableStateOf(false) }

    ScaleTap(onClick = { showDialog = true }) {
        Image(
            painter = rememberAsyncImagePainter(url),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(250.dp)
                .shadow(
                    offsetY = 8.dp,
                    shadowBlurRadius = 26.dp,
                    color = Color(0xFF0E1E2E),
                    alpha = 0.25f,
                    cornersRadius = 20.dp,
                )
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFD0D5DD))
        )
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Image(
                painter = rememberAsyncImagePainter(url),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
            )
        }
    }
}

@Composable
private fun Carousel(photos: List<String>) {
    val scrollState = rememberScrollState()

    Row(modifier = Modifier.horizontalScroll(scrollState)) {
        Spacer(modifier = Modifier.width(20.dp))

        for (photo in photos.drop(1)) {
            SmallCard(photo)
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}

@Composable
private fun SmallCard(photo: String) {
    var showDialog by remember { mutableStateOf(false) }

    ScaleTap(onClick = { showDialog = true }) {
        Image(
            painter = rememberAsyncImagePainter(photo),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(120.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFD0D5DD))
        )
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Image(
                painter = rememberAsyncImagePainter(photo),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
            )
        }
    }
}