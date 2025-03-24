package net.ifmain.qr_mine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

    NavHost(
        navController = navController,
        startDestination = "main_screen"
    ) {
        composable("main_screen") {
            MainScreen(
                viewModel = viewModel
            )
        }
    }
}