package com.m4kvn.spawn

import com.m4kvn.spawn.command.SpawnCommand
import com.m4kvn.spawn.di.MainModule
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.slf4j.Logger
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GamePreInitializationEvent
import org.spongepowered.api.event.game.state.GameStartingServerEvent
import org.spongepowered.api.plugin.Plugin
import javax.inject.Inject

@Plugin(
    id = "spawn",
    name = "Spawn"
)
class Main : KoinComponent {

    @Inject
    lateinit var logger: Logger

    @Listener
    fun onGamePreInitialization(event: GamePreInitializationEvent) {
        logger.debug("onGamePreInitialization(event=$event)")
        loadKoinModules(listOf(MainModule(this)))
    }

    @Listener
    fun onGameStartingServer(event: GameStartingServerEvent) {
        logger.debug("onGameStartingServer(event=$event)")
        SpawnCommand.register(this)
    }
}