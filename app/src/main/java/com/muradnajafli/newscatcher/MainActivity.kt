package com.muradnajafli.newscatcher

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.muradnajafli.newscatcher.presentation.navigation.NewsNavigator
import com.muradnajafli.newscatcher.ui.theme.NewsCatcherTheme
import com.muradnajafli.newscatcher.util.LanguageUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var languageUtil: LanguageUtil

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsCatcherTheme {
                NewsNavigator(languageUtil = languageUtil)
            }
        }
    }
}