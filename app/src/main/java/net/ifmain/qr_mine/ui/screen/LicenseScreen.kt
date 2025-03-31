package net.ifmain.qr_mine.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.ifmain.qr_mine.ui.theme.SkyBlue

@Composable
fun LicenseScreen() {
    val licenses = listOf(
        LicenseItem("AndroidX Core KTX", "Apache License 2.0"),
        LicenseItem("AndroidX Lifecycle Runtime KTX", "Apache License 2.0"),
        LicenseItem("AndroidX Activity Compose", "Apache License 2.0"),
        LicenseItem("AndroidX Compose BOM", "Apache License 2.0"),
        LicenseItem("AndroidX UI", "Apache License 2.0"),
        LicenseItem("AndroidX UI Graphics", "Apache License 2.0"),
        LicenseItem("AndroidX UI Tooling Preview", "Apache License 2.0"),
        LicenseItem("AndroidX Material3", "Apache License 2.0"),
        LicenseItem("AndroidX Navigation Compose", "Apache License 2.0"),
        LicenseItem("JUnit", "Eclipse Public License 1.0"),
        LicenseItem("AndroidX JUnit", "Apache License 2.0"),
        LicenseItem("AndroidX Espresso Core", "Apache License 2.0"),
        LicenseItem("AndroidX UI Test JUnit4", "Apache License 2.0"),
        LicenseItem("AndroidX UI Tooling", "Apache License 2.0"),
        LicenseItem("AndroidX UI Test Manifest", "Apache License 2.0"),
        LicenseItem("ZXing Android Embedded", "Apache License 2.0"),
        LicenseItem("Lottie Compose", "Apache License 2.0")
    )

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "License",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    color = SkyBlue,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
            ) {
                items(licenses) { license ->
                    LicenseRow(license)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    )
}

@Composable
fun LicenseRow(license: LicenseItem) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = license.libraryName,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "License: ${license.licenseType}",
            style = MaterialTheme.typography.bodySmall,
            color = DarkGray
        )
    }
}

data class LicenseItem(val libraryName: String, val licenseType: String)
