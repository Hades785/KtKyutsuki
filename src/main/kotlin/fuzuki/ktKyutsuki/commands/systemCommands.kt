package fuzuki.ktKyutsuki.commands

import fuzuki.ktKyutsuki.core.commands.Command
import fuzuki.ktKyutsuki.core.commands.CommandRegistry

val help = Command("help", "List all commands.") {
    val commands = CommandRegistry.getAll()

    val msg = StringBuilder()
    msg.apply {
        for (command in commands) {
            append(command.name)
            append(": ")
            append(command.description)
            append("\n")
        }
    }

    channel.createMessage(msg.toString())
}

val systemCommands = listOf(help)