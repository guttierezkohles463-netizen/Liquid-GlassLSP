package com.vivo.liquidglass.hooks

import android.view.ViewGroup
import com.vivo.liquidglass.config.ConfigManager
import com.vivo.liquidglass.render.LiquidGlassRenderer
import com.vivo.liquidglass.util.HookUtils
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class SettingsHook {
    fun hook(lpp: XC_LoadPackage.LoadPackageParam) {
        val ctx = HookUtils.getAppContext(lpp)
        val cfg = ConfigManager.getConfig(ctx)
        if (!cfg.enableSettings) return

        try {
            XposedHelpers.findAndHookMethod(
                "com.android.settings.SettingsActivity",
                lpp.classLoader,
                "onCreate",
                android.os.Bundle::class.java,
                object : XC_MethodHook() {
                    override fun afterHookedMethod(param: MethodHookParam) {
                        val activity = param.thisObject as android.app.Activity
                        val decor = activity.window.decorView as ViewGroup
                        LiquidGlassRenderer(activity, cfg).bindRootView(decor)
                    }
                }
            )
        } catch (_: Throwable) {}
    }
}
