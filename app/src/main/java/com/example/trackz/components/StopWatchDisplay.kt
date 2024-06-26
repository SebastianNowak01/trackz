package com.example.trackz.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable that displays the stopwatch and buttons to control it.
 * @param formattedTime the time to display
 * @param onStartClick the action to perform when the start button is clicked
 * @param onPauseClick the action to perform when the pause button is clicked
 * @param onResetClick the action to perform when the reset button is clicked
 * @param onSaveClick the action to perform when the save button is clicked
 * @param modifier the modifier to apply to this layout
 */
@Composable
fun StopWatchDisplay(
    formattedTime: String,
    onStartClick: () -> Unit,
    onPauseClick: () -> Unit,
    onResetClick: () -> Unit,
    onSaveClick: () -> String,
    modifier: Modifier = Modifier
) {
    var lastTime by remember { mutableStateOf(formattedTime) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formattedTime,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onStartClick,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text(text = "Start")
                }
                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onPauseClick,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                    Text(text = "Pauza")
                }
                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onResetClick,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                    Text(text = "Reset")
                }
                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    {
                        lastTime = onSaveClick()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                    Text(text = "Zapisz")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Ostatni zapisany czas: $lastTime",
                 color = MaterialTheme.colorScheme.primary,
                 fontSize = 20.sp,
                 fontWeight = FontWeight.Bold
                 )
        }
    }
}