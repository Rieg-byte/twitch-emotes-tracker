package com.rieg.repository.settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class SettingsRepositoryImpl(
    private val settings: Settings
) : SettingsRepository {

    override fun setToken(token: String) {
        settings["access_token"] = token
    }

    override fun getToken(): String {
        return settings["access_token"] ?: ""
    }
}