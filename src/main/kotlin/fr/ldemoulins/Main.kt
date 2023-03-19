package fr.ldemoulins

import fr.ldemoulins.game.cards.Deck
import fr.ldemoulins.game.Game
import fr.ldemoulins.game.player.Player
import fr.ldemoulins.game.player.PlayerHuman
import fr.ldemoulins.game.player.PlayerIA
import fr.ldemoulins.utils.ArgParser
import fr.ldemoulins.utils.Version

fun main(args: Array<String>) {
    println("""
        =================================
        Welcome to the president game!
        Version ${Version.getVersion()}
        =================================
        
    """.trimIndent())
    ArgParser.parse(args)

    val deck = Deck()

    val numberOfHands = 4
    val playerNames = arrayOf("Apava", "Geroges", "L'autre teube")
    val players = arrayListOf<Player>()
    for(i in 1 until numberOfHands) players.add(PlayerIA(playerNames[i - 1]))
    players.add(PlayerHuman(ArgParser.playerName))

    var idx = 0
    while (deck.hasCards()) {
        players[idx].hand.addCard(deck.drawCard())
        idx = (idx + 1) % numberOfHands
    }

    val game = Game(players)
    game.startGame()

    game.showResults()
}