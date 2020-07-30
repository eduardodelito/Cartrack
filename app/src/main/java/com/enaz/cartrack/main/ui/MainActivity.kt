package com.enaz.cartrack.main.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.enaz.cartrack.main.client.model.UsersResponse
import com.enaz.cartrack.main.ui.fragment.*
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), LoginFragment.OnLoginFragmentListener,
    CreateAccountFragment.OnCreateAccountFragment, UsersFragment.OnUsersFragment,
    DetailsFragment.OnDetailsFragmentListener, MapsFragment.OnMapsFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onLogin(view: View) {
        val action = LoginFragmentDirections.actionLoginFragmentToUsersFragment()
        view.findNavController().navigate(action)
    }

    override fun onCreateAccount(view: View) {
        val action = LoginFragmentDirections.actionLoginFragmentToCreateAccountFragment()
        view.findNavController().navigate(action)
    }

    override fun submit(view: View) {
        val action = CreateAccountFragmentDirections.actionCreateAccountFragmentToUsersFragment()
        view.findNavController().navigate(action)
    }

    override fun navigateToDetails(view: View, usersResponse: UsersResponse) {
        val detailsFragment: DetailsFragment? =
            supportFragmentManager.findFragmentById(R.id.detailsFragment) as DetailsFragment?
        if (detailsFragment == null) {
            val bundle = bundleOf(DetailsFragment.USER_ITEM to usersResponse)
            val action = UsersFragmentDirections.actionUsersFragmentToDetailsFragment()
            view.findNavController().navigate(action.actionId, bundle)
        } else {
            detailsFragment.updateDetails(usersResponse, view)
        }
    }

    override fun navigateToMapLocation(view: View, isFromDetails: Boolean, user: UsersResponse?) {
        val bundle = bundleOf(MapsFragment.USER_ITEM to user)

        val action = if (isFromDetails)
            DetailsFragmentDirections.actionDetailsFragmentToMapsFragment()
        else
            UsersFragmentDirections.actionUsersFragmentToMapsFragment()

        view.findNavController().navigate(action.actionId, bundle)
    }

    override fun showDetails(isVisible: Boolean) {
        val detailsFragment: DetailsFragment? =
            supportFragmentManager.findFragmentById(R.id.detailsFragment) as DetailsFragment?
        if (detailsFragment != null) {
            if (isVisible)
                detailsFragment?.view?.visibility = View.VISIBLE
            else
                detailsFragment?.view?.visibility = View.GONE
        }
    }

    override fun loadFirstIndex(userItem: UsersResponse?, view: View) {
        (supportFragmentManager.findFragmentById(R.id.detailsFragment) as DetailsFragment?)?.updateDetails(
            userItem,
            view
        )
    }
}
