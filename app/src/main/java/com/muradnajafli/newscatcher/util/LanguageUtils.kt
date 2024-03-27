package com.muradnajafli.newscatcher.util

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject

class LanguageUtils @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private companion object {
        private const val DEFAULT_LANGUAGE = "en"
    }

    fun setupApplicationLocale(languageTag: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)
                ?.applicationLocales = LocaleList.forLanguageTags(languageTag)
        } else {
            val locale = Locale(languageTag)
            Locale.setDefault(locale)
            val resources = context.resources
            val configuration = resources.configuration
            configuration.setLocale(locale)
            @Suppress("DEPRECATION")
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }
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