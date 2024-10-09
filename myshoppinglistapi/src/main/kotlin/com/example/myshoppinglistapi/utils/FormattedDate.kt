package com.example.myshoppinglistapi.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FormattedDate {
    private var PATTERN = "yyyy-MM-dd"
    fun incrementDate(dateString: String, monthIncrement: Long): String{
        val formatter = DateTimeFormatter.ofPattern(PATTERN)
        val dateCurrent = LocalDate.parse(dateString, formatter)
        val dateIncremented = dateCurrent.plusMonths(monthIncrement)
        return dateIncremented.format(formatter)
    }

    fun dateCurrent(): String{
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern(PATTERN)
        return currentDate.format(formatter)
    }
}