package fr.ldemoulins

import fr.ldemoulins.game.cards.Deck
import fr.ldemoulins.game.Game
import fr.ldemoulins.game.player.Player
import fr.ldemoulins.utils.ArgParser

fun main(args: Array<String>) {
    ArgParser.parse(args)

    val deck = Deck()

    val numberOfHands = 4
    val playerNames = arrayOf("Apava", "Ldemoul", "Geroges", "L'autre teube")
    val players = arrayListOf<Player>()
    for(i in 1..numberOfHands) players.add(Player(playerNames[i - 1], true))

    var idx = 0
    while (deck.hasCards()) {
        players[idx].hand.addCard(deck.drawCard())
        idx = (idx + 1) % numberOfHands
    }

    val game = Game(players)
    game.startGame()

    game.showResults()
}