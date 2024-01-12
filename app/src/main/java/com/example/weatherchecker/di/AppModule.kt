package com.example.weatherchecker.di

import android.content.Context
import androidx.room.Room
import com.example.weatherchecker.data.CityDao
import com.example.weatherchecker.data.Database
import com.example.weatherchecker.network.weatherApi
import com.example.weatherchecker.utility.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOpenWeatherApi(): weatherApi {
        return  Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(weatherApi::class.java)
    }
    @Singleton
    @Provides
    fun provideDao(db:Database): CityDao {
        return  db.itemDao()
    }

    @Volatile
    var instance: Database?= null

    @Singleton
    @Provides
    fun provideDatabase( @ApplicationContext context : Context) : Database{


        return instance?: synchronized(this){
            Room.databaseBuilder(context,Database::class.java,"favouriteCity_db")
        }.build().also { instance =it }




    }
}
