package net.ifmain.qr_mine.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import net.ifmain.qr_mine.R
import net.ifmain.qr_mine.ui.theme.SkyBlue

@Composable
fun TopBar() {
    val appName = stringResource(R.string.app_name)

    Text(
        text = appName,
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center,
        color = SkyBlue,
        fontWeight = FontWeight.Bold
    )
}