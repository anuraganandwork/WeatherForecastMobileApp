package com.example.weatherchecker.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherchecker.R
import com.example.weatherchecker.Viewmodel.viewmodel
import com.example.weatherchecker.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(navController: NavController, viewmodel: viewmodel) {
    val keyboardController= LocalSoftwareKeyboardController.current
    val searchedCity = remember{ mutableStateOf("") }
    val defaultCity ="seattle"
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),verticalArrangement = Arrangement.Top) {


        Row( verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()  ) {
            Icon(painter = painterResource(id = R.drawable.arrow), contentDescription =" go Back ",
                modifier = Modifier
                    .size(20.dp)
                    .clickable { navController.navigate(Screens.MainScreen.name+"/${defaultCity}" ) }, tint = Color(
                    77,
                    79,
                    79,
                    255
                )
            )

//        Spacer(modifier = Modifier.padding(horizontal = 15.dp))

            TextField(value = searchedCity.value , onValueChange ={
                searchedCity.value =it
            },
                Modifier.fillMaxWidth().padding(10.dp),
                label = {
                    Text(text = "Search city")
                },
                leadingIcon={}
                ,
                trailingIcon = {},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
//               search(searchedCity.value)
//               searchedCity.value=""
                    keyboardController?.hide()
                    navController.navigate(Screens.MainScreen.name +"/${searchedCity.value}")

                }),
                shape = RoundedCornerShape(10.dp)
            )
        }
    }
}
