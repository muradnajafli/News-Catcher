package com.muradnajafli.newscatcher.navigation

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
import com.muradnajafli.newscatcher.presentation.dropdown.DropdownViewModel
import com.muradnajafli.newscatcher.presentation.home.HomeScreen
import com.muradnajafli.newscatcher.presentation.home.HomeViewModel
import com.muradnajafli.newscatcher.presentation.home.SearchState
import com.muradnajafli.newscatcher.presentation.home.components.NewsTopBar

@Composable
fun NewsNavigator(
    onGetApplicationLocale: () -> String,
    onSetApplicationLocale: (String) -> Unit
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

    selectedItem = remember(backStackState) {
        when (backStackState?.destination?.route) {
            Home.route -> "Home"
            Bookmarks.route -> "Bookmark"
            else -> "Home"
        }
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        val currentRoute = backStackState?.destination?.route
        currentRoute !in listOf(Details.route, Dropdown.route)
    }
    val isTopBarVisible = remember(backStackState) {
        val currentRoute = backStackState?.destination?.route
        currentRoute !in listOf(Details.route, Dropdown.route)
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
                            "Home" -> navigateToTab(navController, Home.route)
                            "Bookmark" -> navigateToTab(navController, Bookmarks.route)
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
        val topPadding = it.calculateTopPadding()
        NavHost(
            navController = navController,
            startDestination = Home.route,
            modifier = Modifier.padding(
                bottom = bottomPadding,
                top = topPadding
            )
        ) {
            composable(Home.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val latestHeadlines by viewModel.latestHeadlines.collectAsStateWithLifecycle()
                val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
                val searchText by viewModel.searchText.collectAsStateWithLifecycle()
                val isSearching by viewModel.isSearching.collectAsStateWithLifecycle()
                val errorHomeMessage by viewModel.errorHomeMessage.collectAsStateWithLifecycle()

                HomeScreen(
                    searchState = SearchState(
                        searchText = searchText,
                        isSearching = isSearching,
                    ),
                    onHomeEvent = viewModel::onEvent,
                    latestHeadlines = latestHeadlines,
                    searchResults = searchResults,
                    navigateToDetails = { article ->
                        navController.safeNavigate(Details.route, article)
                    },
                    navigateToDropdown = {
                        navController.safeNavigate(Dropdown.route)
                    },
                    appLanguage = onGetApplicationLocale(),
                    errorHomeMessage = errorHomeMessage
                )

            }

            composable(Bookmarks.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val articles by viewModel.savedNews.collectAsStateWithLifecycle()

                BookMarkScreen(
                    articles = articles,
                    navigateToDetails = { article ->
                        navController.safeNavigate(Details.route, article)
                    },
                    onGetApplicationLocale = onGetApplicationLocale
                )
            }

            composable(Details.route) {
                val viewModel: DetailViewModel = hiltViewModel()
                val isSaved by viewModel.isSaved.collectAsStateWithLifecycle()

                val retrievedArticle = remember {
                    navController.previousBackStackEntry?.savedStateHandle?.remove<Article>("article")
                }

                retrievedArticle?.let { article ->
                    DetailsScreen(
                        article = article,
                        isSaved = isSaved,
                        onEvent = viewModel::onEvent,
                        onNavigateBack = navController::navigateUpOrBack
                    )
                }
            }

            composable(Dropdown.route) {
                val viewModel: DropdownViewModel = hiltViewModel()

                DropdownScreen(
                    onEvent = viewModel::onEvent,
                    onSetApplicationLocale = onSetApplicationLocale,
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

fun NavController.safeNavigate(route: String, article: Article? = null) {
    if (canNavigateTo(route)) {
        if (article == null) {
            navigate(route)
        } else {
            val currentBackStackEntry = currentBackStackEntry
            currentBackStackEntry?.savedStateHandle?.set("article", article)
            navigate(route)
        }
    }
}


fun NavController.canGoBack(): Boolean {
    return this.previousBackStackEntry != null
}

fun NavController.canNavigateTo(route: String): Boolean {
    return currentDestination?.route != route
}

fun NavController.navigateUpOrBack() {
    if (canGoBack()) {
        popBackStack()
    }
}