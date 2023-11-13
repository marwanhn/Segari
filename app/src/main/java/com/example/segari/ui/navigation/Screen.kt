package com.example.segari.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object About : Screen("about")
    object DetailProduct : Screen("home/{productId}") {
        fun createRoute(productId: Long) = "home/$productId"
    }
}