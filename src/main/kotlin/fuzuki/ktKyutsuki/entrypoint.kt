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
import fuzuki.ktKyutsuki.core.logging.dsl.loggers
import io.github.cdimascio.dotenv.dotenv
import java.io.File
import java.time.OffsetDateTime
import java.time.ZoneOffset

val env = dotenv()

suspend fun main() {
    File("logs").apply { if (!exists()) mkdir() }
    val logFile = File("logs/${OffsetDateTime.now(ZoneOffset.UTC)}.txt")

    Logger.loggerGroup.addAll(loggers {
        logger {
            stream(logFile.outputStream())
            name("キュツキ")
            colour(false)
            level(LogLevel.Neutral)
            level(LogLevel.Info)
            level(LogLevel.Warn)
            level(LogLevel.Error)
        }
        logger {
            name("キュツキ")
            level(LogLevel.Info)
            level(LogLevel.Warn)
        }
        logger {
            stream(System.err)
            name("キュツキ")
            level(LogLevel.Error)
        }
    })

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