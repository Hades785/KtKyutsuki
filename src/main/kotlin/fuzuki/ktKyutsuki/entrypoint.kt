package fuzuki.ktKyutsuki

import dev.kord.core.Kord
import io.github.cdimascio.dotenv.dotenv

suspend fun main() {
    val env = dotenv()

    val client = Kord(env["TOKEN"])

    client.login()
}