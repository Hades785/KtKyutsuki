package fuzuki.ktKyutsuki.commands

import fuzuki.ktKyutsuki.core.commands.Command

val test = Command("test", "Test command.") {
    channel.createMessage("Test command ran.")
}

val echo = Command("echo", "Makes the bot say your message.") {
    val args = Command.getCommandArguments(this)
    channel.createMessage(args.joinToString(" "))
}

val testCommands = listOf(test, echo)