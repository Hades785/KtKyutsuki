package fuzuki.ktKyutsuki.core.commands

import dev.kord.core.entity.Message
import fuzuki.ktKyutsuki.core.logging.LogLevel
import fuzuki.ktKyutsuki.core.logging.Logger
import fuzuki.ktKyutsuki.env

class Command(val name: String, val description: String, private val run: suspend Message.() -> Unit) {
    companion object {
        fun getCommandArguments(message: Message): List<String> {
            val noPrefix = message.content.split(env["PREFIX"])[1]
            val split = noPrefix.split(" ")
            return split.subList(1, split.count())
        }
    }

    suspend fun execute(message: Message) {
        Logger.log(LogLevel.Info, "Command \"$name\" was called.")
        try {
            run(message)
        } catch (e: Exception) {
            Logger.log(LogLevel.Error, "Command \"$name\" has run into an exception: ${e.stackTrace}")
        }
    }
}