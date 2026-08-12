package com.vivo.liquidglass

import com.vivo.liquidglass.hooks.LauncherHook
import com.vivo.liquidglass.hooks.SettingsHook
import com.vivo.liquidglass.hooks.SystemUIHook
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage

class MainHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (lpparam.packageName) {
            "com.android.systemui" -> SystemUIHook().hook(lpparam)
            "com.vivo.launcher" -> LauncherHook().hook(lpparam)
            "com.android.settings" -> SettingsHook().hook(lpparam)
        }
    }
}
