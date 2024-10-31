package com.rieg.model

import kotlinx.serialization.Serializable

@Serializable
data class Channel(
    val twitchId: String,
    val twitchUsername: String,
    val sevenTvId: String
)
