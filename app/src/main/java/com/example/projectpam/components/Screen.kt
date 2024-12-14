package com.example.projectpam.components

sealed interface Screen {
    @kotlinx.serialization.Serializable
    data object OnboardingScreen: Screen

    @kotlinx.serialization.Serializable
    data object LoginScreen: Screen

    @kotlinx.serialization.Serializable
    data object SignUpScreen: Screen

    @kotlinx.serialization.Serializable
    data object HomepageScreen: Screen

    @kotlinx.serialization.Serializable
    data object DrinknFoodScreen: Screen

    @kotlinx.serialization.Serializable
    data object CartScreen: Screen

    @kotlinx.serialization.Serializable
    data class ItemDetailScreen(
        val id: Int,
        val title: String,
        val price: Int,
        val description: String,
        val quantity: Int,
        val image: String,
    ): Screen

    @kotlinx.serialization.Serializable
    data object OrderHistoryScreen: Screen

    @kotlinx.serialization.Serializable
    data object ProfileScreen: Screen

    @kotlinx.serialization.Serializable
    data object ProfileUpdateScreen: Screen

    @kotlinx.serialization.Serializable
    data object HomeNav : Screen

    @kotlinx.serialization.Serializable
    data object AuthNav : Screen

    @kotlinx.serialization.Serializable
    data object ItemDetailNav : Screen

    @kotlinx.serialization.Serializable
    data object CartNav : Screen

    @kotlinx.serialization.Serializable
    data object ProfileNav : Screen

}