package com.jhapps.pacecalculator

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PaceScreen(modifier: Modifier = Modifier) {
    Row(modifier.padding(top = 20.dp)){
        Text(text = "This will be the pace calculator screen", fontSize = 18.sp)
    }
}