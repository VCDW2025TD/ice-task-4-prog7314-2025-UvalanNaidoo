// app/src/main/java/com/example/memestream1/Prefs.kt
package com.example.memestream1

import android.content.Context
import android.content.SharedPreferences

object Prefs {
    private const val FILE = "auth_prefs"
    private const val KEY_BIOMETRICS_ENABLED = "biometrics_enabled"

    private fun prefs(ctx: Context): SharedPreferences =
        ctx.getSharedPreferences(FILE, Context.MODE_PRIVATE)

    fun setBiometricsEnabled(ctx: Context, enabled: Boolean) {
        prefs(ctx).edit().putBoolean(KEY_BIOMETRICS_ENABLED, enabled).apply()
    }

    fun isBiometricsEnabled(ctx: Context): Boolean =
        prefs(ctx).getBoolean(KEY_BIOMETRICS_ENABLED, false)
}
