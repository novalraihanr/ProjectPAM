package com.example.projectpam.screen

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.projectpam.R
import com.example.projectpam.ui.theme.ProjectPAMTheme
import com.example.projectpam.viewModel.AuthViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

//class ProfileUpdateScreen : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            ProjectPAMTheme {
//                Scaffold(modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.White)) { innerPadding ->
//                    EditProfileScreen(innerPadding)
//                }
//            }
//        }
//    }
//}

@Composable
fun EditProfileScreen(
    GoBack: () -> Unit,
    authViewModel: AuthViewModel
) {

    var nama by remember {
        mutableStateOf(Firebase.auth.currentUser?.displayName.toString())
    }

    var email by remember {
        mutableStateOf(Firebase.auth.currentUser?.email.toString())
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val laucher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it
    }


    Scaffold(
        bottomBar = {
            Button(
                onClick = {
                    imageUri.let { uri ->
                        authViewModel.updateProfile(nama, uri)
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(android.graphics.Color.parseColor("#946534"))
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Simpan Perubahan",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                )
            }

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Profile",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(25.dp))

                val painter: Painter = if (imageUri != null) {
                    rememberAsyncImagePainter(model = imageUri)
                }else {
                    painterResource(id = R.drawable.image_1_)
                }

                Image(
                    painter = painterResource(id = R.drawable.image_1_) ,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(120.dp)
                        .clickable {
                            laucher.launch("image/*")
                        }
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(Modifier.fillMaxWidth(),  horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Nama", modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 32.dp),
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(value = nama , onValueChange = {nama = it},modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 32.dp)
                    .width(900.dp), label = { Text(text = "Nama")})

                Spacer(modifier = Modifier.padding(10.dp))

                Text(text = "Email", modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 32.dp),
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(value = email , onValueChange = {email = it}, modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 32.dp)
                    .width(900.dp) ,label = { Text(text = "Email")})
            }

        }
    }
}