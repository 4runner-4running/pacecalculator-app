package com.jhapps.pacecalculator

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
){
    PACE("pace", "Pace", Icons.Filled.PlayArrow, "Pace Calculations"),
    SPLITS("splits", "Splits", Icons.Filled.Info, "Split Calculations");
}