package com.rieg.repository

import com.rieg.database.Channels
import com.rieg.model.TwitchChannel
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class TwitchChannelRepositoryImpl : TwitchChannelRepository {
    override fun getChannels(): List<TwitchChannel> = transaction {
        Channels.selectAll().map {
            TwitchChannel(
                twitchId = it[Channels.twitchId],
                twitchUsername = it[Channels.twitchUsername],
                avatarUrl = it[Channels.avatarUrl],
                sevenTvId = it[Channels.sevenTvId]
            )
        }
    }
}