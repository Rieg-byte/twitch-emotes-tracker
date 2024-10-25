package com.rieg.repository

import com.rieg.model.TwitchChannel

interface TwitchChannelRepository {
    fun getChannels(): List<TwitchChannel>
}