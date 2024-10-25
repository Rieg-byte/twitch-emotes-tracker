package com.rieg.repository

import com.rieg.model.Channel

interface ChannelRepository {
    fun getChannels(): List<Channel>
}