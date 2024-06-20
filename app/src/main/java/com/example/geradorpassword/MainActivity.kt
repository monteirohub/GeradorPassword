package com.example.passwordgenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.geradorpassword.ui.theme.GeradorPasswordTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeradorPasswordTheme {
                PasswordGeneratorApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordGeneratorApp() {
    var password by remember { mutableStateOf("") }
    var length by remember { mutableIntStateOf(8) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Password Generator") }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LengthInput(length = length, onLengthChange = { length = it })
                Spacer(modifier = Modifier.height(16.dp))
                GenerateButton(onClick = { password = generatePassword(length) })
                Spacer(modifier = Modifier.height(16.dp))
                PasswordDisplay(password)
            }
        }
    )

}
@Composable
fun LengthInput(length: Int, onLengthChange: (Int) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Password Length: ")
        Spacer(modifier = Modifier.width(8.dp))
        BasicTextField(
            value = length.toString(),
            onValueChange = {
                val newLength = it.toIntOrNull() ?: length
                onLengthChange(newLength)
            },
            modifier = Modifier
                .width(50.dp)
                .padding(8.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary),
            singleLine = true
        )
    }
}

@Composable
fun GenerateButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Generate Password")
    }
}

@Composable
fun PasswordDisplay(password: String) {
    Text(
        text = password,
        modifier = Modifier.padding(16.dp)
    )
}

fun generatePassword(length: Int): String {
    val charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()"
    return (1..length)
        .map { Random.nextInt(0, charPool.length) }
        .map(charPool::get)
        .joinToString("")
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GeradorPasswordTheme {
        PasswordGeneratorApp()
    }

}