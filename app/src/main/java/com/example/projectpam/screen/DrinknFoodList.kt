package com.example.projectpam.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectpam.components.ItemCardsList
import com.example.projectpam.viewModel.MakananMinuman
import com.example.projectpam.viewModel.MakananMinumanViewModel

//@Preview(showBackground = true)
//@Composable
//fun PreviewList() {
//    DrinknFoodList(
//
//        makananMinumanViewModel = MakananMinumanViewModel()
//    )
//}

@Composable
fun DrinknFoodList(
    navController: NavController,
    makananMinumanViewModel: MakananMinumanViewModel
) {
    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White)
        ) {
            Text(
                text = "Menu",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 32.dp, start = 24.dp, end = 24.dp, bottom = 32.dp)
            )

            Text(
                text = "Makanan",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 10.dp)
            )

            GridRecycleView(navController = navController, makananMinumanViewModel = makananMinumanViewModel, type = "makanan")

            Text(
                text = "Minuman",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 10.dp)
            )

            GridRecycleView(navController = navController, makananMinumanViewModel = makananMinumanViewModel, type = "minuman")

        }
    }
}

//TODO: Change this Data to from database, add parameter to differenciate
@Composable
fun GridRecycleView(
    makananMinumanViewModel: MakananMinumanViewModel,
    type: String,
    navController: NavController
) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(vertical = 16.dp),
        columns = GridCells.Fixed(2),
        content = {
            items(makananMinumanViewModel.makananminuman.size) { index ->
                if(makananMinumanViewModel.makananminuman[index].type == type){
                    ItemCardsList(
                        makananMinumanViewModel.makananminuman[index].name,
                        makananMinumanViewModel.makananminuman[index].price.toString(),
                        makananMinumanViewModel.makananminuman[index].image,
                        navController)
                }
            }
        },
        modifier = Modifier.height(620.dp)
    )
}