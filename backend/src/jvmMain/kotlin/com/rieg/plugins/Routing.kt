package com.rieg.plugins

import com.rieg.repository.channel.ChannelRepository
import com.rieg.repository.channel.ChannelRepositoryImpl
import com.rieg.repository.settings.SettingsRepositoryImpl
import com.rieg.routes.channel.channelRoutes
import com.rieg.twitchClient.TwitchClient
import com.rieg.twitchClient.model.TwitchConfig
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.prefs.Preferences

const val API_URL = "/api"

fun Application.configureRouting() {
    val delegate: Preferences = Preferences.userRoot()
    val settings: Settings = PreferencesSettings(delegate)
    val settingsRepository = SettingsRepositoryImpl(settings)
    val channelRepository: ChannelRepository = ChannelRepositoryImpl()
    val twitchConfig = TwitchConfig(
        clientId = System.getenv("TWITCH_CLIENT_ID"),
        clientSecret = System.getenv("TWITCH_CLIENT_SECRET"),
    )
    val twitchClient = TwitchClient(
        twitchConfig = twitchConfig,
        settingsRepository = settingsRepository
    )
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        frontend()
        route(API_URL) {
            channelRoutes(channelRepository, twitchClient)
        }
    }
}

fun Route.frontend() {
    staticResources("/", "")
}
