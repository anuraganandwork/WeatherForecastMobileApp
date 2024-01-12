package com.example.weatherchecker.utility

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.N)
fun formatDate( date: Int) : String{
    val dateInMillis = date

    val dateFormat= SimpleDateFormat( "MMMM dd, yyyy hh:mm a", Locale.getDefault())
    return dateFormat.format(dateInMillis)


}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun formatTime( date: Int) : String{
    val time = date
    val dateFormat= SimpleDateFormat("hh:mm a", Locale.getDefault())
    return dateFormat.format(time)
}

@RequiresApi(Build.VERSION_CODES.N)
fun getDay(date : Int) :String{
    val time = date
    val dateFormat= SimpleDateFormat("EEEE", Locale.getDefault())
    return dateFormat.format(time)
}
