package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var label by remember { mutableStateOf(5) }

        if(label == 0) label += 5
        else if(label == 6) label -= 5

        val subject = when(label) {
            1 -> "서린이 쿠션"
            2 -> "서린이 디자인 가이드"
            3 -> "서린이 파일 홀더"
            4 -> "서린이 정면샷"
            5 -> "자하연에 둥둥 떠다니는 서린이 \nwith 쀽 or 뺙"
            else -> "error"
        }

        Spacer(modifier = Modifier.height(10.dp))
        ArtWall(label)
        ArtDescription(label, subject)
        Controller(label, increase = {label += 1}, decrease = {label -= 1})
    }
}

@Composable
fun ArtWall(label : Int) {
    val paint = when (label) {
        1 -> R.drawable.cushion
        2 -> R.drawable.design_guide
        3 -> R.drawable.fileholder
        4 -> R.drawable.picture
        5 -> R.drawable.spring
        else -> 0
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(430.dp)
            .width(500.dp)
    ) {
        Image(
            painter = painterResource(id = paint),
            contentDescription = "Seorinee picture",
            modifier = Modifier
                .widthIn(0.dp, 350.dp)
                .heightIn(0.dp, 430.dp)
                .shadow(10.dp)
                .background(color = Color.White)
                .border(BorderStroke(3.dp, Color(110, 130, 0)))
                .padding(vertical = 20.dp, horizontal = 25.dp)
        )
    }
}

@Composable
fun ArtDescription(label : Int, subject : String) {
    val artist = when(label) {
        1 -> "서연"
        2 -> "선형"
        3 -> "정안"
        4 -> "식"
        5 -> "먼지"
        else -> "unknown"
    }

    val year = when(label) {
        1, 3 -> "2023"
        2, 4, 5 -> "2021"
        else -> "unknown"
    }

    Column (
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .height(160.dp)
            .widthIn(0.dp, 350.dp)
            .wrapContentSize()
            .padding(0.dp, top = 0.dp, bottom = 20.dp)
            .shadow(10.dp)
            .background(color = Color.White, shape = RectangleShape)
            .padding(15.dp, 15.dp)
            ) {
        Text(
            text = stringResource(id = R.string.name, subject),
            fontSize = 20.sp,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(artist)
                }
                append(" ($year)")
            }
        )
    }
}

@Composable
fun Controller(label : Int, increase : (Int) -> Unit, decrease : (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {decrease(label)},
            colors = ButtonDefaults.buttonColors(containerColor = Color(109, 0, 218), contentColor = Color.White),
            modifier = Modifier.width(150.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Previous")
        }
        Spacer(modifier = Modifier.width(40.dp))
        Button(
            onClick = {increase(label)},
            colors = ButtonDefaults.buttonColors(containerColor = Color(109, 0, 218), contentColor = Color.White),
            modifier = Modifier.width(150.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtPreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}