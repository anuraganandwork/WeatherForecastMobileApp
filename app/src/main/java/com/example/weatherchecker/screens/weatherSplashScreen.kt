package com.example.weatherchecker.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherchecker.R
import com.example.weatherchecker.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun weatherSplashScreen(navController: NavController){
    val defaultCity="seattle"

    val scale = remember{
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(0.9f, tween(800, easing = {
            OvershootInterpolator(8f)
                .getInterpolation(it)
        }))

        delay(2000)
        navController.navigate(Screens.MainScreen.name + "/$defaultCity")

    })
    Surface(modifier = Modifier
        .size(330.dp)
        .padding(15.dp)
        .scale(scale.value),
        shape = CircleShape,
        tonalElevation = 5.dp,
        border = BorderStroke(width = 2.dp, color = Color.DarkGray)

    ) {

        Column(modifier = Modifier.padding(1.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(painter = painterResource(id = R.drawable.landscape ), contentDescription ="Icon loading",
                modifier = Modifier.size(40.dp))
            Text(text = "Find the sun?")
        }

    }


}