package com.muradnajafli.newscatcher.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.muradnajafli.newscatcher.R
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.presentation.bookmark.BookMarkScreen
import com.muradnajafli.newscatcher.presentation.bookmark.BookmarkViewModel
import com.muradnajafli.newscatcher.presentation.detail.DetailViewModel
import com.muradnajafli.newscatcher.presentation.detail.DetailsScreen
import com.muradnajafli.newscatcher.presentation.dropdown.DropdownScreen
import com.muradnajafli.newscatcher.presentation.home.HomeScreen
import com.muradnajafli.newscatcher.presentation.home.HomeViewModel
import com.muradnajafli.newscatcher.presentation.home.SearchState
import com.muradnajafli.newscatcher.presentation.home.components.NewsTopBar
import com.muradnajafli.newscatcher.util.LanguageUtil


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NewsNavigator(
    languageUtil: LanguageUtil
) {
    val bottomNavItems = listOf(
        BottomNavItem(
            icon = R.drawable.ic_home,
            selectedIcon = R.drawable.ic_filled_home,
            content = "Home"
        ),
        BottomNavItem(
            icon = R.drawable.ic_save,
            selectedIcon = R.drawable.ic_filled_save,
            content = "Bookmark"
        )
    )

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf("Home")
    }

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            "home" -> "Home"
            "bookmark" -> "Bookmark"
            else -> "Home"
        }
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        val currentRoute = backStackState?.destination?.route
        currentRoute !in listOf("details", "dropdown")
    }
    val isTopBarVisible = remember(backStackState) {
        val currentRoute = backStackState?.destination?.route
        currentRoute !in listOf("details", "dropdown")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavItems,
                    selectedItem = selectedItem,
                    onItemClick = { item ->
                        when (item) {
                            "Home" -> navigateToTab(navController, "home")
                            "Bookmark" -> navigateToTab(navController, "bookmark")
                        }
                    }
                )
            }
        },
        topBar = {
            if (isTopBarVisible) {
                NewsTopBar()
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable("home") {
                val viewModel = hiltViewModel<HomeViewModel>()
                val latestHeadlines by viewModel.latestHeadlines.collectAsStateWithLifecycle()
                val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
                val searchText by viewModel.searchText.collectAsStateWithLifecycle()
                val isSearching by viewModel.isSearching.collectAsStateWithLifecycle()
                val errorHomeMessage by viewModel.errorHomeMessage.collectAsStateWithLifecycle()
                val errorSearchMessage by viewModel.errorSearchMessage.collectAsStateWithLifecycle()

                HomeScreen(
                    searchState = SearchState(
                        searchText = searchText,
                        onSearchChange = viewModel::searchArticles,
                        isSearching = isSearching,
                    ),
                    latestHeadlines = latestHeadlines,
                    searchResults = searchResults,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController, article = article
                        )
                    },
                    navigateToDropdown = {
                        navigateToDropdown(navController = navController)
                    },
                    appLanguage = languageUtil.getApplicationLocale(),
                    errorHomeMessage = errorHomeMessage,
                    errorSearchMessage = errorSearchMessage
                )

            }

            composable("bookmark") {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val articles by viewModel.savedNews.collectAsStateWithLifecycle()

                BookMarkScreen(
                    articles = articles,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    },
                    languageUtil = languageUtil
                )
            }

            composable("details") {
                val viewModel: DetailViewModel = hiltViewModel()
                val isSaved by viewModel.isSaved.collectAsStateWithLifecycle()

                navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            addOrDeleteFromSaved = viewModel::addOrRemoveNewsFromSaved,
                            checkIfNewsIsInSaved = viewModel::checkIfNewsIsInSaved,
                            isSaved = isSaved,
                            navigateToBack = navController::navigateUpOrBack
                        )
                    }
            }

            composable("dropdown") {
                val viewModel: HomeViewModel = hiltViewModel()

                DropdownScreen(
                    setLanguage = { language ->
                        viewModel.updateLanguagePreference(language)
                        languageUtil.setupApplicationLocale(language)
                    },
                    navigateToBack = navController::navigateUpOrBack
                )

            }
        }
    }
}

fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun navigateToDetails(navController: NavController, article: Article) {
    if (navController.canGoTo("details")) {
        navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
        navController.navigate("details")
    }
}

fun navigateToDropdown(navController: NavController) {
    if (navController.canGoTo("dropdown")) {
        navController.navigate("dropdown")
    }
}

fun NavController.canGoBack(): Boolean {
    return this.previousBackStackEntry != null
}

fun NavController.canGoTo(route: String): Boolean {
    return currentDestination?.route != route
}

fun NavController.navigateUpOrBack() {
    if (canGoBack()) {
        popBackStack()
    }
}

