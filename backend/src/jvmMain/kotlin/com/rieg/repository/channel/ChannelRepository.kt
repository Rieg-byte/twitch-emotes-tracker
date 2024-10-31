package com.rieg.repository.channel

import com.rieg.model.Channel

interface ChannelRepository {
    fun getChannels(): List<Channel>
    fun addChannel(channel: Channel)
}