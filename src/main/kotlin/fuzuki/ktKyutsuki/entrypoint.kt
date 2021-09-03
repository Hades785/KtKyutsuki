package fuzuki.ktKyutsuki

import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.respondEphemeral
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.event.interaction.InteractionCreateEvent
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.rest.builder.interaction.BooleanBuilder
import dev.kord.rest.builder.interaction.StringChoiceBuilder
import fuzuki.ktKyutsuki.commands.systemCommands
import fuzuki.ktKyutsuki.commands.testCommands
import fuzuki.ktKyutsuki.core.commands.CommandHandler
import fuzuki.ktKyutsuki.core.commands.registerAll
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

    client.createGuildApplicationCommands(Snowflake("592674225203052555")) {
        input(
            name = "test",
            description = "Test slash command"
        ) {
            options = mutableListOf(
                StringChoiceBuilder(
                    name = "test_option",
                    description = "A test option"
                ),
                BooleanBuilder(
                    name = "test_boolean",
                    description = "Another test option"
                )
            )
        }
    }

    client.on<InteractionCreateEvent> {
        interaction.respondEphemeral {
            val user = interaction.user.asUser()
            content = """
                Interaction: ${interaction.data.data.name.toString()}
                User: ${user.username}#${user.discriminator} (${user.id.toString()})
            """.trimIndent()
        }
    }

    client.on<ReadyEvent> {
        Logger.log(LogLevel.Info, "キュツキはログインしました")
    }
    client.on<MessageCreateEvent> {
        CommandHandler.handle(message)
    }

    client.login()
}