package com.enaz.cartrack.main.common.util

import android.content.Context
import android.graphics.Point
import android.view.WindowManager

/**
 * Util class used by error banner fragment.
 *
 * Created by eduardo.delito on 7/27/20.
 */
object ViewUtil {
    /**
     * Get the screen width in pixels
     *
     * @param context
     * @return screen width
     */
    fun getScreenWidth(context: Context?): Int {
        val windowManager =
            context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }
}
