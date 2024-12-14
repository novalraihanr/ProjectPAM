package com.example.projectpam.components

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val route: Screen,
    val SelectedIcon: ImageVector,
    val UnSelectedIcon: ImageVector,
)
