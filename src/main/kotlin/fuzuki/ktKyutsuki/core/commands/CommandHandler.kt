package fuzuki.ktKyutsuki.core.commands

import dev.kord.core.entity.Message
import fuzuki.ktKyutsuki.env
import fuzuki.ktKyutsuki.core.logging.LogLevel
import fuzuki.ktKyutsuki.core.logging.Logger

object CommandHandler {
    suspend fun handle(message: Message) {
        if (message.content.startsWith(env["PREFIX"])) {
            val noPrefix = message.content.split(env["PREFIX"])[1]
            val split = noPrefix.split(" ")
            CommandRegistry.get(split[0])?.execute(message)
                ?: Logger.log(LogLevel.Info, "Command \"${split[0]}\" is not registered.")
        }
    }
}