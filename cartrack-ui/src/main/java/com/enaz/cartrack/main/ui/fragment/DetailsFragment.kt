package com.enaz.cartrack.main.ui.fragment

import com.enaz.cartrack.main.client.UsersResponse
import com.enaz.cartrack.main.common.fragment.BaseFragment
import com.enaz.cartrack.main.ui.fragment.databinding.DetailsFragmentBinding
import javax.inject.Inject

class DetailsFragment : BaseFragment<DetailsFragmentBinding, DetailsViewModel>() {

    @Inject
    override lateinit var viewModel: DetailsViewModel

    override fun createLayout() = R.layout.details_fragment

    override fun getBindingVariable() = BR.detailsViewModel

    override fun initViews() {
        val userItem = arguments?.getSerializable(USER_ITEM) as UsersResponse?
        println("name======${userItem?.name}")
    }

    override fun subscribeUi() {

    }

    companion object {
        const val USER_ITEM = "userItem"
        fun newInstance() = DetailsFragment()
    }
}
