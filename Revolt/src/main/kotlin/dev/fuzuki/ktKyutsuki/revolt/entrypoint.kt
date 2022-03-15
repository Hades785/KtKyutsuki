package dev.fuzuki.ktKyutsuki.revolt

import org.slf4j.LoggerFactory

fun main() {
    val logger = LoggerFactory.getLogger("TEST")

    logger.apply {
        info("blep")
        warn("mlep")
        error("boop")
    }
}