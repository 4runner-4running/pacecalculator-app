package com.jhapps.pacecalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PaceScreen(modifier: Modifier = Modifier) {
    var paceText by remember { mutableStateOf("")};
    var speedText by remember { mutableStateOf("")}

    modifier.background(Color.LightGray)
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier= modifier.fillMaxWidth().padding(top = 40.dp, start= 10.dp, end=10.dp)){
        //Text(text = "This will be the pace calculator screen", fontSize = 18.sp)

        Column(modifier.weight(.33f)) {
            // label: Pace
            // input: Number:number i.e. 8:30 /mi
            TextField(paceText,
                onValueChange = { paceText = it },
                maxLines = 1,
                label = { Text("Pace")}
            )
        }

        Column (modifier.weight(.33f)) {
            // label: none
            // vertical stack buttons: "<"  and ">"
            // "<" converts B to A
            // ">" converts A to B
            Text(text = "Convert Button Stack")
        }
        Column (modifier.weight(.33f)){
            // label: Speed
            // mph
            // to .00 precision. I.e: 5.41
            TextField(speedText,
                onValueChange= {speedText = it},
                maxLines = 1,
                label = { Text("Speed")})
        }
    }
}