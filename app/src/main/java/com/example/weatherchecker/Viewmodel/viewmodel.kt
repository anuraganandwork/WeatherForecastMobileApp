package com.example.weatherchecker.Viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherchecker.data.DataOrException
import com.example.weatherchecker.data.entityClass
import com.example.weatherchecker.model.apiData
import com.example.weatherchecker.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class viewmodel @Inject constructor(val repo : Repository) : ViewModel() {

    private var cityList = MutableStateFlow<List<entityClass>>(emptyList())
    val _cityList  = cityList.asStateFlow()

    private  val unitList = MutableStateFlow<List<Unit>>(emptyList())
    val _unitList = unitList.asStateFlow()
    //}
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getTheList().collect(){
                cityList.value=it
                Log.d("CITY","${_cityList.value}")
            }
//
//        repo.getUnits().collect(){
//             unitList.value=it
//        }
        }

    }
    suspend fun getData(city : String) : DataOrException<apiData, Boolean, Exception> {

        return repo.getWeather(city)
    }


    fun addCity(city: String){
        viewModelScope.launch {
            repo.addCity(city)
        }}


    fun deleteCity(cityClass:entityClass){
        viewModelScope.launch {
            repo.deleteCity(cityClass)
//            repo.getTheList().collect(){
//                cityList.value=it
//                Log.d("Del","city is updated")
//            }
        }
    }






//Recomposition: collect() doesn't trigger recomposition directly, while collectAsState() does.


}

