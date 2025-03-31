package net.ifmain.qr_mine

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.ifmain.qr_mine.ui.screen.IntroScreen
import net.ifmain.qr_mine.ui.screen.MainScreen
import net.ifmain.qr_mine.ui.theme.QR_MineTheme
import net.ifmain.qr_mine.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            QR_MineTheme {
                StartNavigation(
                    viewModel = MainViewModel()
                )
            }
        }
    }
}

@Composable
fun StartNavigation(
    viewModel: MainViewModel
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    var backPressedTime by remember { mutableLongStateOf(0L) }

    NavHost(
        navController = navController,
        startDestination = "intro_screen"
    ) {
        composable("intro_screen") {
            IntroScreen(
                onNavigateToMain = {
                    navController.navigate("main_screen") {
                        popUpTo("intro_screen") { inclusive = true }
                    }
                }
            )
        }
        composable("main_screen") {
            MainScreen(
                viewModel = viewModel
            )
        }
    }

    BackHandler {
        val currentTime = System.currentTimeMillis()
        when (navController.currentDestination?.route) {
            "intro_screen", "main_screen" -> {
                if (currentTime - backPressedTime < 2000) {
                    (context as? ComponentActivity)?.finish()
                } else {
                    Toast.makeText(context, "뒤로가기를 한 번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
                    backPressedTime = currentTime
                }
            }
            else -> {
                navController.popBackStack()
            }
        }
    }
}