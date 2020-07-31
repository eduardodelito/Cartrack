package com.enaz.cartrack.main.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.enaz.cartrack.main.client.model.UsersResponse
import com.enaz.cartrack.main.ui.fragment.R
import com.enaz.cartrack.main.ui.fragment.databinding.ItemUserBinding

/**
 * Adapter data binding class to display users in list.
 *
 * Created by eduardo.delito on 7/27/20.
 */
class UsersAdapter(private val listener: OnUsersAdapterListener) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private var list: List<UsersResponse> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val itemUserBinding: ItemUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        return UsersViewHolder(itemUserBinding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.itemUserBinding.usersResponse = list[position]
        holder.itemUserBinding.executePendingBindings()
        holder.itemUserBinding.itemLayout.setOnClickListener {
            listener.onUserSelected(it, list[position])
        }
    }

    override fun getItemCount() = list.size

    /**
     * Method to update list of Users.
     * @param list Users
     */
    fun updateData(list: List<UsersResponse>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class UsersViewHolder(val itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root)

    /**
     * Adapter listener
     */
    interface OnUsersAdapterListener {
        /**
         * onClick method to display details for mobile/tablet.
         * @param view used to navigate fragment.
         * @param UsersResponse data to display details.
         */
        fun onUserSelected(view: View, usersResponse: UsersResponse)
    }
}
