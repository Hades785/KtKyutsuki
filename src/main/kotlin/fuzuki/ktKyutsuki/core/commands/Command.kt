package fuzuki.ktKyutsuki.core.commands

import dev.kord.core.entity.Message
import fuzuki.ktKyutsuki.core.logging.LogLevel
import fuzuki.ktKyutsuki.core.logging.Logger

class Command(val name: String, private val run: suspend Message.() -> Unit) {
    suspend fun execute(message: Message) {
        Logger.log(LogLevel.Info, "Command \"$name\" was called.")
        try {
            run(message)
        } catch (e: Exception) {
            Logger.log(LogLevel.Error, "Command \"$name\" has run into an exception: ${e.stackTrace}")
        }
    }
}