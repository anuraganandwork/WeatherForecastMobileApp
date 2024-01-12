package com.example.weatherchecker.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.weatherchecker.R
import com.example.weatherchecker.navigation.Screens

@SuppressLint("UnrememberedMutableState")
@Composable
fun DailogBox(
    opendailog: MutableState<Boolean>, modifier: Modifier = Modifier.fillMaxSize(), navController: NavController

)
{

    if(opendailog.value) {

        Dialog(onDismissRequest ={opendailog.value=false} ,

            content = {


//1259
                Surface(Modifier.wrapContentSize(), shape = RoundedCornerShape(15.dp)) {


                    Column(modifier = Modifier

                        .padding(20.dp)
                        .wrapContentSize(Alignment.TopEnd)

                        , verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row(
                            Modifier
                                .padding(end = 25.dp)
                                .clickable {
                                    opendailog.value = false
                                    navController.navigate(Screens.FavouriteScreen.name)
                                },
                            verticalAlignment = Alignment.CenterVertically) {
                            Icon(painter = painterResource(id = R.drawable.heart), contentDescription = "text1",
                                Modifier
                                    .size(30.dp)
                                    .padding(start = 10.dp))
                            Text(text = "Favourite", Modifier.padding(start=10.dp))
                        }

//                 Spacer(modifier = )

                        Row(
                            Modifier
                                .padding(end = 25.dp)
                                .clickable {
                                    opendailog.value = false

                                    navController.navigate(Screens.AboutScreen.name)
                                },
                            verticalAlignment = Alignment.CenterVertically) {
                            Icon(painter = painterResource(id = R.drawable.information), contentDescription = "text2",
                                Modifier
                                    .size(30.dp)
                                    .padding(start = 10.dp))
                            Text(text = "About", Modifier.padding(start=10.dp))
                        }


                        Row (
                            Modifier
                                .padding(end = 15.dp)
                                .clickable {
                                    opendailog.value = false

                                    navController.navigate(Screens.SettingsScreen.name)
                                },
                            verticalAlignment = Alignment.CenterVertically){
                            Icon(painter = painterResource(id = R.drawable.settings), contentDescription = "text3",
                                Modifier
                                    .size(30.dp)
                                    .padding(start = 10.dp))
                            Text(text = "Setting", Modifier.padding(start=10.dp))
                        }


                    }
                }
            },
            properties = DialogProperties(dismissOnClickOutside = true, dismissOnBackPress = true)
        )


    }}

