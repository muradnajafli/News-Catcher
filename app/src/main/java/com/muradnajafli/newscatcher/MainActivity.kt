package com.muradnajafli.newscatcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.muradnajafli.newscatcher.ui.navigation.NewsNavigator
import com.muradnajafli.newscatcher.ui.theme.NewsCatcherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsCatcherTheme {
                NewsNavigator()
            }
        }
    }
}