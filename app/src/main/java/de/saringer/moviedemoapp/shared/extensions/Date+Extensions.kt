package de.saringer.moviedemoapp.shared.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String?.getDateInAnotherFormat(inputFormat: String, outputFormat: String): String =
    SimpleDateFormat(inputFormat, Locale.getDefault()).parse(this.orEmpty())
        ?.let { SimpleDateFormat(outputFormat, Locale.getDefault()).format(it) }
        ?: ""