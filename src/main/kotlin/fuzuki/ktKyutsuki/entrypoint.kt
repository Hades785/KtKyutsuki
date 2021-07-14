package fuzuki.ktKyutsuki

import dev.kord.core.Kord
import fuzuki.ktKyutsuki.core.logging.*
import fuzuki.ktKyutsuki.core.logging.Logger
import fuzuki.ktKyutsuki.core.logging.dsl.loggers
import io.github.cdimascio.dotenv.dotenv
import java.io.File
import java.time.OffsetDateTime
import java.time.ZoneOffset

suspend fun main() {
    val env = dotenv()

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

    val client = Kord(env["TOKEN"])

    loggerGroup.log(LogLevel.Neutral, "キュツキはログインしています")
    loggerGroup.log(LogLevel.Info, "キュツキはログインしています")
    loggerGroup.log(LogLevel.Warn, "キュツキはログインしています")
    loggerGroup.log(LogLevel.Error, "キュツキはログインしています")

    client.login()
}