package fuzuki.ktKyutsuki.core.logging

import com.andreapivetta.kolor.Color
import com.andreapivetta.kolor.Kolor
import java.io.BufferedWriter
import java.io.OutputStream

enum class LogLevel {
    Neutral,
    Info,
    Warn,
    Error
}

fun LogLevel.string(): String {
    return when (this) {
        LogLevel.Neutral -> ""
        LogLevel.Info -> "[INFO]"
        LogLevel.Warn -> "[WARN]"
        LogLevel.Error -> "[ERROR]"
    }
}

fun LogLevel.colour(): Color {
    return when (this) {
        LogLevel.Neutral -> Color.WHITE
        LogLevel.Info -> Color.CYAN
        LogLevel.Warn -> Color.YELLOW
        LogLevel.Error -> Color.RED
    }
}

class Logger(
    stream: OutputStream,
    private val loggingLevel: List<LogLevel>,
    private val name: String,
    charset: String,
    private val colourOutput: Boolean
) {
    companion object {
        val loggerGroup = mutableListOf<Logger>()

        fun log(level: LogLevel, message: String) = loggerGroup.forEach { it.log(level, message) }
    }

    private val stream: BufferedWriter = BufferedWriter(stream.writer(charset(charset)))

    private fun log(level: LogLevel, message: String) {
        if (level !in loggingLevel) return

        val levelStr = level.string()
        val fmtMessage = if (levelStr.isEmpty()) "[$name] $message\n" else "[$name] ${level.string()} $message\n"
        stream.apply {
            write(
                if (colourOutput) Kolor.foreground(fmtMessage, level.colour())
                else fmtMessage
            )
            flush()
        }
    }
}