package com.muradnajafli.newscatcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.muradnajafli.newscatcher.navigation.NewsNavigator
import com.muradnajafli.newscatcher.ui.theme.NewsCatcherTheme
import com.muradnajafli.newscatcher.util.LanguageUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsCatcherTheme {
                NewsNavigator(
                    onSetApplicationLocale = LanguageUtils::setupApplicationLocale,
                    onGetApplicationLocale = LanguageUtils::getApplicationLocale
                )
            }
        }
    }
}