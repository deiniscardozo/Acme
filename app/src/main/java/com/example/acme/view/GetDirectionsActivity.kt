package com.example.acme.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.acme.R
import com.example.acme.databinding.ActivityGetDirectionsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GetDirectionsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap:GoogleMap
    private lateinit var binding:ActivityGetDirectionsBinding

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetDirectionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tab = binding.tabLayout
        tab.addTab(tab.newTab().setText("Overview"))
        tab.addTab(tab.newTab().setText("Work Details"))
        tab.addTab(tab.newTab().setText("Purchasing"))
        tab.addTab(tab.newTab().setText("Finishing Up"))
        tab.addTab(tab.newTab().setIcon(R.drawable.ic_baseline_photo_camera_24))

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap:GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}