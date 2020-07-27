package com.enaz.cartrack.main.common.anim

import android.view.animation.Animation

/**
 * Animation listener
 *
 * Created by eduardo.delito on 7/27/20.
 */
abstract class AnimationListenerAdapter : Animation.AnimationListener {
    /**
     * {@inheritDoc}
     */
    override fun onAnimationStart(animation: Animation?) {
        //NO-OP
    }

    /**
     * {@inheritDoc}
     */
    override fun onAnimationEnd(animation: Animation?) {
        //NO-OP
    }

    /**
     * {@inheritDoc}
     */
    override fun onAnimationRepeat(animation: Animation?) {
        //NO-OP
    }
}
