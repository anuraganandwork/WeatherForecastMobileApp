package com.example.weatherchecker.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherchecker.R
import com.example.weatherchecker.Viewmodel.viewmodel
import com.example.weatherchecker.navigation.Screens

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(viewmodel: viewmodel, navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(painter = painterResource(id = R.drawable.arrow), contentDescription = "Back ",
                        modifier = Modifier.padding(start= 10.dp, end= 20.dp).size(20.dp)
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

    ) { innerPadding ->
        val list = viewmodel._cityList.collectAsState().value
        Column(Modifier.padding(10.dp)) {
            LazyColumn(modifier = Modifier.padding(innerPadding)){
                items (items = list,
//                    key = {it.id }
                ) {
                    Surface(shape = RoundedCornerShape(15.dp), color = MaterialTheme.colorScheme.surface ,
                        modifier = Modifier.clickable {
                            navController.navigate(Screens.MainScreen.name+"/${it.favouriteCity}")
                        })
                    {


                        Row(horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(10.dp, 15.dp)
                                .fillMaxWidth())
                        {

                            Text(text = it.favouriteCity, color = MaterialTheme.colorScheme.onPrimary)

                            Icon(painter = painterResource(R.drawable.deleteicon), contentDescription = " Delete city",

                                modifier = Modifier
                                    .clickable {
                                        viewmodel.deleteCity(it)
                                    }
                                    .size(30.dp), tint = MaterialTheme.colorScheme.onSurface

                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }
        }

    }

}
//536
