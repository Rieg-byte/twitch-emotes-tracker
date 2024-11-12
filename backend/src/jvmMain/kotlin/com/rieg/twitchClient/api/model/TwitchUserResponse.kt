package com.rieg.twitchClient.api.model

import kotlinx.serialization.Serializable

@Serializable
data class TwitchUserResponse(
    val data: List<TwitchUser>
)