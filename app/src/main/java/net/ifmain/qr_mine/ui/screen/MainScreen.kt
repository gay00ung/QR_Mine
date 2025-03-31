package net.ifmain.qr_mine.ui.screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import net.ifmain.qr_mine.R
import net.ifmain.qr_mine.ui.components.InputSection
import net.ifmain.qr_mine.ui.components.TopBar
import net.ifmain.qr_mine.ui.theme.SkyBlue
import net.ifmain.qr_mine.ui.theme.SoftIvory
import net.ifmain.qr_mine.viewmodel.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val userURL by viewModel.userURL
    val qrBitmap by viewModel.qrBitmap
    val context = LocalContext.current
    LaunchedEffect(context) {
        viewModel.setContext(context)
    }
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopBar()
            }
        },
        content = { innerPadding ->
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
                    onUserURLChange = { viewModel.setUserURL(it) },
                    onSubmit = {
                        if (userURL.isEmpty()) {
                            Toast.makeText(context, "URL을 입력하세요.", Toast.LENGTH_SHORT).show()
                        } else {
                            val success = viewModel.makeQR()
                            if (success) {
                                Toast.makeText(context, "QR 코드 생성 완료!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "QR 코드 생성 실패", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                qrBitmap?.let { bitmap ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = "QR Code",
                            modifier = Modifier
                                .width(200.dp)
                                .height(200.dp)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            onClick = {
                                if (viewModel.saveQRCodeToGallery(bitmap)) {
                                    Toast.makeText(
                                        context,
                                        "QR 코드가 갤러리에 저장되었습니다!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(context, "QR 코드 저장 실패", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.6f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SkyBlue, contentColor = SoftIvory
                            )
                        ) {
                            Text(text = "저장하기")
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(MainViewModel())
}