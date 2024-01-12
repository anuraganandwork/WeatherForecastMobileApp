package com.example.weatherchecker.repo

import android.util.Log
import com.example.weatherchecker.data.DataOrException
import com.example.weatherchecker.data.Degree
import com.example.weatherchecker.data.CityDao
import com.example.weatherchecker.data.entityClass
import com.example.weatherchecker.model.apiData
import com.example.weatherchecker.network.weatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
class Repository @Inject constructor(val api : weatherApi, val dao: CityDao) {

    suspend fun getWeather(City : String) : DataOrException<apiData, Boolean, Exception>{

        val response = try {

            api.getWeather(query = City)

        }
        catch (e: Exception){
            Log.d("REX", " $e")
            return DataOrException(exception = e)
        }
        Log.d("REX", " Get REX $response")

        return DataOrException(data = response)
    }


    suspend fun addCity(city: String){
        dao.insert(entityClass(favouriteCity = city))
    }
    suspend fun deleteCity(cityClass: entityClass){
        dao.delete(cityClass)
    }

    suspend fun getTheList() : Flow<List<entityClass>> {
        return    dao.getTheList().flowOn(Dispatchers.IO).conflate()
    }

//    suspend fun getUnits(): Flow<List<Degree>> {
//         return dao.getUnits().flowOn(Dispatchers.IO).conflate()
//    }
//
//    suspend fun insertUnit(u:Degree){
//        dao.insertUnit(u)
//    }
//
//    suspend fun deleteUnit(u:Degree){
//        dao.deleteUnit(u)
//    }



}

//243