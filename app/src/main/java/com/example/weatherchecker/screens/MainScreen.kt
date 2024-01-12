package com.example.weatherchecker.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherchecker.Viewmodel.viewmodel
import com.example.weatherchecker.data.DataOrException
import com.example.weatherchecker.model.apiData

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MainScreen(navController: NavController, vm: viewmodel = hiltViewModel(), city: String?){
    showData(v = vm, navController, city)
//    vm.addCity("KOTA")
}

@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun showData(v:viewmodel, navController: NavController, city: String?){

//       105
//    CoroutineScope(Dispatchers.IO).launch{v.addCity("Muzaffarput")

//    v.addCity("Muzaffarpur")



    val weather = produceState<DataOrException<apiData, Boolean, Exception>>(initialValue = DataOrException(loading = true) ){
        value= city?.let { v.getData(it) }!!
    }


    if (weather.value.loading==true){
        CircularProgressIndicator()
    }
    else  if (weather.value.data != null){

        HomeScreen(data = weather.value.data!!, navController,v )

    }


}

//1105