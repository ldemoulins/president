package fr.ldemoulins.utils

object ArgParser {
    var playerName = "DefaultPlayer"
    fun parse(args: Array<String>){
        if("-v" in args) Logger.activateDebugLogger()
        if("-n" in args) {
            args.indexOf("-n").let {
                playerName = args[it+1]
            }
        }
    }


}