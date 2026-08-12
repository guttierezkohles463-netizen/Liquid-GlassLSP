package com.vivo.liquidglass.hooks

import android.view.ViewGroup
import com.vivo.liquidglass.config.ConfigManager
import com.vivo.liquidglass.render.LiquidGlassRenderer
import com.vivo.liquidglass.util.HookUtils
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class SystemUIHook {
    fun hook(lpp: XC_LoadPackage.LoadPackageParam) {
        val ctx = HookUtils.getAppContext(lpp)
        val cfg = ConfigManager.getConfig(ctx)
        if (!cfg.enableSystemUI) return

        try {
            XposedHelpers.findAndHookMethod(
                "com.android.systemui.statusbar.phone.PhoneStatusBarView",
                lpp.classLoader,
                "onAttachedToWindow",
                object : XC_MethodHook() {
                    override fun afterHookedMethod(param: MethodHookParam) {
                        val rootView = param.thisObject as ViewGroup
                        LiquidGlassRenderer(rootView.context, cfg).bindRootView(rootView)
                    }
                }
            )
        } catch (_: Throwable) {}
    }
}
