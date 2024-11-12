package com.rieg.repository.settings

interface SettingsRepository {
    fun setToken(token: String)
    fun getToken(): String
}