package com.telesoftas.lithuaniaindependencynews.utils.base.fragment
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.telesoftas.lithuaniaindependencynews.R
import com.telesoftas.lithuaniaindependencynews.utils.base.view.BaseNetworkView
import com.telesoftas.lithuaniaindependencynews.utils.dialogs.InfoDialogFragment
import com.telesoftas.lithuaniaindependencynews.utils.network.error.NetworkError
import dagger.android.support.DaggerFragment

abstract class BaseNetworkFragment : DaggerFragment(), BaseNetworkView {

    override fun showErrorDialog(error: NetworkError) {
        InfoDialogFragment.newInstance(error.title, error.message)
                .withAcceptButton(getString(R.string.dialog_button_label_ok))
                .show(activity!!.supportFragmentManager, listener = null)
    }

    override fun hideSoftKeyboard() {
        val view = activity!!.currentFocus
        if (view != null) {
            (activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun showLoadingView() {
        getProgressView()?.visibility = View.VISIBLE
    }

    override fun hideLoadingView() {
        getProgressView()?.visibility = View.GONE
    }

    open fun getProgressView(): View? = null
}