package com.rieg.plugins

import com.rieg.repository.TwitchChannelRepository
import com.rieg.repository.TwitchChannelRepositoryImpl
import com.rieg.routes.channel.channelRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

const val API_url = "/api"

fun Application.configureRouting() {
    val twitchChannelRepository: TwitchChannelRepository = TwitchChannelRepositoryImpl()
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        frontend()
        route(API_url) {
            channelRoutes(twitchChannelRepository)
        }
    }
}

fun Route.frontend() {
    staticResources("/", "")
}
