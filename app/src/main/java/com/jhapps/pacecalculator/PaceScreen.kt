package com.jhapps.pacecalculator

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("DefaultLocale")
@Composable
fun PaceScreen(modifier: Modifier = Modifier) {
    var paceMinText by remember { mutableStateOf("")}
    var paceSecText by remember { mutableStateOf("") }
    var speedText by remember { mutableStateOf("")}

    fun handleSpeedConversion(){
        if(speedText.isEmpty())
            return;

        val pace = Calculators.convertSpeedToPace(speedText);
        paceMinText = "${pace.minutes}";
        paceSecText = String.format("%02d", pace.seconds);
    }

    fun handlePaceConversion(){

        val minutes = if (paceMinText.isEmpty()) {
            "00"
        }
        else {paceMinText}

        val seconds = if (paceSecText.isEmpty()){
            "00"
        }else {paceSecText}
        paceSecText = seconds;

        val speed = Calculators.convertPaceToSpeed(minutes, seconds);
        speedText = speed;
    }

    modifier.background(Color.LightGray)
    Row(modifier = modifier.fillMaxWidth().padding(top = 40.dp)){
        Text("Pace Calculations", fontSize = 36.sp, fontWeight = FontWeight.Bold)
    }
    HorizontalDivider(modifier = modifier.padding(top = 90.dp, bottom = 10.dp))
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier= modifier.fillMaxWidth().padding(top = 100.dp, start= 10.dp, end=10.dp)){
        //Text(text = "This will be the pace calculator screen", fontSize = 18.sp)

        Column(verticalArrangement = Arrangement.Center,
            ) {
            // label: Pace
            // input: Number:number i.e. 8:30 /mi
            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceAround,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(text = "Pace (min/mi)", fontWeight = FontWeight.SemiBold);
                Text(text = "Speed (mph)", fontWeight = FontWeight.SemiBold);
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                TextField(
                    paceMinText,
                    onValueChange = {
                        paceMinText = it
                    },
                    maxLines = 1,
                    textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 28.sp),
                    modifier = modifier.width(100.dp).padding(end = 4.dp),
                    keyboardOptions = KeyboardOptions(keyboardType =  KeyboardType.Number)
                )
                TextField(
                    paceSecText,
                    onValueChange = {
                        paceSecText = it
                    },
                    maxLines = 1,
                    textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 28.sp),
                    modifier = modifier.width(100.dp).padding(end = 4.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                TextField(
                    speedText,
                    onValueChange = { speedText = it; Log.d("SpeedInput::", "input value: $it") },
                    maxLines = 1,
                    textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 28.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = modifier.fillMaxWidth().padding(top = 10.dp)
            ) {

                Button(
                    onClick = { handlePaceConversion()}, modifier = modifier.width(75.dp)) {
                    Text(">")
                }
                Button(onClick = { handleSpeedConversion()}, modifier = modifier.width(75.dp)) {
                    Text("<")
                }
            }

            HorizontalDivider(modifier = modifier.padding(top = 10.dp, bottom = 10.dp))

            TimeSpeedDistance(setSpeedCallback = { speedText = it})
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun TimeSpeedDistance(setSpeedCallback: (calculatedSpeed: String) -> Unit, modifier:Modifier = Modifier){
    var hourText by remember{ mutableStateOf("")}
    var minText by remember { mutableStateOf("")}
    var secondText by remember { mutableStateOf("")}
    var speedText by remember { mutableStateOf("")}
    var distanceText by remember { mutableStateOf("")}

    fun handleTimeClick(){
        if (speedText.isEmpty() || distanceText.isEmpty())
            return;

        val pace = Calculators.calculateTime(speedText.toDouble(), distanceText.toDouble())
        Log.d("PaceScreen::", "${pace.hour} : ${pace.minutes} : ${pace.seconds}")
        hourText = if (pace.hour > 0){
            pace.hour.toString()
        } else {
            ""
        }

        minText = String.format("%02d", pace.minutes);
        secondText = String.format("%02d", pace.seconds);
    }

    fun handleDistanceClick(){
        if (speedText.isEmpty())
            return;

        val distance = Calculators.calculateDistance(hourText.toDouble(), minText.toDouble(), secondText.toDouble(), speedText.toDouble())
        distanceText = "$distance";
    }

    fun handleSpeedClick(){
        if (distanceText.isEmpty())
            return;

        val speed = Calculators.calculateSpeed(hourText.toDouble(), minText.toDouble(), secondText.toDouble(), distanceText.toDouble())
        speedText = "$speed"
    }

    Row(modifier = modifier.fillMaxWidth()){
        Column(modifier = modifier.weight(.6f).padding(start = 10.dp)){
            Row() {//hour
                TextField(
                    hourText,
                    onValueChange = { hourText = it },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 20.sp),
                    modifier = modifier.width(60.dp).padding(end = 4.dp)
                )
                //min
                TextField(
                    minText,
                    onValueChange = { minText = it },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 20.sp),
                    modifier = modifier.width(60.dp).padding(end = 4.dp)
                )
                //Sec
                TextField(
                    secondText,
                    onValueChange = { secondText = it },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 20.sp),
                    modifier = modifier.width(60.dp).padding(end = 4.dp)
                )
            }
        }
        Column(modifier = modifier.weight(.4f)){
            Row(){
                Button(onClick={handleTimeClick()}){
                    Text("TIME")
                }
            }
        }
    }
    Row(modifier = modifier.fillMaxWidth().padding(top=10.dp)){
        Column(modifier = modifier.weight(.1f)){}
        Column(modifier = modifier.weight(.5f)) {
            Row() {
                Button(onClick = {setSpeedCallback(speedText)}) {
                    Text("^")
                }
                TextField(
                    speedText,
                    onValueChange = { speedText = it },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 20.sp),
                    modifier = modifier.width(90.dp).padding(start = 10.dp)
                )
            }
        }
        Column(modifier = modifier.weight(.4f)){
            Button(onClick = {handleSpeedClick()}){
                Text("SPEED")
            }
        }
    }
    Row(modifier = modifier.fillMaxWidth().padding(top=10.dp)){
        Column(modifier = modifier.weight(.1f)){}
        Column(modifier = modifier.weight(.5f)){
            TextField(distanceText,
                onValueChange = { distanceText = it},
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 20.sp),
                modifier = modifier.width(150.dp)
            )
        }
        Column(modifier = modifier.weight(.4f)){
            Button(onClick = {handleDistanceClick()}){
                Text("DISTANCE")
            }
        }
    }
}

@Preview
@Composable
fun PaceScreenPreview(){
    PaceScreen()
}