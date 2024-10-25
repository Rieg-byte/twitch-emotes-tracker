package com.rieg.routes.channel

import com.rieg.repository.TwitchChannelRepository
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.channelRoutes(twitchChannelRepository: TwitchChannelRepository) {
    get("/channels") {
        val listChannels = twitchChannelRepository.getChannels()
        call.respond(listChannels)
    }
}