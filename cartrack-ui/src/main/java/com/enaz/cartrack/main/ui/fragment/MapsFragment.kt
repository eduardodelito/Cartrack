package com.enaz.cartrack.main.ui.fragment

import android.content.Context
import com.enaz.cartrack.main.common.fragment.BaseFragment
import com.enaz.cartrack.main.ui.fragment.databinding.MapsFragmentBinding
import com.enaz.cartrack.main.ui.viewmodel.MapsViewModel
import javax.inject.Inject

class MapsFragment : BaseFragment<MapsFragmentBinding, MapsViewModel>() {
    private var listener: OnMapsFragmentListener? = null

    @Inject
    override lateinit var viewModel: MapsViewModel

    override fun createLayout() = R.layout.maps_fragment

    override fun getBindingVariable() = BR.mapsViewModel

    override fun initViews() {
        listener?.showDetails(false)
    }

    override fun subscribeUi() {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMapsFragmentListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnMapsFragmentListener {
        fun showDetails(isVisible: Boolean)
    }

    companion object {
        fun newInstance() = MapsFragment()
    }
}