package com.enaz.cartrack.main.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.enaz.cartrack.main.client.model.CountriesResponse
import com.enaz.cartrack.main.ui.fragment.R
import com.enaz.cartrack.main.ui.fragment.databinding.ItemCountryBinding

/**
 * Adapter class to get all countries.
 *
 * Source API: https://restcountries.eu/#api-endpoints-all
 *
 * Created by eduardo.delito on 7/29/20.
 */
class CountriesAdapter(private val listener: OnCountriesAdapterListener) :
    RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    private var list: List<CountriesResponse> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val itemCountryBinding: ItemCountryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_country,
            parent,
            false
        )
        return CountriesViewHolder(itemCountryBinding)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.itemCountryBinding.countryResponse = list[position]
        holder.itemCountryBinding.executePendingBindings()
        holder.itemCountryBinding.itemCountryLayout.setOnClickListener {
            listener.onCountrySelected(list[position])
        }
    }

    override fun getItemCount() = list.size

    /**
     * Method to update list of Users.
     * @param list Users
     */
    fun updateData(list: List<CountriesResponse>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class CountriesViewHolder(val itemCountryBinding: ItemCountryBinding) :
        RecyclerView.ViewHolder(itemCountryBinding.root)

    /**
     * Adapter listener
     */
    interface OnCountriesAdapterListener {
        /**
         * onClick method to select country.
         * @param countriesResponse data to display details.
         */
        fun onCountrySelected(countriesResponse: CountriesResponse)
    }
}
