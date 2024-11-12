package com.rieg.routes.channel

import com.rieg.model.Channel
import com.rieg.repository.channel.ChannelRepository
import com.rieg.twitchClient.TwitchClient
import com.rieg.twitchClient.api.getUserByLogin
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.channelRoutes(channelRepository: ChannelRepository, twitchClient: TwitchClient) {
    get("/channels") {
        val listChannels = channelRepository.getChannels()
        call.respond(listChannels)
    }

    post("/channels/add") {
        val twitchChannel = call.receive<TwitchChannel>()
        val twitchUser = twitchClient.getUserByLogin(twitchChannel.username)
        val channel = Channel(
            twitchId = twitchUser.id,
            twitchUsername = twitchUser.displayName,
        )
        channelRepository.addChannel(channel)
        call.respondText("Пользователь добавлен", status = HttpStatusCode.OK)
    }
}