package com.enaz.cartrack.main.ui.fragment

import android.content.Context
import android.view.View
import com.enaz.cartrack.main.common.fragment.BaseFragment
import com.enaz.cartrack.main.common.util.hideKeyboard
import com.enaz.cartrack.main.common.util.reObserve
import com.enaz.cartrack.main.common.util.setViewVisibility
import com.enaz.cartrack.main.ui.fragment.databinding.LoginFragmentBinding
import com.enaz.cartrack.main.ui.model.ErrorModel
import com.enaz.cartrack.main.ui.model.LoginSuccessModel
import com.enaz.cartrack.main.ui.model.LoginValidModel
import com.enaz.cartrack.main.ui.model.LoginViewState
import com.enaz.cartrack.main.ui.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.create_account_fragment.*
import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject

/**
 * Fragment class to validate authentication.
 *
 * Created by eduardo.delito on 7/27/20.
 */
class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>() {

    @Inject
    override lateinit var viewModel: LoginViewModel

    private var listener: OnLoginFragmentListener? = null

    override fun createLayout() =
        R.layout.login_fragment

    override fun getBindingVariable() =
        BR.loginViewModel

    /**
     * Init UI views.
     */
    override fun initViews() {
        listener?.showDetails(false)
        login_button.setOnClickListener {
            viewModel.onLogin(editText_username.text.toString(), editText_password.text.toString())
        }

        create_account.setOnClickListener {
            listener?.onCreateAccount(it)
        }
    }

    /**
     * Subscribe UI into view model.
     */
    override fun subscribeUi() {
        with(viewModel) {
            reObserve(getLoginLiveData(), ::onLoginStateChanged)
        }
    }

    /**
     * Method to update enable/disable login button.
     * @param state
     */
    private fun onLoginStateChanged(state: LoginViewState?) {
        when (state) {
            is LoginValidModel -> {
                login_button.isEnabled = state.isValid
                if (state.isValid) {
                    login_button.background =
                        resources.getDrawable(R.drawable.btn_selector_shape, null)
                } else {
                    login_button.setBackgroundColor(
                        resources.getColor(
                            R.color.disabledColorPrimary,
                            null
                        )
                    )
                }
            }

            is LoginSuccessModel -> {
                if (state.isSuccess) {
                    hideKeyboard()
                    view?.let { listener?.onLogin(it) }
                    clearFields()
                }
            }

            is ErrorModel -> login_error_message.setViewVisibility(getString(state.message))
        }
    }

    /**
     * Reset fields.
     */
    private fun clearFields() {
        editText_username.setText("")
        editText_password.setText("")
        login_error_message.visibility = View.GONE
    }

    /**
     * Init OnLoginFragmentListener listener.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLoginFragmentListener) {
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
    interface OnLoginFragmentListener {
        fun onLogin(view: View)

        fun onCreateAccount(view: View)

        fun showDetails(isVisible: Boolean)
    }

    companion object {
        fun newInstance() =
            LoginFragment()
    }
}
