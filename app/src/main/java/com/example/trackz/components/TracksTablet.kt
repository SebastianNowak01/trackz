package com.example.trackz.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * MainScreenTablet is a composable function that displays the main screen of the app on a tablet.
 * It consists of two columns: the first one displays the list of tracks, the second one displays the
 * description of the selected track.
 */
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

/**
 * TrackListTablet is a composable function that displays the list of tracks on a tablet.
 *
 * @param tracks: a HashMap of tracks
 * @param onTrackClick: a function that takes a track name as a parameter
 */
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

/**
 * TrackDescriptionTablet is a composable function that displays the description of a track on a tablet.
 *
 * @param track: a track name
 * @param tracks: a HashMap of tracks
 */
@Composable
fun TrackDescriptionTablet(track: String, tracks: HashMap<String, Track> = Tracks){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = track,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 50.sp,
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1)
        Divider(thickness = 5.dp, color = MaterialTheme.colorScheme.secondary)
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                tracks[track]?.let { painterResource(id = it.image) }
                    ?.let { Image(
                        painter = it,
                        contentDescription = null,
                        modifier = Modifier
                            .size(400.dp)
                            .padding(40.dp),
                        contentScale = ContentScale.Crop)
                    }
                Text(
                    text = Tracks[track]!!.description,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = { Tracks[track]!!.favourite = !Tracks[track]!!.favourite },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                    Text(text = "Dodaj/usun z ulubionych")
                }
            }
        }
    }
}

