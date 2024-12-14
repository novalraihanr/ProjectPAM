package com.example.projectpam.screen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectpam.viewModel.AuthState
import com.example.projectpam.viewModel.AuthViewModel

@Composable
fun SignUp(
    GoBackToLogin: () -> Unit,
    authViewModel: AuthViewModel
){
    var nama by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val authState = authViewModel.authState.observeAsState(AuthState.Unauthenticated)

    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.Authenticated -> GoBackToLogin()
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Surface(Modifier.fillMaxSize(), color = Color.White) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = "Daftar" , style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold))

            Spacer(modifier = Modifier.padding(25.dp))
            // Text Field
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Nama", modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 60.dp))
                OutlinedTextField(value = nama , onValueChange = {nama = it}, placeholder = { Text(
                    text = "Masukkan Nama"
                )})

                Spacer(modifier = Modifier.padding(10.dp))

                Text(text = "Email", modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 60.dp))
                OutlinedTextField(value = email , onValueChange = {email = it}, placeholder = { Text(
                    text = "Masukkan Email"
                )})

                Spacer(modifier = Modifier.padding(10.dp))

                Text(text = "Kata Sandi", modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 60.dp))
                OutlinedTextField(value = password , onValueChange = {password = it}, placeholder = { Text(
                    text = "Masukkan Password"
                )})

                Spacer(modifier = Modifier.padding(10.dp))

            }

            Spacer(modifier = Modifier.padding(20.dp))

            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { authViewModel.signup(email, password, nama) }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp)
                    .height(50.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF946534)), shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Daftar")
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Row {
                Text(text = "Sudah memiliki akun?")
                Spacer(modifier = Modifier.padding(2.dp))
                ClickableText(text = AnnotatedString("Masuk"), style = TextStyle(color = Color(0xFF946534), fontWeight = FontWeight.Bold) ,onClick = { offset ->
                    // Do something when clicked
                })
            }
            Spacer(modifier = Modifier.padding(25.dp))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SignUpPreview(){
//    SignUp {
//
//    }
//}