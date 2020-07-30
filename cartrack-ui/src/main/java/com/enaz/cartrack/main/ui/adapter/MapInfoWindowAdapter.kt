package com.enaz.cartrack.main.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
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
        val tvName = view?.findViewById<TextView>(R.id.tv_name)
        val tvPhone = view?.findViewById<TextView>(R.id.tv_phone)
        val tvEmail = view?.findViewById<TextView>(R.id.tv_email)
        val tvAddress = view?.findViewById<TextView>(R.id.tv_address)

        user?.name?.let { tvName?.text = it }
        user?.phone?.let { tvPhone?.text = it }
        user?.email?.let { tvEmail?.text = it }
        user?.address?.let { tvAddress?.text = it.toString() }

        return view
    }

    override fun getInfoWindow(marker: Marker?): View?  = null
}