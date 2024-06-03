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
import androidx.navigation.NavController

/**
 * Composable function that displays the main screen of the app in the mobile version.
 *
 * @param navController The NavController that manages app navigation.
 */
@Composable
fun MainScreenMobile(navController: NavController) {
    var name by remember {
        mutableStateOf(Tracks.keys.first())
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 52.dp)
        ,
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            TrackListMobile(Tracks, onTrackClick = {
                    selectedTrack ->
                name = selectedTrack
                navController.navigate(Screen.DetailScreen.withArgs(name))
                }
            )
        }
    }
}

/**
 * Composable function that displays a list of tracks in the mobile version.
 *
 * @param tracks The list of tracks to display.
 * @param onTrackClick The lambda function that is called when a track is clicked.
 */
@Composable
fun TrackListMobile(tracks: HashMap<String, Track>, onTrackClick: (String) -> Unit){
    LazyColumn {
        items(tracks.keys.toList()) { track ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                tracks[track]?.let { painterResource(id = it.image) }
                    ?.let { Image(
                        painter = it,
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp),
                        contentScale = ContentScale.Crop)}
                Text(
                    text = track,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clickable { onTrackClick(track) }
                )
            }
            Divider(thickness = 2.dp)
        }
    }
}

/**
 * Composable function that displays the description of a track in the mobile version.
 *
 * @param track The name of the track.
 * @param tracks The list of tracks.
 */
@Composable
fun TrackDescriptionMobile(track: String?, tracks : HashMap<String, Track> = Tracks) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (track != null) {
                Text(
                    text = track,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 50.sp,
                    modifier = Modifier
                        .padding(16.dp)
                        .wrapContentSize()
                        .padding(top = 52.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Divider(thickness = 5.dp, color = MaterialTheme.colorScheme.secondary)
            tracks[track]?.let { painterResource(id = it.image) }
                ?.let { Image(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier
                        .size(600.dp)
                        .padding(20.dp),
                    contentScale = ContentScale.Crop)}
            Text(
                text = tracks[track]!!.description,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { if (track != null) {Tracks[track]!!.favourite = !Tracks[track]!!.favourite} },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                Text(text = "Dodaj/usun z ulubionych")
            }
        }
    }
}