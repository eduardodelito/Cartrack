package com.enaz.cartrack.main.ui.fragment

import android.content.Context
import android.view.View
import com.enaz.cartrack.main.common.fragment.BaseFragment
import com.enaz.cartrack.main.common.util.reObserve
import com.enaz.cartrack.main.common.util.setViewVisibility
import com.enaz.cartrack.main.ui.fragment.databinding.CreateAccountFragmentBinding
import com.enaz.cartrack.main.ui.model.AccountExistModel
import com.enaz.cartrack.main.ui.model.FailingModel
import com.enaz.cartrack.main.ui.model.LoadingModel
import com.enaz.cartrack.main.ui.model.LoadingViewState
import com.enaz.cartrack.main.ui.viewmodel.CreateAccountViewModel
import kotlinx.android.synthetic.main.create_account_fragment.*
import javax.inject.Inject

class CreateAccountFragment : BaseFragment<CreateAccountFragmentBinding, CreateAccountViewModel>() {

    private var listener: OnCreateAccountFragment? = null

    @Inject
    override lateinit var viewModel: CreateAccountViewModel

    override fun createLayout() = R.layout.create_account_fragment

    override fun getBindingVariable() = BR.createAccountViewModel

    override fun initViews() {
        submit_button.setOnClickListener {
            viewModel.submit(first_name_field.text.toString(), last_name_field.text.toString(),
                user_name_field.text.toString(), password_field.text.toString())
        }
    }

    override fun subscribeUi() {
        with(viewModel) {
            reObserve(getLoadingLiveData(), ::onLoadingStateChanged)
        }
    }

    private fun onLoadingStateChanged(state: LoadingViewState?) {
        when(state) {
            is LoadingModel -> {
                loading_layout.setViewVisibility(state.isLoading)
                if (!state.isLoading) view?.let { listener?.submit(it) }
            }
            is FailingModel -> {
                if (state.isFailing)
                    loading_layout.setViewVisibility(false)
            }

            is AccountExistModel -> {
                loading_layout.setViewVisibility(false)
                error_message.setViewVisibility(getString(state.message, user_name_field.text.toString()))
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCreateAccountFragment) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnCreateAccountFragment {
        fun submit(view: View)
    }

    companion object {
        fun newInstance() = CreateAccountFragment()
    }
}