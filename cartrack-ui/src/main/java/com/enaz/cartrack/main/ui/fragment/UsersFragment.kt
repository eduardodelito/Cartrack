package com.enaz.cartrack.main.ui.fragment

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.enaz.cartrack.main.client.UsersResponse
import com.enaz.cartrack.main.common.fragment.BaseFragment
import com.enaz.cartrack.main.common.util.reObserve
import com.enaz.cartrack.main.db.entity.UsersEntity
import com.enaz.cartrack.main.ui.adapter.UsersAdapter
import com.enaz.cartrack.main.ui.fragment.databinding.UsersFragmentBinding
import com.enaz.cartrack.main.ui.mapper.entityModelToUsersResponse
import com.enaz.cartrack.main.ui.model.UsersLoading
import com.enaz.cartrack.main.ui.model.UsersViewState
import com.enaz.cartrack.main.ui.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.users_fragment.*
import javax.inject.Inject

class UsersFragment : BaseFragment<UsersFragmentBinding, UsersViewModel>(),
    SearchView.OnQueryTextListener{

    @Inject
    override lateinit var viewModel: UsersViewModel

    private lateinit var usersAdapter: UsersAdapter

    override fun createLayout() = R.layout.users_fragment

    override fun getBindingVariable() = BR.usersViewModel

    override fun initViews() {
        usersAdapter = UsersAdapter(object : UsersAdapter.OnUsersAdapterListener {
            /**
             * onClick method to display details for mobile/tablet.
             * @param view used to navigate fragment.
             * @param usersResponse data to display details.
             */
            override fun onUserSelected(view: View, usersResponse: UsersResponse) {

            }
        })

        with(recycler_view) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
            adapter = usersAdapter
        }

        search_view.setOnQueryTextListener(this)

        swipe_to_refresh_view.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun subscribeUi() {
        with(viewModel) {
            reObserve(getUsers(), ::onUsersLoaded)

            reObserve(getUsersLiveData(), ::onUsersStateChanged)
            loadUsers()
        }
    }

    private fun onUsersLoaded(list: List<UsersEntity>?) {
        list?.entityModelToUsersResponse()?.let { usersAdapter.updateData(it) }
    }

    private fun onUsersStateChanged(state: UsersViewState?) {
        when(state) {
            is UsersLoading -> swipe_to_refresh_view.isRefreshing = state.isLoading
        }
    }

    override fun onQueryTextSubmit(query: String?) = viewModel.onQueryTextSubmit(query)

    override fun onQueryTextChange(newText: String?) = false

    companion object {
        fun newInstance() = UsersFragment()
    }
}
