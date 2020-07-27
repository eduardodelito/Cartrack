package com.enaz.cartrack.main.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.enaz.cartrack.main.ui.fragment.CreateAccountFragment
import com.enaz.cartrack.main.ui.fragment.CreateAccountFragmentDirections
import com.enaz.cartrack.main.ui.fragment.LoginFragment
import com.enaz.cartrack.main.ui.fragment.LoginFragmentDirections
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), LoginFragment.OnLoginFragmentListener,
    CreateAccountFragment.OnCreateAccountFragment {
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
}
