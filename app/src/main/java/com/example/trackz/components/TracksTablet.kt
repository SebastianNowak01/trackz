package com.example.trackz.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreenTablet(){
    var name by remember {
        mutableStateOf(Tracks.keys.first())
    }
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
                TrackDescriptionTablet(track = name)
            }
        }
    }
}

        @Composable
fun TrackListTablet(tracks: HashMap<String, Track> = Tracks, onTrackClick: (String) -> Unit){
    LazyColumn {
        items(tracks.keys.toList()) { track ->
            Text(
                text = track,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable { onTrackClick(track) }
            )
            Divider(thickness = 2.dp)
        }
    }
}

@Composable
fun TrackDescriptionTablet(track: String){
    Column {
        Text(text = track,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 50.sp,
            modifier = Modifier.padding(16.dp).wrapContentSize(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1)
        Divider(thickness = 5.dp, color = MaterialTheme.colorScheme.secondary)
        Text(
            text = Tracks[track]!!.description,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

