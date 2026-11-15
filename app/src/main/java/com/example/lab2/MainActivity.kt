package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab2.ui.theme.Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen(Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    top = 100.dp,
                    end = 16.dp,
                    bottom = 8.dp
                ).background(Color.White))
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(58.dp)){
    Column(modifier = modifier,
        verticalArrangement = verticalArrangement){
        Title()
        Degrees()
    }
}

@Composable
fun Title() {
    Text(
        text = "Температура",
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Degrees() {
    var count by remember {
        mutableIntStateOf(22)
    }

    var statusName by remember {
        mutableStateOf("Комфортная \nтемпература")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(45.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$count°C",
            fontSize = 70.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
            )
        Buttons(count = count, countChange = {newCount -> count = newCount})

        statusName = if (count < 20) {
            "Прохладно"
        } else if (count > 25) {
            "Жарко"
        } else {
            "Комфортная \nтемпература"
        }
        Status(statusName)
    }
}

@Composable
fun Buttons(count: Int, countChange: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(28.dp, Alignment.CenterHorizontally)
    ) {
        Button(
            onClick = {
                if (count >= 17) {
                    countChange(count-1)
                }
            },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFCEE0FD),
                contentColor = Color.Black
            ),
            modifier = Modifier.height(100.dp).width(150.dp)
        ){
            Text(
                text = "—",
                fontSize = 40.sp
            )
        }

        Button(
            onClick = {
                if (count <= 29) {
                    countChange(count+1)
                }

            },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3B82F6),
                contentColor = Color.White
            ),
            modifier = Modifier.height(100.dp).width(150.dp)
        ){
            Text(
                text = "+",
                fontSize = 40.sp
            )
        }
    }
}

@Composable
fun Status(stausName: String) {
    Card(
        modifier = Modifier.height(150.dp).width(328.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFBAD2F8),
            contentColor = Color.Blue
        ),

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stausName,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold

            )
        }

    }
}

@Preview(showBackground = true)

@Composable
fun MainScreenPreview() {
    MainScreen(Modifier
        .fillMaxSize()
        .padding(
            start = 16.dp,
            top = 100.dp,
            end = 16.dp,
            bottom = 8.dp
        ).background(Color.White))
}
