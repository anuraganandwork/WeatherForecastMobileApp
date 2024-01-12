package com.example.weatherchecker.screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.weatherchecker.R
import com.example.weatherchecker.Viewmodel.viewmodel
import com.example.weatherchecker.model.apiData
import com.example.weatherchecker.navigation.Screens
import com.example.weatherchecker.utility.formatDate
import com.example.weatherchecker.utility.formatTime
import com.example.weatherchecker.utility.getDay

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun HomeScreen(data: apiData, navController: NavController, viewmodel: viewmodel){
    val opendailog = remember{ mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = MaterialTheme.colorScheme.surface, contentColor = MaterialTheme.colorScheme.onPrimary, elevation = 10.dp
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
                ) {

                    Text(text = "Weather app", modifier = Modifier.padding(horizontal = 10.dp), color= MaterialTheme.colorScheme.onPrimary)
//                   Spacer(modifier = Modifier.padding(horizontal = 80.dp))

                    Row {


                        Icon(painter = painterResource(id = R.drawable.iconofsearch), contentDescription =" Srearch icon", modifier = Modifier

                            .scale(1.25f)
                            .align(Alignment.CenterVertically)
                            .clickable { navController.navigate(Screens.SearchScreen.name) } )

                        Spacer(modifier = Modifier.padding(horizontal = 15.dp))
                        Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More icon",
                            modifier = Modifier
                                .scale(1.25f)
                                .clickable {

                                    opendailog.value = true

                                })
                    }

                    DailogBox(opendailog = opendailog, Modifier.fillMaxSize(),navController)
                }

            }
        }

    ) {

            innerPadding ->
        Surface(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            val i =1..5
            val context = LocalContext.current

            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()) {
                    Column {

                        Text(text = data.city.name + ", ${ data.city.country.toString() }", color= MaterialTheme.colorScheme.onPrimary)
                        Text(
                            text = formatDate(data.list[0].dt * 1000),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color= MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Icon(painter = painterResource(id = R.drawable.heart), contentDescription = "Save city"
                        , modifier = Modifier
                            .size(40.dp)
                            .clickable {

                                viewmodel.addCity(data.city.name)
                                Toast
                                    .makeText(context, "This city is saved", Toast.LENGTH_SHORT)
                                    .show()
                            })
                }


                Spacer(modifier = Modifier.padding(20.dp))
                Surface(shape = CircleShape,  modifier = Modifier.size(200.dp), color = MaterialTheme.colorScheme.secondary

                    ,
                    content = {



                        val imgURL = "https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"
                        val tempURP =
                            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally ) {
                                    val temp = (data.list[0].temp.day -32) * (5/9)
                                Image(painter = rememberImagePainter(data =imgURL ),
                                    "Icon loading",modifier = Modifier.size(80.dp),)
                                Text(text = "${data.list[0].temp.day}°", color = Color.Black, fontSize = 30.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
                                Text(text = "${data.list[0].weather[0].description}", color= Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Medium)
//                            Image(painter = Painter(data.list[0].weather[0].icon), contentDescription = )
                            }


                    })
                humidityWindPresureRow(data = data)
                SunsetSunrise(data = data)
//                LazyColumn( ){
//                    items(items=listt, itemContent={
//                        Log.d("COMPOSE", "This get rendered $it")
//                    })
//                }
                Text(text = "This week", fontWeight = FontWeight.Bold, fontSize = 15.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),  color= MaterialTheme.colorScheme.onPrimary)
                ForOtherDays(data = data)




//                    Column {
//                        Text(text = "54", fontStyle = FontStyle.Italic,color= Color(0,0,0))
//                    }
                //1212


            }



        }




//        Text(text = data.city.name, color = Color.White )
        Log.d("GETT","NAME IS  ${data.city.name}")
    }
}


