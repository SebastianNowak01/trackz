package com.example.trackz.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * FavouritesTablet composable is used to display the favourites screen on a tablet device.
 * It displays the list of favourite tracks on the left side and the description of the selected track on the right side.
 */
@Composable
fun FavouritesTablet() {
    var name by remember {
        mutableStateOf(if (Tracks.keys.toList().filter { key -> Tracks[key]!!.favourite }.isEmpty()) Tracks.keys.first() else
            Tracks.keys.toList().filter { key -> Tracks[key]!!.favourite }.first())
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
                TrackListTabletFavourite(Tracks, onTrackClick = { selectedTrack ->
                    name = selectedTrack
                })
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                TrackDescriptionTabletFavourite(track = name)
            }
        }
    }
}

/**
 * TrackListTabletFavourite composable is used to display the list of favourite tracks on the left side of the screen.
 */
@Composable
fun TrackListTabletFavourite(tracks: HashMap<String, Track> = Tracks, onTrackClick: (String) -> Unit){
    LazyColumn {
        items(tracks.keys.toList().filter { key -> tracks[key]!!.favourite }) { track ->
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
 * TrackDescriptionTabletFavourite composable is used to display the description of the selected track on the right side of the screen.
 */
@Composable
fun TrackDescriptionTabletFavourite(track: String, tracks: HashMap<String, Track> = Tracks){
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
            }
        }
    }
}