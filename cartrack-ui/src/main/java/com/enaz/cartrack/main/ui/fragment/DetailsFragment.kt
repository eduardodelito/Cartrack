package com.enaz.cartrack.main.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.enaz.cartrack.main.client.model.UsersResponse
import com.enaz.cartrack.main.common.fragment.BaseFragment
import com.enaz.cartrack.main.common.util.reObserve
import com.enaz.cartrack.main.common.util.setViewVisibility
import com.enaz.cartrack.main.ui.fragment.databinding.DetailsFragmentBinding
import com.enaz.cartrack.main.ui.model.AvailableModel
import com.enaz.cartrack.main.ui.model.DetailsViewState
import com.enaz.cartrack.main.ui.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.details_fragment.*
import javax.inject.Inject

/**
 * Fragment class for display user details.
 *
 * Created by eduardo.delito on 7/27/20.
 */
class DetailsFragment : BaseFragment<DetailsFragmentBinding, DetailsViewModel>() {

    private var mUserItem: UsersResponse? = null
    private var listener: OnDetailsFragmentListener? = null
    private var mView: View? = null
    private var isFromDetails = false

    @Inject
    override lateinit var viewModel: DetailsViewModel

    override fun createLayout() = R.layout.details_fragment

    override fun getBindingVariable() = BR.detailsViewModel

    /**
     * Init UI views.
     */
    override fun initViews() {
        mUserItem = arguments?.getSerializable(USER_ITEM) as UsersResponse?
        updateDetails(mUserItem, mView)

        address_location_btn.setOnClickListener {
            if (mView == null) {
                mView = it
                isFromDetails = true
            }
            mView?.let { it1 -> listener?.navigateToMapLocation(it1, isFromDetails, mUserItem) }
        }
    }

    /**
     * Subscribe UI into view model.
     */
    override fun subscribeUi() {
        with(viewModel) {
            reObserve(getDetailsLiveData(), ::onDetailsStateChanged)
        }
    }

    /**
     * Method model state to show/hide details layout.
     * @param state
     */
    private fun onDetailsStateChanged(state: DetailsViewState?) {
        when(state) {
            is AvailableModel -> details_layout.setViewVisibility(state.isAvailable)
        }
    }

    /**
     * Method to update details when selecting item in the list.
     * @param userItem data
     * @param view
     */
    fun updateDetails(userItem: UsersResponse?, view: View?) {
        with(viewModel) {
            user = userItem
            details(userItem)
            mUserItem = userItem
            mView = view;
        }
        getBinding().executePendingBindings()
        getBinding().invalidateAll()
    }

    /**
     * Init OnDetailsFragmentListener listener.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDetailsFragmentListener) {
            listener = context
        }
    }

    /**
     * Reset listener.
     */
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * Reset view.
     */
    override fun onResume() {
        super.onResume()
        if (isFromDetails) mView = null
    }

    /**
     * Action listener interface.
     */
    interface OnDetailsFragmentListener {
        fun navigateToMapLocation(view: View, isFromDetails: Boolean, user: UsersResponse?)
    }

    companion object {
        const val USER_ITEM = "userItem"
        fun newInstance() = DetailsFragment()
    }
}
