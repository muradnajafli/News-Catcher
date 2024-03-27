package com.muradnajafli.newscatcher.presentation.navigation


interface Destinations{
    val route: String
}

object Details : Destinations {
    override val route = "details"
}

object Home : Destinations {
    override val route = "home"
}

object Dropdown : Destinations {
    override val route = "dropdown"
}

object Bookmarks : Destinations {
    override val route = "bookmarks"
}