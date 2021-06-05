package com.example.basicproject

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager

import android.os.Build
import android.os.Bundle

import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.basicproject.rv.CheckDetailsActivity
import com.example.basicproject.rv.CityAdapter
import com.example.basicproject.rv.CityObjects
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.coroutines.launch
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity() {
    private val api = ApiFactory.weatherApi
    private val cityApi = ApiCity.cityApi
    private var flag: Boolean = false
    var adapter : CityAdapter? = null
    private var permission: Array<out String> = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    val PERMISSION_REQUEST_CODE = 1001
    var success: Boolean = true
    var lat: Double = 25.204849
    var lon: Double = 55.270783

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkPermission(permission)) requestPermissions(permission, PERMISSION_REQUEST_CODE)
            else {
                var loc = LocationServices.getFusedLocationProviderClient(this).lastLocation
                    .addOnSuccessListener (this, OnSuccessListener {
                        if (it != null) {
                            lon = it.longitude
                        }
                    })
            }
        }
        if (flag) {
            rv.adapter = CityAdapter(CityObjects.cityObjects) { id ->
                onHelloClick(id)
            }
        }
        adapter = CityAdapter(CityObjects.cityObjects) {
            CityObjects.cityObjects.removeAt(it)
            CityObjects.clear()
            adapter?.updateData(CityObjects.cityObjects)
        }
        around()
        search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                var idCity: Int? = null
                lifecycleScope.launch {
                    var list: List<CityApi.CityX>
                    list = cityApi.getId().city
                    Toast.makeText(applicationContext, list.size.toString() + "234567г8ш9", Toast.LENGTH_SHORT).show()
                    for (i in 0..list.size) {
                        if (list[i].name.equals(query)) idCity = list[i].cityId.toInt()
                    }
                }
                onHelloClickName(query)
                if (idCity != null) onHelloClick(idCity)
                else Toast.makeText(applicationContext, "Город не найден", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

    }

    private fun around() {
        lifecycleScope.launch {
            api.getSquareWeather(lat, lon).run {
                if (flag) CityObjects.clear()
                CityObjects.clear()
                for (i in list.indices) {
                    CityObjects.addItem(list[i])
                }
                adapter?.updateData(CityObjects.cityObjects)
                rv.adapter = CityAdapter(CityObjects.cityObjects) { id ->
                    onHelloClick(id)
                }
            }
        }
    }

    private fun onHelloClick(query: Int?) {
        var idCity: Int
        var windSpeedCity: String
        var windDeg: String
        var descr: String
        var tempCity: Int
        var cityName: String
        var feel: String
        lifecycleScope.launch {
            query?.let {
                api.getWeather(it).run {
                    cityName = name
                    idCity = id
                    windSpeedCity = wind.speed.toInt().toString()
                    windDeg = calcWind(wind.deg)
                    descr = weather[0].description
                    tempCity = main.tempr.toInt()
                    feel = main.feelsLike.toInt().toString()
                }
                val intent = Intent(applicationContext, CheckDetailsActivity::class.java)
                intent.putExtra("ID", idCity)
                intent.putExtra("WIND_SPEED", windSpeedCity)
                intent.putExtra("DESCR", descr)
                intent.putExtra("TEMP", tempCity)
                intent.putExtra("NAME", cityName)
                intent.putExtra("WIND_DEG", windDeg)
                intent.putExtra("FEEL", feel)
                startActivity(intent)
            }
        }
    }

    private fun onHelloClickName(query: String?) {
        var idCity: Int
        var windSpeed: String
        var windDeg: String
        var descr: String
        var tempCity: Int
        var cityName: String
        var feel: String
        lifecycleScope.launch {
            query?.let {
                api.getWeather(it).run {
                    cityName = name
                    idCity = id
                    windSpeed = wind.speed.toInt().toString()
                    windDeg = calcWind(wind.deg)
                    descr = weather[0].description
                    tempCity = main.tempr.toInt()
                    feel = main.feelsLike.toInt().toString()
                }
                val intent = Intent(applicationContext, CheckDetailsActivity::class.java)
                intent.putExtra("ID", idCity)
                intent.putExtra("WIND_SPEED", windSpeed)
                intent.putExtra("DESCR", descr)
                intent.putExtra("TEMP", tempCity)
                intent.putExtra("NAME", cityName)
                intent.putExtra("WIND_DEG", windDeg)
                intent.putExtra("FEEL", feel)
                startActivity(intent)
            }
        }
    }

    private fun checkPermission(permissionArray: Array<out String>) : Boolean {
        success = true
        for (i in permission.indices) {
            if (checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED) {
                success = false
            }
        }
        return success
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            var allowed = true
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    allowed = false
                    var requestAgain = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(permissions[i])
                    if (requestAgain) {
                        Toast.makeText(this,"PERM denied", Toast.LENGTH_SHORT).show()
                    } else Toast.makeText(this,"go to settings", Toast.LENGTH_SHORT).show()
                }
            }
            if (allowed) {
                Toast.makeText(this,"PERM granted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calcWind(wind: Int): String {
        if (wind >= 338) return "северный"
        if (wind <= 22) return "северный"
        if (wind >= 23 && wind <= 67) return "северно-восточный"
        if (wind >= 68 && wind <= 112) return "восточный"
        if (wind >= 113 && wind <= 157) return "юго-восточный"
        if (wind >= 158 && wind <= 202) return "южный"
        if (wind >= 203 && wind <= 247) return "юго-западный"
        if (wind >= 248 && wind <= 292) return "западный"
        if (wind >= 293 && wind <= 337) return "северо-западный"
        return ""
    }

}

