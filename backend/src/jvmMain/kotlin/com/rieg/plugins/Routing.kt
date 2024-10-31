package com.rieg.plugins

import com.rieg.repository.channel.ChannelRepository
import com.rieg.repository.channel.ChannelRepositoryImpl
import com.rieg.routes.channel.channelRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

const val API_url = "/api"

fun Application.configureRouting() {
    val channelRepository: ChannelRepository = ChannelRepositoryImpl()
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        frontend()
        route(API_url) {
            channelRoutes(channelRepository)
        }
    }
}

fun Route.frontend() {
    staticResources("/", "")
}
