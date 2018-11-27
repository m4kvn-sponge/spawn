package com.m4kvn.spawn

import org.spongepowered.api.Sponge
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColors

class Messenger {

    fun info(message: String) {
        val text = Text.builder("[Spawn] $message")
            .color(TextColors.AQUA).build()
        Sponge.getGame().server.console.sendMessage(text)
    }
}