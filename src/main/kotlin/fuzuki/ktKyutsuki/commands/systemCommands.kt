package fuzuki.ktKyutsuki.commands

import fuzuki.ktKyutsuki.core.commands.Command
import fuzuki.ktKyutsuki.core.commands.CommandRegistry

val help = Command("help") {
    val commands = CommandRegistry.getAll()

    val msg = StringBuilder()
    for (command in commands) msg.append(command.name).append("\n")

    channel.createMessage(msg.toString())
}

val systemCommands = listOf(help)