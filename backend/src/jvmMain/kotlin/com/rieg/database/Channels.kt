package com.rieg.database

import org.jetbrains.exposed.dao.id.IntIdTable

object Channels: IntIdTable("channels") {
    val twitchId = varchar("twitch_id", 50).uniqueIndex()
    val twitchUsername = varchar("twitch_username", 50).uniqueIndex()
    val avatarUrl = text("avatar_url").uniqueIndex()
    val sevenTvId = varchar("seven_tv_id", 50).uniqueIndex()

}