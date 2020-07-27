package com.enaz.cartrack.main.ui.fragment

import android.content.Context
import android.view.View
import com.enaz.cartrack.main.common.fragment.BaseFragment
import com.enaz.cartrack.main.common.util.reObserve
import com.enaz.cartrack.main.ui.fragment.databinding.LoginFragmentBinding
import com.enaz.cartrack.main.ui.model.LoginValidModel
import com.enaz.cartrack.main.ui.model.LoginViewState
import com.enaz.cartrack.main.ui.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject

class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>() {

    @Inject
    override lateinit var viewModel: LoginViewModel

    private var listener: OnLoginFragmentListener? = null

    override fun createLayout() =
        R.layout.login_fragment

    override fun getBindingVariable() =
        BR.loginViewModel

    override fun initViews() {
        login_button.setOnClickListener {
            listener?.onLogin(it)
        }

        create_account.setOnClickListener {
            listener?.onCreateAccount(it)
        }
    }

    override fun subscribeUi() {
        with(viewModel) {
            reObserve(getLoginLiveData(), ::onLoginStateChange)
        }
    }

    private fun onLoginStateChange(state: LoginViewState?) {
        when(state) {
            is LoginValidModel -> {
                login_button.isEnabled = state.isValid
                if (state.isValid) {
                    login_button.background = resources.getDrawable(R.drawable.btn_selector_shape, null)
                } else {
                    login_button.setBackgroundColor(resources.getColor(R.color.disabledColorPrimary, null))
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLoginFragmentListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnLoginFragmentListener {
        fun onLogin(view: View)

        fun onCreateAccount(view: View)
    }

    companion object {
        fun newInstance() =
            LoginFragment()
    }
}
