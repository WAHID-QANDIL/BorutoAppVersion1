package org.wahid.borutoappversion1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.wahid.borutoappversion1.navigation.SetUpNavGraph
import org.wahid.borutoappversion1.ui.theme.BorutoAppVersion1Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BorutoAppVersion1Theme {
                val navHostController = rememberNavController()
                SetUpNavGraph(navHostController)

            }
        }

    }
}