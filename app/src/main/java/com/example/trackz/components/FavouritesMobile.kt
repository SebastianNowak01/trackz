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
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

/**
 * Composable that displays the favourite tracks.
 * The user can click on a track to see its description.
 */
@Composable
fun FavouritesMobile() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  Favourite.FavMainScreen.route) {
        composable(Favourite.FavMainScreen.route) {
            MainScreenMobileFavourites(navController = navController)
        }
        composable(route = Favourite.FavDetailScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { entry ->
            TrackDescriptionMobileFavourites(track = entry.arguments?.getString("name"))
        }
    }
}

/**
 * Composable that displays the main screen of the favourite tracks.
 * The user can click on a track to see its description.
 * @param navController the navigation controller
 */
@Composable
fun MainScreenMobileFavourites(navController: NavController) {
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
            TrackListMobileFavourites(Tracks, onTrackClick = {
                    selectedTrack ->
                name = selectedTrack
                navController.navigate(Favourite.FavDetailScreen.withArgs(name))
            })
        }
    }
}

/**
 * Composable that displays the list of favourite tracks.
 * The user can click on a track to see its description.
 * @param tracks the list of tracks to display
 * @param onTrackClick the action to perform when a track is clicked
 */
@Composable
fun TrackListMobileFavourites(tracks: HashMap<String, Track>, onTrackClick: (String) -> Unit){
    LazyColumn {
        items(tracks.keys.toList().filter { key -> tracks[key]!!.favourite }) { track ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                tracks[track]?.let { painterResource(id = it.image) }
                    ?.let { Image(
                        painter = it,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp).padding(8.dp),
                        contentScale = ContentScale.Crop)
                    }
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
 * Composable that displays the description of a track.
 * @param track the name of the track
 * @param tracks the list of tracks
 */
@Composable
fun TrackDescriptionMobileFavourites(track: String?, tracks : HashMap<String, Track> = Tracks) {
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
                    modifier = Modifier.size(600.dp).padding(20.dp),
                    contentScale = ContentScale.Crop)
                }
            Text(
                text = tracks[track]!!.description,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}