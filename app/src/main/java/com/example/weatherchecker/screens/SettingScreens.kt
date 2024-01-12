package com.example.weatherchecker.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherchecker.R
import com.example.weatherchecker.Viewmodel.viewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController, viewmodel: viewmodel){
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(painter = painterResource(id = R.drawable.arrow), contentDescription = "Back ",
                        modifier = Modifier
                            .padding(start = 10.dp, end = 20.dp)
                            .size(20.dp)
                            .clickable {
                                navController.popBackStack()
                            })
                    Text(text = "Favourite cities", fontSize = 20.sp)

                }
            }
                ,
//                contentColor = MaterialTheme.colorScheme.copy(onSurface = Color()),
                backgroundColor= Color(31, 93, 188
                ), elevation = 10.dp
            )
        }

    )
    {
            innerpadding->
        Box(modifier = Modifier.padding(innerpadding)){
            androidx.compose.material.Text(text = "This is setting screen")
            exposeddropDown(viewmodel)
        }
    }

}


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun exposeddropDown(viewmodel: viewmodel){

    val listOfItem = arrayOf("Celcius, °C", "Farenheit, °F")
    val expandable = remember{ mutableStateOf(false) }
    val showingText = remember {
        mutableStateOf("Celcius, °C")
    }

    ExposedDropdownMenuBox(expanded = expandable.value, onExpandedChange = {expandable.value=it} ) {

        Column {


            TextField(value = showingText.value , onValueChange = {},
                trailingIcon = { Icon(imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription =" Down arrow",
                    modifier = Modifier.clickable { expandable.value =true }) },

                readOnly = true  )
            if (expandable.value){
                DropdownMenuItem(text = { androidx.compose.material.Text(text = "Celcius, °C") },
                    onClick = {
                        showingText.value= "Celcius, °C"
                        expandable.value=false
                    }    )

                Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.onSurface)

                DropdownMenuItem(text = { androidx.compose.material.Text(text = "Farenheit, °F") },
                    onClick = {
                        showingText.value="Farenheit, °F"
                        expandable.value=false
                    }    )

            }}
    }
}