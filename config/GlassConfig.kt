package com.vivo.liquidglass.config

data class GlassConfig(
    var blurRadius: Float = 28f,
    var dispersion: Float = 35f,
    var refract: Float = 55f,
    var highlightIntensity: Float = 70f,
    var enableSystemUI: Boolean = true,
    var enableLauncher: Boolean = true,
    var enableSettings: Boolean = true
)
