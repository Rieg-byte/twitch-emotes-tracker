package com.rieg.routes.channel

import com.rieg.repository.ChannelRepository
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.channelRoutes(channelRepository: ChannelRepository) {
    get("/channels") {
        val listChannels = channelRepository.getChannels()
        call.respond(listChannels)
    }
}