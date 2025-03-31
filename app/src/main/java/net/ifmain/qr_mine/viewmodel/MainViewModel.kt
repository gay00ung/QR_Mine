package net.ifmain.qr_mine.viewmodel

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

@SuppressLint("StaticFieldLeak")
class MainViewModel : ViewModel() {
    var userURL = mutableStateOf("")
    var qrBitmap = mutableStateOf<Bitmap?>(null)
    private var context: Context? = null

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

    fun setContext(context: Context) {
        this.context = context
    }

    fun saveQRCodeToGallery(bitmap: Bitmap): Boolean {
        val appContext = context ?: return false
        val fileName = "QRCode_${System.currentTimeMillis()}.png"
        val mimeType = "image/png"
        val relativePath = "Pictures/QR_Mine"

        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val contentValues = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
                    put(MediaStore.Images.Media.MIME_TYPE, mimeType)
                    put(MediaStore.Images.Media.RELATIVE_PATH, relativePath)
                }

                val uri = appContext.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                uri?.let {
                    appContext.contentResolver.openOutputStream(it)?.use { outputStream ->
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    }
                    true
                } ?: false
            } else {
                val file = File(appContext.getExternalFilesDir(null), fileName)
                val outputStream: OutputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.flush()
                outputStream.close()
                true
            }
        } catch (e: IOException) {
            Log.e("SaveQRCode", "Error saving QR code: ${e.message}")
            false
        }
    }
}
