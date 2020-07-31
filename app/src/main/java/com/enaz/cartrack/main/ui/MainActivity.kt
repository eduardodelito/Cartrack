package com.enaz.cartrack.main.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.enaz.cartrack.main.client.model.UsersResponse
import com.enaz.cartrack.main.ui.fragment.*
import dagger.android.support.DaggerAppCompatActivity

/**
 * MainActivity class loaded with navigation calls.
 *
 * Created by eduardo.delito on 7/26/20.
 */
class MainActivity : DaggerAppCompatActivity(), LoginFragment.OnLoginFragmentListener,
    CreateAccountFragment.OnCreateAccountFragmentListener, UsersFragment.OnUsersFragmentListener,
    DetailsFragment.OnDetailsFragmentListener, MapsFragment.OnMapsFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Navigation for the Login authentication to display Users.
     * @param view
     */
    override fun onLogin(view: View) {
        val action = LoginFragmentDirections.actionLoginFragmentToUsersFragment()
        view.findNavController().navigate(action)
    }

    /**
     * Navigation from Login to Create Account.
     * @param view
     */
    override fun onCreateAccount(view: View) {
        val action = LoginFragmentDirections.actionLoginFragmentToCreateAccountFragment()
        view.findNavController().navigate(action)
    }

    /**
     * Navigation from Create Account to Users after the completed registration.
     * @param view
     */
    override fun submit(view: View) {
        val action = CreateAccountFragmentDirections.actionCreateAccountFragmentToUsersFragment()
        view.findNavController().navigate(action)
    }

    /**
     * Navigation from Users list to Details or update
     * if using tablet.
     * @param view
     * @param user data
     */
    override fun navigateToDetails(view: View, user: UsersResponse) {
        val detailsFragment: DetailsFragment? =
            supportFragmentManager.findFragmentById(R.id.detailsFragment) as DetailsFragment?
        if (detailsFragment == null) {
            val bundle = bundleOf(DetailsFragment.USER_ITEM to user)
            val action = UsersFragmentDirections.actionUsersFragmentToDetailsFragment()
            view.findNavController().navigate(action.actionId, bundle)
        } else {
            detailsFragment.updateDetails(user, view)
        }
    }

    /**
     * Navigation from details to Map
     * @param view
     * @param user data
     */
    override fun navigateToMapLocation(view: View, isFromDetails: Boolean, user: UsersResponse?) {
        val bundle = bundleOf(MapsFragment.USER_ITEM to user)

        val action = if (isFromDetails)
            DetailsFragmentDirections.actionDetailsFragmentToMapsFragment()
        else
            UsersFragmentDirections.actionUsersFragmentToMapsFragment()

        view.findNavController().navigate(action.actionId, bundle)
    }

    /**
     * Show/Hide Details since covering master details with in one activity.
     * @param isVisible true if tablet and displaying Users or false otherwise.
     */
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

    /**
     * Load default Users first index to display details.
     * @param user
     * @param view
     */
    override fun loadFirstIndex(user: UsersResponse?, view: View) {
        (supportFragmentManager.findFragmentById(R.id.detailsFragment) as DetailsFragment?)?.updateDetails(
            user,
            view
        )
    }
}
