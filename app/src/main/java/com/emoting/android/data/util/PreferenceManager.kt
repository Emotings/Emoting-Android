package com.emoting.android.data.util

import android.content.Context
import androidx.core.content.edit
import com.emoting.android.util.PreferenceKeys

internal class PreferenceManager(context: Context) {
    private val sharedPreference =
        context.getSharedPreferences(PreferenceKeys.PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun putValue(
        key: String,
        value: String,
    ) = sharedPreference.edit {
        this.putString(key, value)
    }

    fun getValue(key: String) = sharedPreference.getString(key, "")
}
