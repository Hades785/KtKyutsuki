package fuzuki.ktKyutsuki.core.logging.dsl

import fuzuki.ktKyutsuki.core.logging.LogLevel
import java.io.OutputStream
import fuzuki.ktKyutsuki.core.logging.Logger as Log

@DslMarker
annotation class LoggerDslMarker

private fun <T> dslInit(t: T, init: T.() -> Unit): T {
    t.init()
    return t
}

fun loggers(init: Loggers.() -> Unit): List<Log> = dslInit(Loggers(), init).build()

@LoggerDslMarker
abstract class DslBuilder<T> {
    internal abstract fun build(): T
}

class Loggers: DslBuilder<List<Log>>() {
    private val loggers = mutableListOf<Logger>()

    fun logger(init: Logger.() -> Unit) = loggers.add(dslInit(Logger(), init))

    override fun build(): List<Log> = loggers.map(Logger::build)
}

class Logger: DslBuilder<Log>() {
    private var stream: OutputStream = System.out
    private val levels = mutableListOf<LogLevel>()
    private var name = ""
    private var charset = "utf8"
    private var colouredOutput = true

    fun stream(stream: OutputStream) {
        this.stream = stream
    }

    fun level(level: LogLevel) {
        levels.add(level)
    }

    fun name(name: String) {
        this.name = name
    }

    fun charset(charset: String) {
        this.charset = charset
    }

    fun colour(colouredOutput: Boolean) {
        this.colouredOutput = colouredOutput
    }

    override fun build(): Log = Log(stream, levels.toList(), name, charset, colouredOutput)
}
