package com.example.projectpam.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.projectpam.components.BottomNavItem
import com.example.projectpam.R
import com.example.projectpam.components.ItemCards
import com.example.projectpam.components.Screen
import com.example.projectpam.viewModel.AuthState
import com.example.projectpam.viewModel.AuthViewModel
import com.example.projectpam.viewModel.MakananMinumanViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlin.math.log

@Composable
fun Homepage(
    logout: () -> Unit,
    authViewModel: AuthViewModel
){

    val bottomNavItems = listOf(
        BottomNavItem(
            title = "Home",
            route = Screen.HomepageScreen,
            SelectedIcon = ImageVector.vectorResource(id = R.drawable.home_filled),
            UnSelectedIcon = ImageVector.vectorResource(id = R.drawable.home)
        ),
        BottomNavItem(
            title = "Menu",
            route = Screen.DrinknFoodScreen,
            SelectedIcon = ImageVector.vectorResource(id = R.drawable.coffee_filled),
            UnSelectedIcon = ImageVector.vectorResource(id = R.drawable.coffee)
        ),
        BottomNavItem(
            title = "Reciept",
            route = Screen.OrderHistoryScreen,
            SelectedIcon = ImageVector.vectorResource(id = R.drawable.reciept),
            UnSelectedIcon = ImageVector.vectorResource(id = R.drawable.reciept)
        ),
        BottomNavItem(
            title = "Profile",
            route = Screen.ProfileScreen,
            SelectedIcon = ImageVector.vectorResource(id = R.drawable.person_filled),
            UnSelectedIcon = ImageVector.vectorResource(id = R.drawable.person)
        )
    )

    var selected by remember {
        mutableIntStateOf(0)
    }

    val navController = rememberNavController()

    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.Unauthenticated -> logout()
            else -> Unit
        }
    }

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            val bottomBarDestination = bottomNavItems.any { it.route::class.qualifiedName == currentDestination?.route }

            if(bottomBarDestination){
                NavigationBar(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
                    containerColor = Color.White
                ) {
                    bottomNavItems.forEachIndexed { index, bottomNavItem ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any { it.route == bottomNavItem.route::class.qualifiedName } == true,
                            onClick = {
                                navController.navigate(bottomNavItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(imageVector =
                                if (index == selected){
                                    bottomNavItem.SelectedIcon
                                }else {
                                    bottomNavItem.UnSelectedIcon
                                }, contentDescription = bottomNavItem.title)
                            },
                            colors = NavigationBarItemColors(
                                selectedIconColor = Color(0xFF946534),
                                selectedTextColor = NavigationBarItemDefaults.colors().selectedTextColor,
                                selectedIndicatorColor = Color.White,
                                unselectedIconColor = NavigationBarItemDefaults.colors().unselectedIconColor,
                                unselectedTextColor = NavigationBarItemDefaults.colors().unselectedTextColor,
                                disabledIconColor = NavigationBarItemDefaults.colors().disabledIconColor,
                                disabledTextColor = NavigationBarItemDefaults.colors().disabledTextColor
                            ),
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val bottomBarDestination = bottomNavItems.any { it.route::class.qualifiedName == currentDestination?.route }

            if(bottomBarDestination) {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.CartScreen) },
                    containerColor = Color(0xFF946534),
                    shape = RoundedCornerShape(100.dp),
                    modifier = Modifier
                        .size(70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cart),
                        contentDescription = "Add",
                        modifier = Modifier.size(35.dp)
                    )
                }

            }
        },
        modifier = Modifier.background(Color.White)
    ) { paddingValues ->
        InnerNavigation(
            navController,
            modifier = Modifier.padding(paddingValues),
            logout,
        )
    }
}

