package com.example.trackz

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.trackz.components.Title
import com.example.trackz.components.TrackDescription
import com.example.trackz.components.TrackListMobile
import com.example.trackz.components.TrackListTablet
import com.example.trackz.components.Tracks
import com.example.trackz.components.WindowInfo
import com.example.trackz.components.rememberWindowInfo
import com.example.trackz.ui.theme.TrackzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            TrackzTheme {
                var name by remember {
                    mutableStateOf(Tracks.keys.first())
                }
                val windowInfo = rememberWindowInfo()
                // A surface container using the 'background' color from the theme
                if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Column {
                            Title("Trackz")
                            TrackListMobile()
                        }
                    }
                } else {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Row {
                            Column(
                                modifier = Modifier.weight(1f)
                            )
                            {
                                Title("Trackz")
                                TrackListTablet(Tracks, onTrackClick = { selectedTrack ->
                                    name = selectedTrack
                                })
                            }
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                TrackDescription(track = name)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrackzTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        )
            {
                Column{
                    Title("Trackz")
                    TrackListMobile()
                }
            }
    }
}