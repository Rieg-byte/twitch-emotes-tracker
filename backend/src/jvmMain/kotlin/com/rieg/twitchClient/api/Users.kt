package com.rieg.twitchClient.api

import com.rieg.twitchClient.TwitchClient
import com.rieg.twitchClient.api.model.TwitchUser
import com.rieg.twitchClient.api.model.TwitchUserResponse
import io.ktor.client.call.*
import io.ktor.client.request.*


suspend fun TwitchClient.getUserByLogin(login: String): TwitchUser {
    val response: TwitchUserResponse = twitchClient.get("https://api.twitch.tv/helix/users") {
        headers {
            append("Client-ID", twitchConfig.clientId)
        }
        url {
            parameters.append("login", login)
        }
    }.body()
    return response.data[0]
}
