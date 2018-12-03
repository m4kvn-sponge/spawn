package com.m4kvn.spawn.di

import com.m4kvn.spawn.Main
import com.m4kvn.spawn.Messenger
import org.koin.core.KoinContext
import org.koin.dsl.context.ModuleDefinition
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

class MainModule(
    private val plugin: Main
) : Module {

    companion object {
        private const val PATH = "spawn"
    }

    override fun invoke(context: KoinContext): ModuleDefinition =
        module { invoke(this) }.invoke(context)

    operator fun invoke(moduleDefinition: ModuleDefinition) =
        moduleDefinition.module(PATH) {
            single { plugin }
            single { Messenger() }
        }
}