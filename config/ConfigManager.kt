package com.vivo.liquidglass.config

import android.content.Context
import android.content.SharedPreferences

object ConfigManager {
    private const val SP_NAME = "liquid_glass_cfg"

    fun getConfig(ctx: Context): GlassConfig {
        val sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        return GlassConfig(
            blurRadius = sp.getFloat("blur", 28f),
            dispersion = sp.getFloat("disp", 35f),
            refract = sp.getFloat("refr", 55f),
            highlightIntensity = sp.getFloat("hl", 70f),
            enableSystemUI = sp.getBoolean("sysui", true),
            enableLauncher = sp.getBoolean("launcher", true),
            enableSettings = sp.getBoolean("setting", true)
        )
    }

    fun save(ctx: Context, cfg: GlassConfig) {
        val sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit()
        sp.putFloat("blur", cfg.blurRadius)
        sp.putFloat("disp", cfg.dispersion)
        sp.putFloat("refr", cfg.refract)
        sp.putFloat("hl", cfg.highlightIntensity)
        sp.putBoolean("sysui", cfg.enableSystemUI)
        sp.putBoolean("launcher", cfg.enableLauncher)
        sp.putBoolean("setting", cfg.enableSettings)
        sp.apply()
    }
}
