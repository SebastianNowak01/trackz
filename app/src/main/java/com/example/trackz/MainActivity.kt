package com.example.trackz

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.trackz.components.NavigationDrawerMobile
import com.example.trackz.components.NavigationDrawerTablet
import com.example.trackz.components.WindowInfo
import com.example.trackz.components.rememberWindowInfo
import com.example.trackz.ui.theme.TrackzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.6f,
                    0.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 500L
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.6f,
                    0.0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 500L
                zoomY.doOnEnd { screen.remove() }

                zoomY.start()
                zoomX.start()
            }
        }
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