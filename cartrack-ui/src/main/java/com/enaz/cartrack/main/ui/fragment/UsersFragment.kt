package com.enaz.cartrack.main.ui.fragment

import com.enaz.cartrack.main.common.fragment.BaseFragment
import com.enaz.cartrack.main.ui.fragment.databinding.UsersFragmentBinding
import com.enaz.cartrack.main.ui.viewmodel.UsersViewModel
import javax.inject.Inject

class UsersFragment : BaseFragment<UsersFragmentBinding, UsersViewModel>() {

    @Inject
    override lateinit var viewModel: UsersViewModel

    override fun createLayout() = R.layout.users_fragment

    override fun getBindingVariable() = BR.usersViewModel

    override fun initViews() {

    }

    override fun subscribeUi() {

    }

    companion object {
        fun newInstance() = UsersFragment()
    }
}
