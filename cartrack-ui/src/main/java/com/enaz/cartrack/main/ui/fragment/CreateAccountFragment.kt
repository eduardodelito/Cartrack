package com.enaz.cartrack.main.ui.fragment

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enaz.cartrack.main.client.model.CountriesResponse
import com.enaz.cartrack.main.common.fragment.BaseFragment
import com.enaz.cartrack.main.common.util.hideKeyboard
import com.enaz.cartrack.main.common.util.reObserve
import com.enaz.cartrack.main.common.util.setViewVisibility
import com.enaz.cartrack.main.db.entity.CountriesEntity
import com.enaz.cartrack.main.ui.adapter.CountriesAdapter
import com.enaz.cartrack.main.ui.fragment.databinding.CreateAccountFragmentBinding
import com.enaz.cartrack.main.ui.mapper.countriesEntityToCountriesResponse
import com.enaz.cartrack.main.ui.model.*
import com.enaz.cartrack.main.ui.viewmodel.CreateAccountViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.create_account_fragment.*
import javax.inject.Inject

class CreateAccountFragment : BaseFragment<CreateAccountFragmentBinding, CreateAccountViewModel>() {

    private var listener: OnCreateAccountFragment? = null
    private var dialog: BottomSheetDialog? = null

    @Inject
    override lateinit var viewModel: CreateAccountViewModel

    override fun createLayout() = R.layout.create_account_fragment

    override fun getBindingVariable() = BR.createAccountViewModel

    override fun initViews() {
        listener?.showDetails(false)
        submit_button.setOnClickListener {
            viewModel.submit(
                first_name_field.text.toString(), last_name_field.text.toString(),
                user_name_field.text.toString(), password_field.text.toString()
            )
        }
    }

    override fun subscribeUi() {
        with(viewModel) {
            reObserve(getLoadingLiveData(), ::onLoadingStateChanged)
            reObserve(getCountries(), ::showBottomCountriesDialog)
        }

        country_field.setOnClickListener {
            viewModel.loadCountries()
        }
    }

    private fun onLoadingStateChanged(state: CreateAccountViewState?) {
        when (state) {
            is LoadingModel -> {
                loading_layout.setViewVisibility(state.isLoading)
                enableView(state.isLoading)
                if (!state.isLoading) {
                    hideKeyboard()
                    view?.let { listener?.submit(it) }
                }
            }
            is FailingModel -> {
                if (state.isFailing)
                    loading_layout.setViewVisibility(false)
            }

            is AccountExistModel -> {
                loading_layout.setViewVisibility(false)
                error_message.setViewVisibility(
                    getString(
                        state.message,
                        user_name_field.text.toString()
                    )
                )
            }

            is ValidModel -> {
                submit_button.isEnabled = state.isValid
                if (state.isValid) {
                    submit_button.background =
                        resources.getDrawable(R.drawable.btn_selector_shape, null)
                } else {
                    submit_button.setBackgroundColor(
                        resources.getColor(
                            R.color.disabledColorPrimary,
                            null
                        )
                    )
                }
            }

            is MatchPasswordModel -> {
                if (state.isMatchPassword) {
                    confirm_password_field.error = null
                } else {
                    confirm_password_field.error = getString(R.string.invalid_password)
                }
            }

            is CountryLoadingModel -> loading_layout.setViewVisibility(state.isLoading)
        }
    }

    private fun showBottomCountriesDialog(list: List<CountriesEntity>?) {
        if (!list.isNullOrEmpty()) {
            dialog?.dismiss()
            dialog = context?.let { BottomSheetDialog(it) }
            val countriesAdapter =
                CountriesAdapter(object : CountriesAdapter.OnCountriesAdapterListener {
                    override fun onCountrySelected(countriesResponse: CountriesResponse) {
                        updateCountryField(countriesResponse.name)
                        dialog?.dismiss()
                    }
                })
            val view = layoutInflater.inflate(R.layout.bottom_countriest_layout, null)
            val recyclerView = view.findViewById<RecyclerView>(R.id.countryRecyclerView)
            val layoutManager = LinearLayoutManager(context)
            recyclerView.layoutManager = layoutManager
            recyclerView.addItemDecoration(
                DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            )
            recyclerView.adapter = countriesAdapter
            countriesAdapter.updateData(list.countriesEntityToCountriesResponse())
            dialog?.setContentView(view)
            dialog?.show()
        }
    }

    private fun updateCountryField(name: String?) {
        country_field.setText(name)
    }

    private fun enableView(enable: Boolean) {
        first_name_field.isEnabled = enable
        first_name_field.isClickable = enable
        last_name_field.isEnabled = enable
        last_name_field.isClickable = enable
        user_name_field.isEnabled = enable
        user_name_field.isClickable = enable
        password_field.isEnabled = enable
        password_field.isClickable = enable
        confirm_password_field.isEnabled = enable
        confirm_password_field.isClickable = enable
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
        viewModel.deleteCountries()
    }

    interface OnCreateAccountFragment {
        fun submit(view: View)

        fun showDetails(isVisible: Boolean)
    }

    companion object {
        fun newInstance() = CreateAccountFragment()
    }
}
