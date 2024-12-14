package com.example.projectpam.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectpam.components.ItemCardOrder
import com.example.projectpam.viewModel.MakananMinumanViewModel

@Composable
fun OrderScreen(
    makananMinumanViewModel: MakananMinumanViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 24.dp, start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Orders",
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        HorizontalDivider()

        ListOrderCard(makananMinumanViewModel)
    }
}

//TODO: Implement this with data from database
@Composable
fun ListOrderCard(
    makananMinumanViewModel: MakananMinumanViewModel
) {
    LazyColumn(
        modifier = Modifier,
        contentPadding = PaddingValues(top = 24.dp)
    ) {
        items(makananMinumanViewModel.history.size) { index ->
            ItemCardOrder(
                names = makananMinumanViewModel.history[index].names,
                total = makananMinumanViewModel.history[index].total,
                image = makananMinumanViewModel.history[index].image
            )
        }
    }
}