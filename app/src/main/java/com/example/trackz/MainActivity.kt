package com.example.trackz

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.trackz.components.NavigationDrawerMobile
import com.example.trackz.components.NavigationDrawerTablet
import com.example.trackz.components.WindowInfo
import com.example.trackz.components.rememberWindowInfo
import com.example.trackz.ui.theme.TrackzTheme

/**
 * MainActivity is the entry point of the app. It sets up the splash screen and the main content of the app.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Install splash screen with exit animation.
         */
        installSplashScreen().apply {
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.7f,
                    0.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 800L
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.7f,
                    0.0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 800L
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
