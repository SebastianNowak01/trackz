package com.example.trackz.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


var Tracks = hashMapOf<String, String>(
    "Morskie Oko" to "Morskie oko to znana atrakcja turystyczna dla wielu Polakow",
    "Tatry" to "Tatry to najwyzsze gory w Polsce",
    "Wieliczka" to "Kopalnia soli w Wieliczce to jedna z najwiekszych atrakcji turystycznych w Polsce",
    "Rysy" to "Rysy to najwyzszy szczyt w Polsce",
    "Orle gniazda" to "Orle gniazda to jedna z najbardziej znanych atrakcji turystycznych w Polsce",
    "Kraina Otwartych Okiennic" to "Kraina Otwartych Okiennic to szlak tworzony przez 3 unikalne wsie na Podlasiu - Trześciankę, Soce i Puchły.",
    "Bieszczady" to "Bieszczady to góry w południowo-wschodniej Polsce",
    "Szlak Piastowski" to "Szlak Piastowski to niezwykle atrakcyjna trasa turystyczna łącząca trzy grody stołeczne z okresu wczesnopiastowskiego: Poznan, Gniezno i Kruszwica",
    "Szlak Cysterski" to "Szlak Cysterski to wyjątkowy szlak turystyczny, powstały na terytorium Europy, obejmujący znaczny obszar Polski, szczególnie w zachodniej i środkowej jej części.",
    "Droga Stu Zakretow" to "Droga Stu Zakrętów zwana też Szosą Stu Zakrętów to bardzo popularna trasa wiodąca przez Góry Stołowe",
    "Szlak Tatarski" to "Szlak Tatarski to ciekawy szlak turystyczny w Polsce wschodniej, na Podlasiu.",
    )

@Composable
fun TrackListMobile(tracks: HashMap<String, String> = Tracks){
    LazyColumn {
        items(Tracks.keys.toList()) { track ->
            Text(
                text = track,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            Divider(thickness = 2.dp)
        }
    }
}

@Composable
fun TrackListTablet(tracks: HashMap<String, String> = Tracks, onTrackClick: (String) -> Unit){
    LazyColumn {
        items(Tracks.keys.toList()) { track ->
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
fun TrackDescription(track: String){
    Column {
        Text(text = track,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 50.sp,
            modifier = Modifier.padding(16.dp))
        Divider(thickness = 5.dp, color = MaterialTheme.colorScheme.secondary)
        Text(
            text = Tracks[track]!!,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun MainScreenMobile(navController: NavController) {
    var name by remember {
        mutableStateOf(Tracks.keys.first())
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Title("Trackz")
            TrackListMobile()
        }
    }
}

@Composable
fun TrackDescriptionMobile(navController: NavController, track: String) {
    Text(
        text = Tracks[track]!!,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 20.sp,
        modifier = Modifier.padding(16.dp)
    )
}