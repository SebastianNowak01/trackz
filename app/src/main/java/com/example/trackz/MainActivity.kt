package com.example.trackz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.trackz.components.NavigationDrawerMobile
import com.example.trackz.components.NavigationDrawerTablet
import com.example.trackz.components.WindowInfo
import com.example.trackz.components.rememberWindowInfo
import com.example.trackz.ui.theme.TrackzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrackzTheme {
                val windowInfo = rememberWindowInfo()
                if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Small || windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium) {
                    NavigationDrawerMobile()
                } else {
                    NavigationDrawerTablet()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrackzTheme {
        NavigationDrawerMobile()
    }
}