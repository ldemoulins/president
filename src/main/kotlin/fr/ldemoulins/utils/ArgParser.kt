package fr.ldemoulins.utils

object ArgParser {
    fun parse(args: Array<String>){
        args.forEach { arg ->
            when (arg) {
                "-v" -> Logger.activateDebugLogger()
            }
        }
    }
}