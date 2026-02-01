package com.example.lab2parte2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

//clases especiales,se usan cuando se quieren trabajar con valores limitados,
// como los colores del semaforo
enum class Light {
    Red, Yellow, Green
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TrafficLight()
            }
        }
    }
}

@Composable
fun TrafficLight() {

    var currentLight by remember { mutableStateOf(Light.Red) }

    // esta es la logica del semaforo
    LaunchedEffect(Unit) {
        while (true) {
            currentLight = Light.Red
            delay(2000)

            currentLight = Light.Green
            delay(2000)

            currentLight = Light.Yellow
            delay(1000)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), // <- Fondo negro
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        LightCircle(
            isActive = currentLight == Light.Red,
            activeColor = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))

        LightCircle(
            isActive = currentLight == Light.Yellow,
            activeColor = Color.Yellow
        )

        Spacer(modifier = Modifier.height(16.dp))

        LightCircle(
            isActive = currentLight == Light.Green,
            activeColor = Color.Green
        )
    }
} // q

@Composable
fun LightCircle(
    isActive: Boolean,
    activeColor: Color
) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(
                if (isActive) activeColor else Color.Gray
            )
    )
}