package fuzuki.ktKyutsuki.commands

import fuzuki.ktKyutsuki.core.commands.Command

val help = Command("help") {
    channel.createMessage("TEST")
}

val systemCommands = listOf(help)