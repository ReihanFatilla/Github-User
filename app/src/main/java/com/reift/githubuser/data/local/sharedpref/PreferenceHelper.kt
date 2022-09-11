package com.reift.githubuser.data.local.sharedpref

import android.content.Context
import android.content.SharedPreferences
import com.reift.githubuser.constant.Constant

class PreferenceHelper(context: Context) {
    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        pref = context.getSharedPreferences(Constant.PREF_USER, Context.MODE_PRIVATE)
        editor = pref.edit()
    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun remove(key: String){
        editor.remove(key)
    }

    fun getBoolean(key: String): Boolean {
        return pref.getBoolean(key, false)
    }
}