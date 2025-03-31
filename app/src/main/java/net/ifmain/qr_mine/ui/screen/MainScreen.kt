package net.ifmain.qr_mine.ui.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.ifmain.qr_mine.R
import net.ifmain.qr_mine.ui.components.InputSection
import net.ifmain.qr_mine.ui.components.TopBar
import net.ifmain.qr_mine.viewmodel.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    var userURL by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopBar()
            }

        },
        content = {innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                InputSection(
                    userURL = userURL,
                    onUserURLChange = { userURL = it },
                    onSubmit = {
                        Toast.makeText(context, "QR 코드 생성: $userURL", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(MainViewModel())
}