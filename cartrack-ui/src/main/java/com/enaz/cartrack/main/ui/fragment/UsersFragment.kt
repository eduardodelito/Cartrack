package com.enaz.cartrack.main.ui.fragment

import android.content.Context
import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enaz.cartrack.main.client.model.UsersResponse
import com.enaz.cartrack.main.common.fragment.BaseFragment
import com.enaz.cartrack.main.common.util.reObserve
import com.enaz.cartrack.main.common.util.setViewVisibility
import com.enaz.cartrack.main.db.entity.UsersEntity
import com.enaz.cartrack.main.ui.adapter.UsersAdapter
import com.enaz.cartrack.main.ui.fragment.databinding.UsersFragmentBinding
import com.enaz.cartrack.main.ui.mapper.entityModelToUsersResponse
import com.enaz.cartrack.main.ui.model.ProgressLoading
import com.enaz.cartrack.main.ui.model.UsersLoading
import com.enaz.cartrack.main.ui.model.UsersViewState
import com.enaz.cartrack.main.ui.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.users_fragment.*
import javax.inject.Inject

/**
 * Fragment class for display Users in list.
 *
 * Created by eduardo.delito on 7/27/20.
 */
class UsersFragment : BaseFragment<UsersFragmentBinding, UsersViewModel>(){

    @Inject
    override lateinit var viewModel: UsersViewModel

    private var listener: OnUsersFragmentListener? = null

    private lateinit var usersAdapter: UsersAdapter

    override fun createLayout() = R.layout.users_fragment

    override fun getBindingVariable() = BR.usersViewModel

    /**
     * Init UI views.
     */
    override fun initViews() {
        listener?.showDetails(true)
        usersAdapter = UsersAdapter(object : UsersAdapter.OnUsersAdapterListener {
            /**
             * onClick method to display details for mobile/tablet.
             * @param view used to navigate fragment.
             * @param usersResponse data to display details.
             */
            override fun onUserSelected(view: View, usersResponse: UsersResponse) {
                listener?.navigateToDetails(view, usersResponse)
            }
        })

        with(recycler_view) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(object :
                DividerItemDecoration(context, LinearLayoutManager.VERTICAL) {
                override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                    // Do not draw divider line
                }
            })
            adapter = usersAdapter
        }

        swipe_to_refresh_view.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    /**
     * Subscribe UI into view model.
     */
    override fun subscribeUi() {
        with(viewModel) {
            reObserve(getUsers(), ::onUsersLoaded)

            reObserve(getUsersLiveData(), ::onUsersStateChanged)
            loadUsers()
        }
    }

    /**
     * Method to display Users in list.
     * @param list data.
     */
    private fun onUsersLoaded(list: List<UsersEntity>?) {
        list?.entityModelToUsersResponse()?.let {
            if (!it.isNullOrEmpty()) {
                usersAdapter.updateData(it)
                view?.let { it1 -> listener?.loadFirstIndex(it[0], it1) }
            }
        }
    }

    /**
     * Method state model to show/hide swipe and progress loading.
     * @param state
     */
    private fun onUsersStateChanged(state: UsersViewState?) {
        when(state) {
            is UsersLoading -> swipe_to_refresh_view.isRefreshing = state.isLoading
            is ProgressLoading -> progress_view.setViewVisibility(state.isLoading)
        }
    }

    /**
     * Init OnUsersFragmentListener listener.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnUsersFragmentListener) {
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
     * Action listener interface.
     */
    interface OnUsersFragmentListener {
        fun navigateToDetails(view: View, usersResponse: UsersResponse)

        fun showDetails(isVisible: Boolean)

        /**
         * Display first index as default details.
         * @param userItem data to display details.
         */
        fun loadFirstIndex(userItem: UsersResponse?, view: View)
    }

    companion object {
        fun newInstance() = UsersFragment()
    }
}
