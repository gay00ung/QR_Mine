package net.ifmain.qr_mine.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainViewModel : ViewModel() {
    var userURL = mutableStateOf("")
    var qrBitmap = mutableStateOf<Bitmap?>(null)

    private val writer = MultiFormatWriter()
    private val encoder = BarcodeEncoder()

    fun setUserURL(url: String) {
        userURL.value = url
    }

    fun makeQR(): Boolean {
        return try {
            val matrix: BitMatrix =
                writer.encode(userURL.value, BarcodeFormat.QR_CODE, 300, 300)
            qrBitmap.value = encoder.createBitmap(matrix)
            true
        } catch (e: Exception) {
            false
        }
    }
}
