package com.enaz.cartrack.main.ui.fragment

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import com.enaz.cartrack.main.client.model.UsersResponse
import com.enaz.cartrack.main.ui.adapter.MapInfoWindowAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : SupportMapFragment(), OnMapReadyCallback {

    private var user: UsersResponse? = null
    private var mMap: GoogleMap?= null

    private var listener: OnMapsFragmentListener? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        user = arguments?.getSerializable(USER_ITEM) as UsersResponse?
        listener?.showDetails(false)
        getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
        if (activity?.let { checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) } == PackageManager.PERMISSION_GRANTED) {
            mMap?.isMyLocationEnabled = true
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE_USER_LOCATION_PERMISSION)
        }
        mMap?.apply {
                    val latLng = user?.address?.geo?.lng?.toDouble()?.let {  user?.address?.geo?.lat?.toDouble()?.let { it1 ->
                        LatLng(
                            it1, it)
                    } }
                    val markerOption = latLng?.let {
                        MarkerOptions()
                            .position(it)
                    }

                    val marker = addMarker(markerOption)
                    marker.tag = user

                    moveCamera(CameraUpdateFactory.newLatLng(latLng))
                    moveCamera(CameraUpdateFactory.zoomTo(MAP_ZOOM))

                    val infoWindowAdapter = MapInfoWindowAdapter(requireActivity())
                    setInfoWindowAdapter(infoWindowAdapter)

                    marker.showInfoWindow()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_USER_LOCATION_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    if (activity?.let {
                            ActivityCompat.checkSelfPermission(
                                it,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            )
                        } != PackageManager.PERMISSION_GRANTED && activity?.let {
                            ActivityCompat.checkSelfPermission(
                                it,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        } != PackageManager.PERMISSION_GRANTED
                    ) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return
                    }
                    mMap?.isMyLocationEnabled = true
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMapsFragmentListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnMapsFragmentListener {
        fun showDetails(isVisible: Boolean)
    }

    companion object {
        const val USER_ITEM = "userItem"
        const val REQUEST_CODE_USER_LOCATION_PERMISSION = 1002
        const val MAP_ZOOM = 17.0f
        fun newInstance() = MapsFragment()
    }
}
