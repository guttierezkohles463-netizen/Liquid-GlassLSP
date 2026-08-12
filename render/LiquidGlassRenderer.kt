package com.vivo.liquidglass.render

import android.content.Context
import android.view.ViewGroup
import com.vivo.liquidglass.config.GlassConfig

class LiquidGlassRenderer(ctx: Context, cfg: GlassConfig) {
    private val glassShader = LiquidGlassShader.getShader(
        cfg.blurRadius, cfg.dispersion, cfg.refract
    )
    private var overlayView: LiquidGlassOverlay? = null

    fun bindRootView(parent: ViewGroup) {
        overlayView = LiquidGlassOverlay(parent.context, glassShader)
        parent.addView(overlayView)
    }

    fun removeOverlay() {
        overlayView?.parent?.let {
            (it as ViewGroup).removeView(overlayView)
        }
    }
}
