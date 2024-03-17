package com.muradnajafli.newscatcher.util

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

object LanguageUtil {

    private const val DEFAULT_LANGUAGE = "EN"

    fun setupApplicationLocale(languageTag: String) {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(languageTag.lowercase())
        )
    }

    fun getApplicationLocale(): String {
        val defaultLocale = LocaleListCompat.getAdjustedDefault().get(0)
        val language = defaultLocale?.language?.uppercase() ?: DEFAULT_LANGUAGE
        return language.ifEmpty {
            DEFAULT_LANGUAGE
        }
    }
}