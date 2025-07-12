package com.example.lemonade

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeAPP(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LemonadeAPP(modifier: Modifier = Modifier) {

    var currentStep by remember { mutableStateOf(1) }

    var squeezeCount by remember { mutableStateOf(0) }

    when (currentStep) {
        1 -> {

            LemonTextAndImage(
                text = stringResource(R.string.tree_content_description),
                image = painterResource(R.drawable.lemon_tree),
                contentDescription = stringResource(R.string.tree_content),
                onImageClick = { currentStep = 2
                    squeezeCount = (2..4).random()
                }
            )
        }
        2 -> {

            LemonTextAndImage(
                text = stringResource(R.string.lemon_content_description),
                image = painterResource(R.drawable.lemon_squeeze),
                contentDescription = stringResource(R.string.lemon_content),
                onImageClick = {
                    squeezeCount--
                    if (squeezeCount == 0) {
                        currentStep = 3
                    }
                    }
            )

        }
            3 -> {


                LemonTextAndImage(
                    text = stringResource(R.string.lemonade_content_description),
                    image = painterResource(R.drawable.lemon_drink),
                    contentDescription = stringResource(R.string.lemonade_content),
                    onImageClick = { currentStep = 4 }
                )

        }
        4 -> {
            LemonTextAndImage(
                text = stringResource(R.string.glass_content_description),
                image = painterResource(R.drawable.lemon_restart),
                contentDescription = stringResource(R.string.glass_content),
                onImageClick = { currentStep = 1 }
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize(), // La columna ocupa toda la pantalla
        horizontalAlignment = Alignment.CenterHorizontally // Alinea horizontalmente

    ) {
        Text(
            text = "Lemonade",
            modifier = Modifier
                .fillMaxWidth() // Texto ocupa todo el ancho
                .background(Color.Yellow)
                .padding(16.dp),

            textAlign = TextAlign.Center, // Texto centrado dentro del ancho
            fontWeight = FontWeight.Bold

        )

    }
}

@Composable
fun LemonTextAndImage(
    text: String,
    image: Painter,
    contentDescription: String,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = Modifier
            .fillMaxSize() // llena el espacio restante
            .wrapContentSize(Alignment.Center), // centra el contenido en el centro de la pantalla
        horizontalAlignment = Alignment.CenterHorizontally // alinea el contenido al centro horizontalmente

    ) {
        Image(
            painter = image,
            contentDescription = contentDescription,
            modifier = Modifier
                .clickable{ onImageClick() }
                .border(
                    width = 2.dp,
                    color = Color(red = 105, green = 205, blue = 216),
                    shape = RoundedCornerShape(4.dp)
                )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = text
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeAPP()
    }
}