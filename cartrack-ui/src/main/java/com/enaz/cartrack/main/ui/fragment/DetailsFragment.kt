package com.enaz.cartrack.main.ui.fragment

import com.enaz.cartrack.main.client.UsersResponse
import com.enaz.cartrack.main.common.fragment.BaseFragment
import com.enaz.cartrack.main.common.util.reObserve
import com.enaz.cartrack.main.common.util.setViewVisibility
import com.enaz.cartrack.main.ui.fragment.databinding.DetailsFragmentBinding
import com.enaz.cartrack.main.ui.model.AvailableModel
import com.enaz.cartrack.main.ui.model.DetailsViewState
import com.enaz.cartrack.main.ui.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.details_fragment.*
import javax.inject.Inject

class DetailsFragment : BaseFragment<DetailsFragmentBinding, DetailsViewModel>() {

    @Inject
    override lateinit var viewModel: DetailsViewModel

    override fun createLayout() = R.layout.details_fragment

    override fun getBindingVariable() = BR.detailsViewModel

    override fun initViews() {
        val userItem = arguments?.getSerializable(USER_ITEM) as UsersResponse?
        updateDetails(userItem)
    }

    override fun subscribeUi() {
        with(viewModel) {
            reObserve(getDetailsLiveData(), ::onDetailsStateChanged)
        }
    }

    private fun onDetailsStateChanged(state: DetailsViewState?) {
        when(state) {
            is AvailableModel -> details_layout.setViewVisibility(state.isAvailable)
        }
    }

    fun updateDetails(userItem: UsersResponse?) {
        with(viewModel) {
            user = userItem
            details(userItem)
        }
        getBinding().executePendingBindings()
        getBinding().invalidateAll()
    }

    companion object {
        const val USER_ITEM = "userItem"
        fun newInstance() = DetailsFragment()
    }
}
