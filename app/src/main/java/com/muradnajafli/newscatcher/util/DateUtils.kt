package com.muradnajafli.newscatcher.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object DateUtils {
    fun formatDateTime(dateTimeString: String?): String {
        if (dateTimeString.isNullOrEmpty()) {
            return ""
        }

        return try {
            val inputFormats = arrayOf(
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()),
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            )
            val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

            var date: Date? = null
            for (format in inputFormats) {
                try {
                    date = format.parse(dateTimeString)
                    break
                } catch (e: Exception) {

                }
            }

            outputFormat.format(date ?: Date())
        } catch (e: Exception) {
            ""
        }
    }

    fun getTodayDate(language: String): String {
        val locale = when (language.lowercase()) {
            "en" -> Locale("en", "US")
            "ru" -> Locale("ru", "RU")
            else -> Locale.getDefault()
        }

        val date = Date()
        val dayOfMonth = SimpleDateFormat("d", locale).format(date).toInt()
        val daySuffix = getDaySuffix(dayOfMonth)

        val dateFormat = SimpleDateFormat("MMMM dd'$daySuffix', yyyy", locale)
        return dateFormat.format(date)
    }

    private fun getDaySuffix(day: Int, locale: Locale = Locale.getDefault()): String {
        return when (locale.language) {
            "ru" -> {
                if (day % 10 == 1 && day % 100 != 11) {
                    "ое"
                } else if (day % 10 in 2..4 && day % 100 !in 12..14) {
                    "ое"
                } else {
                    "е"
                }
            }
            else -> {
                if (day in 11..14) {
                    "th"
                } else {
                    when (day % 10) {
                        1 -> "st"
                        2 -> "nd"
                        3 -> "rd"
                        else -> "th"
                    }
                }
            }
        }
    }

}
