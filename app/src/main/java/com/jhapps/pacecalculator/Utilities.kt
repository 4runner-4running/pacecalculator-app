package com.jhapps.pacecalculator

import android.icu.math.BigDecimal

class Utils {
    companion object {
        fun String.formatToPace(input: String): String {
            var output = "";

            if (input.isEmpty())
                return input;

            output = String.format("%1", input);

            return output;
        }

        fun ExtractDecimalValue(input: Double): Double {
            var bigDecimal = BigDecimal(input)

            var intValue = bigDecimal.toInt();

            var decValue = bigDecimal.subtract(BigDecimal(intValue));

            return decValue.toDouble();
        }
    }
}