package net.ifmain.qr_mine.ui.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import net.ifmain.qr_mine.R
import net.ifmain.qr_mine.ui.theme.SkyBlue

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun IntroScreen(
    onNavigateToMain: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val appName = stringResource(R.string.app_name)
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_qr))
    val lottieAnimatable = rememberLottieAnimatable()

    var isLogoVisible by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        delay(500)
        isLogoVisible = true
    }

    LaunchedEffect(composition) {
        lottieAnimatable.animate(
            composition = composition,
            clipSpec = LottieClipSpec.Frame(0, 600),
            initialProgress = 0f
        )
        onNavigateToMain()
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        val screenHeight = maxHeight.value
        val dynamicTitleFontSize = (screenHeight * 0.08).sp

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(visible = isLogoVisible) {
                Text(
                    text = appName,
                    fontSize = dynamicTitleFontSize,
                    textAlign = TextAlign.Center,
                    color = SkyBlue,
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.offset(y = (-50).dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(300.dp),
                progress = { lottieAnimatable.progress }
            )
        }
    }
}