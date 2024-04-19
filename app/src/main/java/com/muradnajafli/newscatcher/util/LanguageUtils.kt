package com.muradnajafli.newscatcher.util


import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

object LanguageUtils {
    private const val DEFAULT_LANGUAGE = "en"

    fun setupApplicationLocale(languageTag: String) {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(languageTag)
        )
    }

    fun getApplicationLocale(): String {
        val defaultLocale = LocaleList.getDefault().get(0)
        val language = defaultLocale?.language?.uppercase() ?: DEFAULT_LANGUAGE
        return if (language.isEmpty() || !isLanguageSupported(language)) {
            DEFAULT_LANGUAGE
        } else {
            language
        }
    }

    private fun isLanguageSupported(language: String): Boolean {
        return language.equals("en", ignoreCase = true) || language.equals("ru", ignoreCase = true)
    }

}