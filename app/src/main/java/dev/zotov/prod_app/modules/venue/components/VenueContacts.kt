package dev.zotov.prod_app.modules.venue.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.zotov.prod_app.data.models.VenueContacts
import dev.zotov.prod_app.R

@Composable
fun VenueContactsView(
    contacts: VenueContacts,
    onPhoneClick: () -> Unit,
    onFacebookClick: () -> Unit,
    onTwitterClick: () -> Unit,
    onInstagramClick: () -> Unit, // Запрещено на территории РФ))))
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 28.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.icon_call),
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )

        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = "Контакты",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF344054),
                ),
                modifier = Modifier.padding(top = 4.dp)
            )

            if (contacts.formattedPhone != null) {
                ClickableText(
                    text = AnnotatedString(contacts.formattedPhone!!),
                    onClick = { onPhoneClick() },
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        color = Color(0xFF3538CD),
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            if (contacts.facebook != null) {
                ClickableText(
                    text = AnnotatedString("Facebook"),
                    onClick = { onFacebookClick() },
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        color = Color(0xFF3538CD),
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            if (contacts.twitter != null) {
                ClickableText(
                    text = AnnotatedString("Twitter"),
                    onClick = { onTwitterClick() },
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        color = Color(0xFF3538CD),
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            if (contacts.instagram != null) {
                ClickableText(
                    text = AnnotatedString("Instagram"),
                    onClick = { onInstagramClick() },
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        color = Color(0xFF3538CD),
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}