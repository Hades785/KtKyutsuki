package fuzuki.ktKyutsuki

import dev.kord.core.Kord
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import fuzuki.ktKyutsuki.commands.systemCommands
import fuzuki.ktKyutsuki.commands.testCommands
import fuzuki.ktKyutsuki.core.commands.*
import fuzuki.ktKyutsuki.core.logging.LogLevel
import fuzuki.ktKyutsuki.core.logging.Logger
import io.github.cdimascio.dotenv.dotenv
import java.io.File
import java.time.OffsetDateTime
import java.time.ZoneOffset

val env = dotenv()

suspend fun main() {
    File("logs").apply { if (!exists()) mkdir() }
    val logFile = File("logs/${OffsetDateTime.now(ZoneOffset.UTC)}.txt")

    Logger.loggerGroup.addAll(
        listOf(
            Logger(
                stream = logFile.outputStream(),
                name = "キュツキ",
                colourOutput = false,
                loggingLevel = listOf(
                    LogLevel.Neutral,
                    LogLevel.Info,
                    LogLevel.Warn,
                    LogLevel.Error
                )
            ),
            Logger(
                stream = System.out,
                name = "キュツキ",
                loggingLevel = listOf(
                    LogLevel.Info,
                    LogLevel.Neutral
                )
            ),
            Logger(
                stream = System.err,
                name = "キュツキ",
                loggingLevel = listOf(
                    LogLevel.Error
                )
            )
        )
    )

    systemCommands.registerAll()
    testCommands.registerAll()

    val client = Kord(env["TOKEN"])

    client.on<ReadyEvent> {
        Logger.log(LogLevel.Info, "キュツキはログインしました")
    }
    client.on<MessageCreateEvent> {
        CommandHandler.handle(message)
    }

    client.login()
}