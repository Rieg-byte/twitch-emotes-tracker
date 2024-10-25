package com.rieg.model

import kotlinx.serialization.Serializable

@Serializable
data class TwitchChannel(
    val twitchId: String,
    val twitchUsername: String,
    val avatarUrl: String,
    val sevenTvId: String
)
