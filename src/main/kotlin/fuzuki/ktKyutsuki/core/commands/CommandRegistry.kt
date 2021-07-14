package fuzuki.ktKyutsuki.core.commands

fun Command.register() = CommandRegistry.register(this)

fun List<Command>.registerAll() = forEach(CommandRegistry::register)

object CommandRegistry {
    private val registry = mutableSetOf<Command>()

    fun register(command: Command) = registry.add(command)
    fun get(name: String): Command? = registry.find { it.name == name }
}