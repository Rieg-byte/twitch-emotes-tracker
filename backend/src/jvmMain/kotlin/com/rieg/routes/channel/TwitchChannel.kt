package com.rieg.routes.channel

import kotlinx.serialization.Serializable

@Serializable
data class TwitchChannel(
    val username: String
)
