package com.enaz.cartrack.main.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.enaz.cartrack.main.client.model.UsersResponse
import com.enaz.cartrack.main.ui.fragment.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

/**
 * Created by eduardo.delito on 7/30/20.
 */
class MapInfoWindowAdapter (private val context: Context) : GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker?): View? {
        val user = marker?.tag as? UsersResponse

        val view = (context as? Activity)?.layoutInflater?.inflate(R.layout.map_info_window, null)
        val mapName = view?.findViewById<AppCompatTextView>(R.id.map_name)
        val mapAddress = view?.findViewById<AppCompatTextView>(R.id.map_address)
        mapName?.text = user?.name
        mapAddress?.text = view?.context?.getString(R.string.map_address, user?.address?.street, user?.address?.suite, user?.address?.city, user?.address?.zipcode)

        return view
    }

    override fun getInfoWindow(marker: Marker?): View?  = null
}