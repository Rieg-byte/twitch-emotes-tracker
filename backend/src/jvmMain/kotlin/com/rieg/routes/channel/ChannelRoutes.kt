package com.rieg.routes.channel

import com.rieg.model.Channel
import com.rieg.repository.channel.ChannelRepository
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.channelRoutes(channelRepository: ChannelRepository) {
    get("/channels") {
        val listChannels = channelRepository.getChannels()
        call.respond(listChannels)
    }

    post("/channels/add") {
        val channel = call.receive<Channel>()
        channelRepository.addChannel(channel)
    }
}