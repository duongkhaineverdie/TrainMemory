package com.emenike.trainmemory.utils

import android.graphics.Color
import com.emenike.trainmemory.data.model.DateTimeHomeDisplay
import com.emenike.trainmemory.data.model.MinuteAndHour
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs
import kotlin.random.Random

object Constants {
    fun convertMillisToFormattedDate(timestamp: Long): String {
        val formatter = SimpleDateFormat("dd-MM-yyyy hh:mm aaa", Locale.US)
        val date = Date(timestamp)
        return formatter.format(date)
    }

    fun convertMillisToMinuteAndHour(timestamp: Long): MinuteAndHour {
        val localDateTime = Instant.fromEpochMilliseconds(timestamp).toLocalDateTime(timeZone = TimeZone.currentSystemDefault())
        val minute = localDateTime.minute + localDateTime.second / 60f
        val hour = localDateTime.hour + localDateTime.minute / 60f
        val second = localDateTime.second
        return MinuteAndHour(minute, hour, second.toFloat())
    }


    fun convertMillisToDateHome(timestamp: Long): DateTimeHomeDisplay {
        val simpleDateFormat = SimpleDateFormat("a MMM dd HH:mm", Locale.US)
        val formattedDate = simpleDateFormat.format(Date(timestamp))

        val amPm = formattedDate.substring(0, 2)
        val month = formattedDate.substring(3, 6)
        val day = formattedDate.substring(7, 9)
        val hour = formattedDate.substring(10, 12)
        val minute = formattedDate.substring(13, 15)

        return DateTimeHomeDisplay(minute = minute, hour = hour, date = day, monthString = month, typeTime = amPm)
    }

    fun convertMinuteToDegrees(minute: Float): Float {
        val result = ((minute % 60) / 60) * 360 - 180
        return result
    }

    fun convertHourToDegrees(hour: Float): Float {
        val result = ((hour % 12) / 12) * 360 - 180
        return result
    }

    fun getDisplayType(totalSecond: Long): Int {
        return when {
            totalSecond <= 59 -> 1
            totalSecond <= 3599 -> 2
            else -> 3
        }
    }

    fun secondToTimeCountdown(seconds: Long, displayType: Int): String {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val secondsRemaining = (seconds % 3600) % 60

        val formattedTime = when (displayType) {
            3 -> "${hours.format(2)}:${minutes.format(2)}:${secondsRemaining.format(2)}"
            2 -> "${minutes.format(2)}:${secondsRemaining.format(2)}"
            1 -> secondsRemaining.format(2)
            else -> throw IllegalArgumentException("Invalid display type: $displayType")
        }

        return formattedTime
    }

    fun randomColor(): Int {
        val alpha = (0..255).random()
        val red = (0..255).random()
        val green = (0..255).random()
        val blue = (0..255).random()
        return alpha shl 24 or (red shl 16) or (green shl 8) or blue
    }


    fun randomSimilarColor(originalColor: Int, level: Int): Int {
        val alpha = (0..255).random()
        val red = (originalColor and 0x00ff0000) shr 16
        val green = (originalColor and 0x0000ff00) shr 8
        val blue = originalColor and 0x000000ff

        val hsv = floatArrayOf(0f, 0f, 0f)
        // Assuming standard color format (ARGB)
        Color.RGBToHSV(red, green, blue, hsv)

        val deltaHue = level * 0.1f
        val deltaValue = level * 0.05f

        hsv[0] = (hsv[0] + deltaHue) % 360f
        hsv[2] = (hsv[2] + deltaValue).coerceIn(0f, 1f)

        val rgb = hsvToRgb(hsv)

        return alpha shl 24 or (rgb[0] shl 16) or (rgb[1] shl 8) or rgb[2]
    }

    fun hsvToRgb(hsv: FloatArray): IntArray {
        val h = hsv[0] / 360f
        val s = hsv[1]
        val v = hsv[2]

        val c = v * s
        val x = c * (1 - abs(h % 2 - 1))
        val m = v - c

        var r = 0f
        var g = 0f
        var b = 0f

        when (val ih = (h * 6).toInt()) {
            0 -> { r = c; g = x; b = 0f }
            1 -> { r = x; g = c; b = 0f }
            2 -> { r = 0f; g = c; b = x }
            3 -> { r = 0f; g = x; b = c }
            4 -> { r = x; g = 0f; b = c }
            5 -> { r = c; g = 0f; b = x }
            else -> { r = 0f; g = 0f; b = 0f }
        }

        return intArrayOf(
            ((r + m) * 255).toInt(),
            ((g + m) * 255).toInt(),
            ((b + m) * 255).toInt()
        )
    }
}

