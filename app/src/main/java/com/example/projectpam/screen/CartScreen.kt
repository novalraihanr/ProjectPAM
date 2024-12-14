package com.example.projectpam.screen

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectpam.R
import com.example.projectpam.components.ItemCardCart
import com.example.projectpam.components.Screen
import com.example.projectpam.ui.theme.ProjectPAMTheme

//class CartsScreen : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            ProjectPAMTheme {
//                Scaffold(modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.White)) { innerPadding ->
//                    CartScreen(innerPadding)
//                }
//            }
//        }
//    }
//}

@Composable
fun CartScreen(
    GoBack: () -> Unit
) {
    Scaffold(
        bottomBar = {
            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(android.graphics.Color.parseColor("#946534"))
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Pesan Sekarang",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                )
            }
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(android.graphics.Color.parseColor("#ffffff")),
                    )
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.group_320),
                    contentDescription = "BackButton",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            GoBack()
                        }
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Kerajang",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            VerticalList()

            Card(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardColors(
                    contentColor = Color.Black,
                    containerColor = Color.White,
                    disabledContentColor = Color.Black,
                    disabledContainerColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(24.dp)
                    )

                    Text(
                        text = "Rp47.000",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(24.dp)
                    )
                }
            }
        }
    }
}

//TODO: Add data from database
@Composable
fun VerticalList() {
    LazyColumn(
        modifier = Modifier
            .background(
                color = Color.White
            ),
        contentPadding = PaddingValues(24.dp)
    ) {
        item {
            ItemCardCart()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}