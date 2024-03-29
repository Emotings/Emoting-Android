package com.emoting.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.emoting.android.navigation.EmotingApp
import com.emoting.designsystem.ui.theme.EmotingAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            EmotingAndroidTheme {
                EmotingApp()
            }
        }
    }
}
