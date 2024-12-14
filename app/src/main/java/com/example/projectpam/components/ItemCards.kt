package com.example.projectpam.components

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.projectpam.R
import kotlin.reflect.typeOf


//TODO: Set it as Clickable
@Composable
fun ItemCards(
    id: Int,
    title: String,
    price: Int,
    description: String,
    quantity: Int,
    image: String,
    type: String,
    navController: NavController
) {
   Card(
       colors = CardDefaults.cardColors(
           containerColor = Color.White
       ),
       onClick = {
           navController.navigate(Screen.ItemDetailScreen(
               id = id,
               title = title,
               price = price,
               description = description,
               quantity = quantity,
               image = image,
               type = type
           ))
                 },
       modifier = Modifier
           .size(width = 129.dp, height = 103.dp)
   ) {
       AsyncImage(
           model = image,
           contentDescription = "placeholder",
           modifier = Modifier
               .size(width = 80.dp, height = 80.dp)
               .align(Alignment.CenterHorizontally)
       )

//       Image(
//           painter = painterResource(id = R.drawable.imageplaceholder),
//           contentDescription = "Placeholder",
//           modifier = Modifier
//               .size(width = 80.dp, 80.dp)
//               .align(Alignment.CenterHorizontally)
//       )

       Text(
           text = title,
           fontSize = 12.sp,
           color = Color.Black,
           fontWeight = FontWeight.Bold,
           modifier = Modifier
               .align(Alignment.CenterHorizontally)
               .padding(top = 2.dp)
       )
   }
}

//TODO: Set it as Clickable
@Composable
fun ItemCardsList(
    title: String,
    price: String,
    image: String,
    navController: NavController
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = {

        },
        modifier = Modifier
            .size(width = 148.dp, height = 140.dp)
    ) {
        AsyncImage(
            model = image,
            contentDescription = "image",
            modifier = Modifier
                .size(width = 90.dp, height = 80.dp)
                .align(Alignment.CenterHorizontally)
        )

//        Image(
//            painter = painterResource(id = R.drawable.imageplaceholder),
//            contentDescription = "Placeholder",
//            modifier = Modifier
//                .size(width = 110.dp, 100.dp)
//                .align(Alignment.CenterHorizontally)
//        )

        Text(
            text = title,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Rp$price",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

    }
}

//TODO: Add data from database
@Composable
fun ItemCardCart(
    title: String,
    price: String,
    image: String
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContentColor = Color.White,
            disabledContainerColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier,
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 24.dp, start = 24.dp)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Rp$price",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(start = 24.dp)
                )
            }
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

//                Image(
//                    painter = painterResource(id = R.drawable.imageplaceholder),
//                    contentDescription = "Image",
//                    modifier = Modifier
//                        .width(150.dp)
//                        .height(100.dp)
//                        .padding(12.dp)
//                )

                AsyncImage(
                    model = image,
                    contentDescription = "Image",
                    modifier = Modifier
                        .width(150.dp)
                        .height(100.dp)
                        .padding(12.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .width(110.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.minus_button),
                        contentDescription = "Minus Button",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                //TODO: Decrement Text
                            }
                    )

                    Text(
                        text = "1",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )

                    Image(
                        painter = painterResource(id = R.drawable.plus_button),
                        contentDescription = "Minus Button",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                //TODO: Increment Text
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun ItemCardOrder(names: String, total: Int, image: String) {

    val split : List<String> = names.split(",")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(bottom = 24.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
//            Text(
//                text = "31 Okt 2024, 18:25",
//                fontSize = 14.sp,
//                color = Color.Black,
//                fontWeight = FontWeight.Bold,
//            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                AsyncImage(
                    model = image,
                    contentDescription = "Placeholder",
                    modifier = Modifier
                        .width(120.dp)
                        .height(100.dp)
                )

//                Image(
//                    painter = painterResource(id = R.drawable.imageplaceholder),
//                    contentDescription = "Placeholder",
//                    modifier = Modifier
//                        .width(120.dp)
//                        .height(100.dp)
//                )

                Spacer(modifier = Modifier.width(24.dp))

                Column(
                    modifier = Modifier,
                ) {
                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.SpaceAround,
                    ) {
                        ListOrder(split)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Total",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Rp$total",
                        fontSize = 24.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

//TODO: Implement Data from Database
@Composable
fun ListOrder(names: List<String>) {
    LazyColumn(
        modifier = Modifier,
    ) {
        items(names.size) { index ->
            Text(
                text = names[index],
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }
    }
}