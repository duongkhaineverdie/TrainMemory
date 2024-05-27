package com.grabiecjan.trainmemory.utils

object Constants {

    fun randomColor(): Int {
        val alpha = (0..255).random()
        val red = (0..255).random()
        val green = (0..255).random()
        val blue = (0..255).random()
        return alpha shl 24 or (red shl 16) or (green shl 8) or blue
    }
}

