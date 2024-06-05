package ic.ac.unpas.jadwalin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ic.ac.unpas.jadwalin.ui.screens.MainScreen
import ic.ac.unpas.jadwalin.ui.theme.JadwalInTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JadwalInTheme {
                // A surface container using the 'background' color from the theme
                MainScreen(onExitClick = {
                    finish()
                })
            }
        }
    }
}