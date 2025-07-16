package com.jhapps.pacecalculator

import java.math.BigDecimal
import java.math.RoundingMode

class Calculators {
    companion object {
        fun convertSpeedToPace(input: String): Pace {
            val speed = input.toDouble();

            val minutes = 60 / speed;

            val partial = Utils.ExtractDecimalValue(minutes);

            val seconds = partial * 60;

            return Pace(0, minutes.toInt(), seconds.toInt());
        }

        fun convertPaceToSpeed(min: String, sec: String): String {
            val minNum = min.toDouble();
            val secNum = sec.toDouble() /  60;

            val pace = minNum + secNum;

            val speed = 60 / pace;

            return BigDecimal(speed).setScale(2, RoundingMode.HALF_EVEN).toString();
        }

        fun calculateTime(speed: Double, distance: Double): Pace {
            val totalTime = distance / speed;

            val totalMinutes = totalTime * 60;

            if (totalMinutes > 60) {

                var hours = totalMinutes / 60;
                var partialHours = Utils.ExtractDecimalValue(hours);
                var minutes = partialHours * 60;
                var partialMinutes = Utils.ExtractDecimalValue(minutes);
                var seconds = partialMinutes * 60;

                return Pace(hours.toInt(), minutes.toInt(), seconds.toInt());
            }

            val partial = Utils.ExtractDecimalValue(totalMinutes);

            val seconds = partial * 60;

            return Pace(0, totalMinutes.toInt(), seconds.toInt())
        }

        fun calculateDistance(hours: Double, minutes: Double, seconds:Double, speed: Double): Double {
            if ((hours == 0.0 && minutes == 0.0 && seconds == 0.0) || speed == 0.0)
                return 0.0

            // convert everything to minutes
            val totalMinutes = minutes + (hours * 60) + (seconds / 60);

            val distance = (totalMinutes * speed / 60);

            return BigDecimal(distance).setScale(2, RoundingMode.HALF_EVEN).toDouble();
        }

        fun calculateSpeed(hours: Double, minutes: Double, seconds: Double, distance: Double): Double {
            if ((hours == 0.0 && minutes == 0.0 && seconds == 0.0) || distance == 0.0)
                return 0.0

            // convert everything to minutes
            val totalMinutes = minutes + (hours * 60) + (seconds / 60);
            val speed = (distance / totalMinutes) * 60;

            return BigDecimal(speed).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        }
    }
}