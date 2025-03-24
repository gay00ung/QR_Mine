package net.ifmain.qr_mine.ui.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import net.ifmain.qr_mine.ui.theme.SkyBlue

@Composable
fun TopBar() {
    Text(
        text = "QR Mine",
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center,
        color = SkyBlue,
        fontWeight = FontWeight.Bold
    )
}