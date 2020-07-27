package com.enaz.cartrack.main.common.util

import android.view.View
import androidx.appcompat.widget.AppCompatTextView

/**
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
