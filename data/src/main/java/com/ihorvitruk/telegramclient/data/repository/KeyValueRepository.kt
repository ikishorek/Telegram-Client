package com.ihorvitruk.telegramclient.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.ihorvitruk.telegramclient.domain.repository.IKeyValueRepository
import kotlinx.coroutines.experimental.async

class KeyValueRepository(context: Context) : IKeyValueRepository {

    companion object {
        private val KEY_VALUE_FILE = "key_values"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(
            KEY_VALUE_FILE, Context.MODE_PRIVATE)

    override fun create(key: String, value: String?) = async {
        prefs.edit().putString(key, value).apply()
    }

    override fun read(key: String) = async {
        prefs.getString(key, null)
    }

    override fun update(key: String, newValue: String?) = create(key, newValue)

    override fun delete(key: String) = async {
        prefs.edit().remove(key).apply()
    }
}