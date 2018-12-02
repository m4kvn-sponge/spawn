package com.m4kvn.spawn

import com.m4kvn.spawn.command.SpawnCommand
import com.m4kvn.spawn.di.MainModule
import org.koin.log.EmptyLogger
import org.koin.standalone.StandAloneContext.startKoin
import org.spongepowered.api.Sponge
import org.spongepowered.api.command.spec.CommandSpec
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GameStartedServerEvent
import org.spongepowered.api.plugin.Plugin

@Plugin(
    id = "spawn",
    name = "Spawn"
)
class Main {

    @Listener
    fun onGameStarted(event: GameStartedServerEvent) {
        startKoin(listOf(MainModule(this)), logger = EmptyLogger())
        SpawnCommand.register(this)
    }
}