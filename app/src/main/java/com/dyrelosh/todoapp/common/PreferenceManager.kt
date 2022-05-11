package com.dyrelosh.todoapp.common

import android.content.Context

class PreferenceManager(private val context: Context) {

    companion object {
        private const val PREFERENCE_TOKEN = "token"
        private const val KEY_TOKEN = "TOKEN"
        private const val PREFERENCE_NAME = "name"
        private const val NAME = "NAME"
    }

    fun writeLoginPreference(token: String) {

        val preference = context.getSharedPreferences(PREFERENCE_TOKEN, Context.MODE_PRIVATE)
        preference.edit().putString(KEY_TOKEN, token).apply()
    }

    fun readLoginPreference(): String {

        val preference = context.getSharedPreferences(PREFERENCE_TOKEN, Context.MODE_PRIVATE)
        return preference.getString(KEY_TOKEN, null) ?: ""
    }

    fun deleteLoginPreference() {
        val preference = context.getSharedPreferences(PREFERENCE_TOKEN, Context.MODE_PRIVATE)
        preference.edit().clear().apply()
    }

    fun writePersonNamePreference(name: String) {
        val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        preference.edit().putString(NAME, name).apply()
    }

    fun readPersonNamePreference(): String {
        val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return preference.getString(NAME, null) ?: ""
    }

    fun deletePersonNamePreference() {
        val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        preference.edit().clear().apply()
    }
}