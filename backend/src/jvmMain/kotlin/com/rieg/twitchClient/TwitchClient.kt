package com.rieg.twitchClient

import com.rieg.repository.settings.SettingsRepository
import com.rieg.twitchClient.model.TokenInfo
import com.rieg.twitchClient.model.TwitchConfig
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*


class TwitchClient(
    val twitchConfig: TwitchConfig,
    private val settingsRepository: SettingsRepository
) {
    val twitchClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
        install(Auth) {
            bearer {
                loadTokens {
                    val token = settingsRepository.getToken()
                    BearerTokens(token, null)
                }
                refreshTokens {
                    val refreshTokenInfo: TokenInfo = client.submitForm(
                        url = "https://id.twitch.tv/oauth2/token",
                        formParameters = parameters {
                            append("client_id", twitchConfig.clientId)
                            append("client_secret", twitchConfig.clientSecret)
                            append("grant_type", "client_credentials")
                        }
                    ).body()
                    settingsRepository.setToken(refreshTokenInfo.accessToken)
                    BearerTokens(settingsRepository.getToken(), null)
                }
            }
        }
    }
}