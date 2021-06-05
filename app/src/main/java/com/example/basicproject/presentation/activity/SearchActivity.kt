package com.example.basicproject.presentation.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.basicproject.WeatherResponse
import com.example.basicproject.data.ConnectionValidator
import com.example.basicproject.data.WeatherRepositoryImpl
import com.example.basicproject.data.api.ApiFactory
import com.example.basicproject.data.model.entity.WeatherEntity
import com.example.basicproject.domain.WeatherRepository
import com.example.basicproject.presentation.rv.CityAdapter
import com.google.android.gms.location.*
import kotlinx.coroutines.launch
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity() {
    lateinit var repo: WeatherRepository
    var listAll = ArrayList<WeatherEntity>()
    private val api = ApiFactory.weatherApi
    private var flag: Boolean = false
    var adapter : CityAdapter? = null
    private var permission: Array<out String> = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
    val PERMISSION_REQUEST_CODE = 1001
    var success: Boolean = true
    var isNetworkEnabled = false
    lateinit var mLastLocation: Location
    private lateinit var mLocationRequest: LocationRequest
    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null
    var first = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        repo = WeatherRepositoryImpl(ApiFactory.weatherApi, this)
        lifecycleScope.launch {
            listAll.addAll(repo.getAroundCity())
        }

        var locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        var isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        isNetworkEnabled = ConnectionValidator().verifyAvailableNetwork(this)
        if (isNetworkEnabled) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!checkPermission(permission)) requestPermissions(permission, PERMISSION_REQUEST_CODE)
                else {
                    if (isGPSEnabled && first) mFusedLocationProviderClient!!.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper())
                    LocationServices.getFusedLocationProviderClient(this).lastLocation.addOnCompleteListener {
                        var lat: Double
                        var lon: Double
                        if (it != null) {
                            while (!it.isComplete) {

                            }
                            lat = it.result.latitude
                            lon = it.result.longitude
                            first = true
                        } else {
                            lat = 0.0
                            lon = 0.0
                        }
                        lifecycleScope.launch {
                            around(lat, lon)
                            forRV()
                            around(lat, lon)
                        }
                        around(lat, lon)
                    }
                }
            }

        } else {
            forRV()
            aroundNetworkDisabled()
        }

        lifecycleScope.launch {
            var listToast = ArrayList<WeatherEntity>()
            listToast.addAll(repo.getAroundCity())
        }
        search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                onHelloClickName(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        button.setOnClickListener {
            var bc: WeatherEntity?
            lifecycleScope.launch {
                bc = repo.getBase()
                if (bc != null) {
                    if (isNetworkEnabled) onHelloClick(bc?.id)
                    else onHelloClickNetworkDisable(bc?.id)
                }
                else Toast.makeText(applicationContext, "Пожалуйста выберите город", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun aroundNetworkDisabled() {
        adapter?.updateData(listAll)
        rv.adapter = CityAdapter(listAll) { id ->
            onHelloClickNetworkDisable(id)
        }
    }

    private fun forRV() {
        if (flag) {
            rv.adapter = CityAdapter(listAll) { id ->
                onHelloClick(id)
            }
        }
        adapter = CityAdapter(listAll) {
            adapter?.updateData(listAll)
        }
    }

    private fun around(lat: Double, lon: Double) {
        lifecycleScope.launch {
            api.getSquareWeather(lat, lon).run {
                var list: ArrayList<WeatherResponse> = ArrayList(this.list)
                var listAll = ArrayList<WeatherEntity>()
                for (i in 0 until list.size) {
                    if (i == 0) listAll.clear()
                    with(list[i]) {
                        listAll.add(WeatherEntity(0, name, id, coord.lat, coord.lon, main.temp.toInt(), weather[0].description, main.feelsLike.toString(), wind.speed.toInt().toString(), calcWind(wind.deg)))
                    }
                }
                repo.saveAroundLocation(listAll)
                adapter?.updateData(listAll)
                rv.adapter = CityAdapter(listAll) { id ->
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
        var tempCity: String
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
                    tempCity = main.temp.toInt().toString()
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

    private fun onHelloClickNetworkDisable(id: Int?) {
        var entity: WeatherEntity?
        lifecycleScope.launch {
            entity = repo.getCityById(id!!)
            val intent = Intent(applicationContext, CheckDetailsActivity::class.java)
            intent.putExtra("ID", entity?.id)
            startActivity(intent)
        }
    }

    private fun onHelloClickName(query: String?) {
        var idCity: Int
        var windSpeedCity: String
        var windDeg: String
        var descr: String
        var tempCity: String
        var cityName: String
        var feel: String
        var entity: WeatherEntity?
        if (isNetworkEnabled) {
            lifecycleScope.launch {
                query?.let {
                    api.getWeather(it).run {
                        cityName = name
                        idCity = id
                        windSpeedCity = wind.speed.toInt().toString()
                        windDeg = calcWind(wind.deg)
                        descr = weather[0].description
                        tempCity = main.temp.toInt().toString()
                        feel = main.feelsLike.toInt().toString()
                        entity = WeatherEntity(1, name, id, coord.lat, coord.lon, tempCity.toInt(), descr, feel, windSpeedCity, windDeg)
                        if (entity != null) repo.saveBase(entity!!)
                        else Toast.makeText(applicationContext, "Город не найден", Toast.LENGTH_SHORT).show()
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
        } else Toast.makeText(applicationContext, "Нет подключения к Интернету", Toast.LENGTH_SHORT).show()
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
                        Toast.makeText(this, "PERM denied", Toast.LENGTH_SHORT).show()
                    } else Toast.makeText(this, "go to settings", Toast.LENGTH_SHORT).show()
                }
            }
            if (allowed) {
                Toast.makeText(this, "PERM granted", Toast.LENGTH_SHORT).show()
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

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            mLastLocation = locationResult.lastLocation
        }
    }
}
