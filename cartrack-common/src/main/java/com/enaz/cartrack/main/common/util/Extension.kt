package com.enaz.cartrack.main.common.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment

/**
 * Extension file
 *
 * Created by eduardo.delito on 7/26/20.
 */
fun View.setViewVisibility(isVisible: Boolean) {
    with(this) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}

/**
 * Enable/Disable AppCompatTextView.
 * @param message set message value if available.
 */
fun AppCompatTextView.setViewVisibility(message: String?) {
    with(this) {
        visibility = message?.let {
            text = message
            View.VISIBLE
        } ?: View.GONE
    }
}

/**
 * Hide Keyboard in the Fragment.
 */
fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

/**
 * Hide Keyboard in the Activity.
 */
fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

/**
 * Hide keyboard method.
 */
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
