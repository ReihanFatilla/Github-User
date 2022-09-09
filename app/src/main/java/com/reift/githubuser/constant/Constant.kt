package com.reift.githubuser.constant

import androidx.datastore.preferences.core.booleanPreferencesKey

object Constant {
    const val EXTRA_DETAIL = "INTENT_EXTRA_DETAIL"

    const val BUNDLE_FOLLOW = "BUNDLE_FOLLOW"
    const val BUNDLE_USERNAME = "BUNDLE_USERNAME"

    const val TYPE_FOLLOWERS = "TYPE_FOLLOWERS"
    const val TYPE_FOLLOWING = "TYPE_FOLLOWING"

    val PREF_THEME = booleanPreferencesKey("THEME_SETTINGS")
}