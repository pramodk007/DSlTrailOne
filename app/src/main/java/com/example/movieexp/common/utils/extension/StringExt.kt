package com.example.movieexp.common.utils.extension

fun String.capitalizeFirstChar(): String {
    return replaceFirstChar {
        it.uppercaseChar()
    }
}