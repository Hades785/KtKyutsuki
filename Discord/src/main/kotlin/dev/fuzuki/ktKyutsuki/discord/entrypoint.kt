package dev.fuzuki.ktKyutsuki.discord

import dev.kord.core.Kord
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.on
import io.github.cdimascio.dotenv.dotenv
import org.slf4j.LoggerFactory

val env = dotenv()

suspend fun main() {
    val client = Kord(env["TOKEN"])

    client.on<ReadyEvent> {
        val logger = LoggerFactory.getLogger("TEST")
//        LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)
        logger.trace("BLEP")
        logger.debug("BLEP")
        logger.info("BLEP")
        logger.warn("BLEP")
        logger.error("BLEP")
    }

    client.login()
}