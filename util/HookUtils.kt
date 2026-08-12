package com.vivo.liquidglass.util

import android.content.Context
import de.robv.android.xposed.callbacks.XC_LoadPackage

object HookUtils {
    fun getAppContext(lp: XC_LoadPackage.LoadPackageParam): Context {
        return lp.appContext
    }
}
