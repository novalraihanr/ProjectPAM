package com.example.projectpam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.projectpam.components.Screen
import com.example.projectpam.screen.CartScreen
import com.example.projectpam.screen.EditProfileScreen
import com.example.projectpam.screen.Homepage
import com.example.projectpam.screen.ItemDetailScreen
import com.example.projectpam.screen.Login
import com.example.projectpam.screen.SignUp
import com.example.projectpam.screen.dashboard
import com.example.projectpam.ui.theme.ProjectPAMTheme
import com.example.projectpam.viewModel.AuthViewModel
import com.example.projectpam.viewModel.MakananMinumanViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectPAMTheme {
               Scaffold(modifier = Modifier.fillMaxSize().background(Color.White)) { innerPadding ->
                    RootNav(Modifier.padding(innerPadding))
               }
            }
        }
    }
}

@Composable
fun RootNav(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.AuthNav) {

        AuthNav(navController)

        composable<Screen.HomeNav> {
            Homepage(
                logout = {
                    navController.navigate(Screen.AuthNav) {
                        popUpTo(0)
                    }
                },
                authViewModel = AuthViewModel()
            )
        }
    }
}

fun NavGraphBuilder.AuthNav(
    navController: NavHostController
) {
    navigation<Screen.AuthNav>(startDestination = Screen.LoginScreen){
        composable<Screen.LoginScreen> {
            Login(
                GoToSignUp = {
                    navController.navigate(Screen.SignUpScreen)
                },
                LoggingIn = {
                    navController.navigate(Screen.HomeNav)
                },
                authViewModel = AuthViewModel()
            )
        }
        composable<Screen.SignUpScreen> {
            SignUp(
                GoBackToLogin = {
                    navController.popBackStack()
                },
                authViewModel = AuthViewModel()
            )
        }
    }
}