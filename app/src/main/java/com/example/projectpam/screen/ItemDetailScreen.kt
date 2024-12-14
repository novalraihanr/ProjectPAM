package com.example.projectpam.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.KeyEventDispatcher.Component
import androidx.navigation.NavArgs
import coil3.compose.AsyncImage
import com.example.projectpam.R
import com.example.projectpam.RootNav
import com.example.projectpam.components.Screen
import com.example.projectpam.ui.theme.ProjectPAMTheme
import com.example.projectpam.viewModel.AuthViewModel
import com.example.projectpam.viewModel.MakananMinumanViewModel

//class DetailScreen : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            ProjectPAMTheme {
//                Scaffold(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.White)) { innerPadding ->
//                    ItemDetailScreen(innerPadding)
//                }
//            }
//        }
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun DetailPreview() {
//    ItemDetailScreen(GoBack = {
//
//    })
//}

@Composable
fun ItemDetailScreen(
    GoBack: () -> Unit,
    screen: Screen.ItemDetailScreen,
    makananMinumanViewModel: MakananMinumanViewModel,
    ) {
    Scaffold(
        bottomBar = {
            Button(
                onClick = { makananMinumanViewModel.insertOrder(
                    name = screen.title,
                    price = screen.price,
                    type = screen.type,
                    description = screen.description,
                    image = screen.image
                ) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(android.graphics.Color.parseColor("#946534"))
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "+ Keranjang",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                )
            }
        },

    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = Color(android.graphics.Color.parseColor("#946534")))
        ) {
            ConstraintLayout {
                val (topImg, picture, Img, title, submitButton) = createRefs()

                Image(
                    painter = painterResource(id = R.drawable.path4190),
                    contentDescription = "picture",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier
                        .size(width = 500.dp, height = 350.dp)
                        .constrainAs(picture) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, start = 24.dp, end = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //TODO: Make this Clickable
                    Image(
                        painter = painterResource(id = R.drawable.group_320_1_),
                        contentDescription = "Back",
                        modifier = Modifier.size(35.dp)
                            .clickable {
                                GoBack()
                            }
                    )

                    Text(
                        text = "Detail",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    //TODO: Make this Clickable
                    Image(
                        painter = painterResource(id = R.drawable.group_322),
                        contentDescription = "Like",
                        modifier = Modifier.size(35.dp)
                            .clickable {

                            }
                    )

                }


                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(900.dp)
                        .padding(top = 100.dp)
                        .constrainAs(topImg) {
                            top.linkTo(picture.bottom)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        }
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                        )
                )


                //TODO: Replace this with AsyncImage
//                Image(
//                    painter = painterResource(id = R.drawable.imageplaceholder),
//                    contentDescription = "placeholder",
//                    modifier = Modifier
//                        .size(width = 500.dp, height = 170.dp)
//                        .constrainAs(Img) {
//                            top.linkTo(picture.bottom)
//                            bottom.linkTo(topImg.top)
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//                        }
//                )
                AsyncImage(
                    model = screen.image,
                    contentDescription = "placeholder",
                    modifier = Modifier
                        .size(width = 500.dp, height = 170.dp)
                        .constrainAs(Img) {
                            top.linkTo(picture.bottom)
                            bottom.linkTo(topImg.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                // Title + Description
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(intrinsicSize = IntrinsicSize.Max)
                        .verticalScroll(state = rememberScrollState())
                        .constrainAs(title) {
                            top.linkTo(Img.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement =  Arrangement.SpaceBetween
                ) {
                    Text(
                        text = screen.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = 32.dp)
                    )

                    Text(
                        text = "Rp${screen.price}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(top = 12.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(intrinsicSize = IntrinsicSize.Max)
                            .padding(start = 24.dp, end = 24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        VerticalDivider(
                            modifier = Modifier
                                .width(1.dp)
                                .padding(start = 10.dp, end = 10.dp, top = 25.dp),
                            color = Color(android.graphics.Color.parseColor("#946534")),
                            thickness = 2.dp
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = screen.description,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                        )
                    }
                }

            }
        }

    }
}
