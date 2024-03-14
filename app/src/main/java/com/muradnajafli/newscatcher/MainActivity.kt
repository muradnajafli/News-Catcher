package com.muradnajafli.newscatcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muradnajafli.newscatcher.ui.home.HomeScreen
import com.muradnajafli.newscatcher.ui.home.HomeViewModel
import com.muradnajafli.newscatcher.ui.home.SearchState
import com.muradnajafli.newscatcher.ui.theme.NewsCatcherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsCatcherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<HomeViewModel>()
                    val news by viewModel.news.collectAsStateWithLifecycle()
                    val isSearching by viewModel.isSearching.collectAsStateWithLifecycle()
                    val searchText by viewModel.searchText.collectAsStateWithLifecycle()

                    HomeScreen(
                        searchState = SearchState(
                            searchText = searchText,
                            onSearchChange = viewModel::onSearchTextChanged,
                            isSearching = isSearching,
                            news = news
                        )
                    )
                }
            }
        }
    }
}
