package com.rieg.repository

import com.rieg.database.Channels
import com.rieg.model.Channel
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ChannelRepositoryImpl : ChannelRepository {
    override fun getChannels(): List<Channel> = transaction {
        Channels.selectAll().map {
            Channel(
                twitchId = it[Channels.twitchId],
                twitchUsername = it[Channels.twitchUsername],
                avatarUrl = it[Channels.avatarUrl],
                sevenTvId = it[Channels.sevenTvId]
            )
        }
    }
}