//@Composable
//fun text(a: apiData){
//    Surface(Modifier.background(color= Color(111, 78, 55)
//    ).fillMaxSize()) {
//        Text(text = " Anurag", color = Color.White)
//
//    }
//
//}


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun humidityWindPresureRow(data: apiData){

    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)) {
        Surface(shape = RoundedCornerShape(15.dp), border = BorderStroke(3.dp, MaterialTheme.colorScheme.surface) ) {


            Row(modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(15.dp)) {
                Icon(painter = painterResource(id = R.drawable.weather), contentDescription ="Humidity", modifier = Modifier.size(30.dp) )
                Text(text = data.list[0].humidity.toString() , color= MaterialTheme.colorScheme.onPrimary)
            }
        }


        Surface(shape = RoundedCornerShape(15.dp) , border = BorderStroke(3.dp, MaterialTheme.colorScheme.surface) ){


            Row(modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(15.dp)) {
                Icon(painter = painterResource(id = R.drawable.windenergy), contentDescription ="Speed of wind", modifier = Modifier.size(30.dp) )
                Text(text = data.list[0].speed.toString() , color= MaterialTheme.colorScheme.onPrimary)

            }
        }

        Surface(shape = RoundedCornerShape(10.dp) , border = BorderStroke(3.dp,
            MaterialTheme.colorScheme.surface)
        ){


            Row(modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(15.dp)) {
                Icon(painter = painterResource(id = R.drawable.gauge), contentDescription ="Air pressure" , modifier = Modifier.size(30.dp))
                Text(text = data.list[0].pressure.toString() , color= MaterialTheme.colorScheme.onPrimary)
            }
        }

//952
    }

}


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SunsetSunrise(data: apiData){

    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)) {
        Surface(shape = RoundedCornerShape(10.dp) , border = BorderStroke(3.dp,
            MaterialTheme.colorScheme.surface)
        ){
            Row(modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(15.dp)) {
                Icon(painter = painterResource(id = R.drawable.sunrise), contentDescription = "Sunrise", modifier = Modifier.size(30.dp))
                Text(text = formatTime(data.list[0].sunrise * 1000) , color= MaterialTheme.colorScheme.onPrimary)
            }
        }
        Surface(shape = RoundedCornerShape(10.dp) , border = BorderStroke(3.dp,
            MaterialTheme.colorScheme.surface)
        ){
            Row(modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(15.dp)) {
                Icon(painter = painterResource(id = R.drawable.sunset), "Sunset", modifier = Modifier.size(30.dp))
                Text(text = formatTime(data.list[0].sunset * 1000) , color= MaterialTheme.colorScheme.onPrimary)
            }
        }
    }


}




@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ForOtherDays(data: apiData){
    var info= data.list
    LazyColumn(contentPadding = PaddingValues(start = 3.dp,3.dp,3.dp)){
        items(items= info, itemContent =  {


            Surface(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, end = 10.dp), shape = RoundedCornerShape(10.dp)
                , color = MaterialTheme.colorScheme.surface

            ) {


                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp), verticalAlignment = Alignment.CenterVertically) {

                    val imgURL = "https://openweathermap.org/img/wn/${it.weather[0].icon}.png"
                    Text(text = getDay(it.dt *1000), color = MaterialTheme.colorScheme.onPrimary)
                    Image(painter = rememberImagePainter(data = imgURL), contentDescription = "Situation image " , modifier = Modifier.size(40.dp))

                    Surface(modifier = Modifier
                        .padding(10.dp),



//        .clip(shape = RoundedCornerShape(20.dp))
                        color= MaterialTheme.colorScheme.secondary , shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(text = it.weather[0].description, Modifier.padding(10.dp), color = Color.Black,
                            fontSize = 10.sp
                        )
                    }

                    Row {
                        Text(text = it.temp.max.toString(),  color= MaterialTheme.colorScheme.onPrimary, fontSize = 12.sp)
                        Text(text ="/${it.temp.min.toString()}", color = Color(124, 122, 122, 255), fontSize = 12.sp)

                    }
                }
            }
        })
    }
    //244
}