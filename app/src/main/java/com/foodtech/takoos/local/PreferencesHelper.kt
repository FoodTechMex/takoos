package com.foodtech.takoos.local

import android.content.Context
import android.content.SharedPreferences

object PreferencesHelper {

    private lateinit var prefs: SharedPreferences

    private const val MIS_PREFERENCES = "preference_takoos"
    private const val DEFAULT = ""

    fun init(context: Context) {
        prefs = context.getSharedPreferences(MIS_PREFERENCES, Context.MODE_PRIVATE)
    }

    fun saveString(key: String?, value: String?) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String?): String? {
        return prefs.getString(key, DEFAULT)
    }

    fun saveBoolean(key: String?, value: Boolean) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String?): Boolean {
        return prefs.getBoolean(key, false)
    }

    fun clearData() {
        prefs.edit().clear().apply()
    }
}