@Composable
fun InnerNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    logout: () -> Unit,
){
    NavHost(
        navController = navController,
        startDestination = Screen.HomepageScreen,
    ) {

        composable<Screen.HomepageScreen> {
            dashboard(
                GoToDetail = {
                    navController.navigate(Screen.ItemDetailScreen)
                },
                authViewModel = AuthViewModel(),
                makananMinumanViewModel = MakananMinumanViewModel(),
                navController
            )
        }

        composable<Screen.DrinknFoodScreen> {
            DrinknFoodList(
                navController = navController,
                makananMinumanViewModel = MakananMinumanViewModel()
            )
        }

        composable<Screen.OrderHistoryScreen> {
            OrderScreen()
        }

        composable<Screen.ProfileScreen> {
            ProfileScreen(
                EditProfile = {
                    navController.navigate(Screen.ProfileUpdateScreen)
                },
                logout,
                authViewModel = AuthViewModel()
            )
        }

        composable<Screen.ItemDetailScreen> {
            val args = it.toRoute<Screen.ItemDetailScreen>()
            ItemDetailScreen(
                screen = args,
                GoBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screen.CartScreen> {
            CartScreen(
                GoBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screen.ProfileUpdateScreen> {
            EditProfileScreen(
                GoBack = {
                    navController.popBackStack()
                },
                authViewModel = AuthViewModel()
            )
        }
    }
}

@Composable
fun dashboard(
    GoToDetail: () -> Unit,
    authViewModel: AuthViewModel,
    makananMinumanViewModel: MakananMinumanViewModel,
    navController: NavController
) {
    var search by remember {
        mutableStateOf("")
    }

    val authState = authViewModel.authState.observeAsState()

    val name = Firebase.auth.currentUser?.displayName

    val scrollState = rememberScrollState()

    Column(
        Modifier
            .background(color = Color.White)
            .verticalScroll(state = scrollState)
            .fillMaxSize()
            .height(900.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout {
            val (topImg, searchField) = createRefs()

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(245.dp)
                    .constrainAs(topImg) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .background(
                        color = Color(android.graphics.Color.parseColor("#946534")),
                        shape = RoundedCornerShape(bottomEnd = 48.dp, bottomStart = 40.dp)
                    )
            )

            Row(
                Modifier
                    .padding(top = 48.dp, start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    Modifier
                        .height(100.dp)
                        .padding(start = 14.dp)
                        .weight(0.7f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Selamat Datang", color = Color.White, fontSize = 18.sp)
                    Text(text = name.toString(), Modifier.padding(top = 14.dp), color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
                Image(painter = painterResource(id = R.drawable.group_330), contentDescription = "Bell", modifier = Modifier
                    .align(Alignment.Top)
                    .size(30.dp))
            }

            // Search Field
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 24.dp, end = 24.dp)
                    .shadow(3.dp, shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .constrainAs(searchField) {
                        top.linkTo(topImg.bottom)
                        bottom.linkTo(topImg.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = "Search",
                    modifier = Modifier.padding(10.dp)
                )

                TextField(
                    value = search,
                    onValueChange = { search = it },
                    placeholder = { Text(text = "Mau kudapan apa hari ini ?") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent
                    )
                )
            }
        }

        // Banner
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "banner",
            modifier = Modifier
                .padding(top = 10.dp, start = 24.dp, end = 24.dp)
                .fillMaxWidth()
                .height(170.dp)
        )

        // Makanan List
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 24.dp, end = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Makanan Favorit",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Lihat semua",
                color = Color(0xFF946534),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        CardRecycleView(GoToDetail, makananMinumanViewModel, "makanan", navController)

        // MinumanList
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 24.dp, end = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Minuman Favorit",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Lihat semua",
                color = Color(0xFF946534),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        CardRecycleView(GoToDetail, makananMinumanViewModel = makananMinumanViewModel, "minuman", navController)

    }
}


@Composable
fun CardRecycleView(
    GoToDetail: () -> Unit,
    makananMinumanViewModel: MakananMinumanViewModel,
    type: String,
    navController: NavController
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 24.dp, top = 16.dp, 16.dp)
    ) {

        items(makananMinumanViewModel.makananminuman.size) { index ->
            Spacer(modifier = Modifier.width(8.dp))
            if(makananMinumanViewModel.makananminuman[index].type == type) {
                ItemCards(
                    id = makananMinumanViewModel.makananminuman[index].id,
                    title = makananMinumanViewModel.makananminuman[index].name,
                    image = makananMinumanViewModel.makananminuman[index].image,
                    description = makananMinumanViewModel.makananminuman[index].description,
                    price = makananMinumanViewModel.makananminuman[index].price,
                    quantity = makananMinumanViewModel.makananminuman[index].quantity,
                    navController = navController
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomepagePreview(){
//    Homepage(
//        logout = {
//
//        }
//    )
//}




