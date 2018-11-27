package com.m4kvn.spawn.command

import com.m4kvn.spawn.Main
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import org.spongepowered.api.Sponge
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.CommandSource
import org.spongepowered.api.command.args.CommandContext
import org.spongepowered.api.command.spec.CommandExecutor
import org.spongepowered.api.entity.living.player.Player
import java.util.concurrent.TimeUnit

class SpawnCommand : CommandExecutor, KoinComponent {

    private val plugin: Main by inject()

    override fun execute(src: CommandSource, args: CommandContext): CommandResult =
        if (src is Player) {
            val executer = Sponge.getScheduler().createSyncExecutor(plugin)

            Completable.create { emitter ->
                val worldName = Sponge.getServer().defaultWorldName
                val world = Sponge.getServer().getWorld(worldName).get()
                src.location = world.spawnLocation.asHighestLocation()
                emitter.onComplete()
            }.delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.from(executer))
                .subscribe()

            CommandResult.success()
        } else {
            CommandResult.empty()
        }
}