package com.example.weatherchecker.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(){
//    Text(text = "About")//    Text(text = "About")


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "About", fontSize = 20.sp) },
//                contentColor = MaterialTheme.colorScheme.copy(onSurface = Color()),
                backgroundColor= Color(31, 93, 188
                ), elevation = 10.dp
            )
        }

    ) { innerPadding ->
        Column(Modifier.padding(10.dp)) {
            Text(text = "WeatherAPI is being called here to show all the info", modifier = Modifier.padding(innerPadding))

        }

    }




}
