package com.vivo.liquidglass.hooks

import android.view.ViewGroup
import com.vivo.liquidglass.config.ConfigManager
import com.vivo.liquidglass.render.LiquidGlassRenderer
import com.vivo.liquidglass.util.HookUtils
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class LauncherHook {
    fun hook(lpp: XC_LoadPackage.LoadPackageParam) {
        val ctx = HookUtils.getAppContext(lpp)
        val cfg = ConfigManager.getConfig(ctx)
        if (!cfg.enableLauncher) return

        try {
            XposedHelpers.findAndHookMethod(
                "com.vivo.launcher.LauncherRootView",
                lpp.classLoader,
                "onAttachedToWindow",
                object : XC_MethodHook() {
                    override fun afterHookedMethod(param: MethodHookParam) {
                        val root = param.thisObject as ViewGroup
                        LiquidGlassRenderer(root.context, cfg).bindRootView(root)
                    }
                }
            )
        } catch (_: Throwable) {}
    }
}
