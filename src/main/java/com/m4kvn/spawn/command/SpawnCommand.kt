package com.m4kvn.spawn.command

import org.koin.standalone.KoinComponent
import org.spongepowered.api.Sponge
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.CommandSource
import org.spongepowered.api.command.args.CommandContext
import org.spongepowered.api.command.spec.CommandExecutor
import org.spongepowered.api.entity.living.player.Player

class SpawnCommand : CommandExecutor, KoinComponent {

    override fun execute(src: CommandSource, args: CommandContext): CommandResult =
        if (src is Player) {
            val worldName = Sponge.getServer().defaultWorldName
            val world = Sponge.getServer().getWorld(worldName).get()
            src.location = world.spawnLocation.asHighestLocation()
            CommandResult.success()
        } else {
            CommandResult.empty()
        }
}