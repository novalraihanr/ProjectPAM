package com.example.projectpam

import android.os.Bundle
import android.view.textclassifier.TextLinks.TextLinkSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectpam.ui.theme.ProjectPAMTheme

class LoginScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectPAMTheme {
                Login()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Login(){
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Surface(Modifier.fillMaxSize(), color = Color.White) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

            // Image
            Image(painter = painterResource(id = R.drawable.onboardingimage), contentDescription = "Logo",
                Modifier
                    .height(150.dp)
                    .width(300.dp))

            Spacer(modifier = Modifier.padding(20.dp))

            // Text Field
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Email", modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 60.dp))
                OutlinedTextField(value = email , onValueChange = {email = it}, label = { Text(text = "Email")})

                Spacer(modifier = Modifier.padding(10.dp))

                Text(text = "Kata Sandi", modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 60.dp))
                OutlinedTextField(value = password , onValueChange = {password = it}, label = { Text(text = "Password")})

                ClickableText(text = AnnotatedString("Lupa Kata Sandi ?"),
                    Modifier
                        .align(Alignment.End)
                        .padding(end = 60.dp, top = 10.dp), onClick = { offset ->
                    // Do something when clicked
                })
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { /*TODO*/ }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp)
                    .height(50.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF946534)), shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Login")
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Text(text = "atau")

                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp)
                    .height(50.dp), shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF946534)) , border = BorderStroke(1.dp, Color(0xFF946534))
                ) {
                    // Add Google Icon
                    Text(text = "Masuk dengan Google")
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Row {
                Text(text = "Belum memiliki akun ?")
                Spacer(modifier = Modifier.padding(2.dp))
                ClickableText(text = AnnotatedString("Daftar"), style = TextStyle(color = Color(0xFF946534), fontWeight = FontWeight.Bold) ,onClick = { offset ->
                    // Do something when clicked
                })
            }
            Spacer(modifier = Modifier.padding(25.dp))
        }
    }
}
