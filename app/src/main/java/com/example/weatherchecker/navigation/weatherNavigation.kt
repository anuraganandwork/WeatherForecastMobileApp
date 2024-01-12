package com.example.weatherchecker.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherchecker.Viewmodel.viewmodel
import com.example.weatherchecker.screens.AboutScreen
import com.example.weatherchecker.screens.FavouriteScreen
import com.example.weatherchecker.screens.MainScreen
import com.example.weatherchecker.screens.SearchScreen
import com.example.weatherchecker.screens.SettingScreen
import com.example.weatherchecker.screens.weatherSplashScreen

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun WeatherNavigation() {

    val navController = rememberNavController()
    val vm = hiltViewModel<viewmodel>()

    suspend fun searching(city: String){
        vm.getData(city)
    }


    NavHost(navController = navController, startDestination =Screens.SplashScreen.name){

        composable(Screens.SplashScreen.name){
            weatherSplashScreen(navController = navController )

        }
        val route = Screens.MainScreen.name
        composable("$route/{city}", arguments = listOf(
            navArgument(name = "city"){
                type= NavType.StringType
            }
        )){

            it.arguments?.getString("city").let {
                MainScreen(navController= navController, vm, city = it)

            }
        }

        composable(Screens.SearchScreen.name){
            SearchScreen(navController = navController, vm  )
        }

        composable(Screens.FavouriteScreen.name){
            FavouriteScreen(vm, navController)
        }
        composable(Screens.AboutScreen.name){
            AboutScreen()
        }
        composable(Screens.SettingsScreen.name){
            SettingScreen(navController,vm)
        }

    }}