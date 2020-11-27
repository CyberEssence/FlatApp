package com.example.flatapp

import com.example.flatapp.R
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng


class CardActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var gmap: GoogleMap

    companion object {
        var MAP_VIEW_BUNDLE_KEY : String = "MapViewBundleKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_one_item)

        val intent = intent
        val url = intent.getStringExtra("url")
        val latitude = intent.getDoubleExtra("latitude", 0.00)
        val longitude = intent.getDoubleExtra("longitude", 0.00)


        val loc = LatLng(latitude, longitude)


        var image: ImageView = findViewById<ImageView>(R.id.url_image);
        Glide.with(this)
            .load(url)
            .into(image)

        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }

        mapView = findViewById<MapView>(R.id.mapView)
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle)
        }
        mapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val intent = intent
        val latitude = intent.getDoubleExtra("latitude", 0.00)
        val longitude = intent.getDoubleExtra("longitude", 0.00)
        gmap = googleMap
        gmap.setMinZoomPreference(12f)
        val ny = LatLng(latitude, longitude)
        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny))
    }

}