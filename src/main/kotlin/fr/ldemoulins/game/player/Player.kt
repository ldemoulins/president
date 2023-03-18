package fr.ldemoulins.game.player

import fr.ldemoulins.game.cards.Card
import fr.ldemoulins.game.cards.canBePlayedOn
import fr.ldemoulins.game.Game
import fr.ldemoulins.utils.Logger

class Player {
    private val name: String
    var hand: Hand = Hand()
    private val isIA: Boolean

    constructor(name: String, isIA: Boolean = false) {
        Logger.log("Creating new player: $name")
        Logger.log("$name ${if (isIA) "is" else "is not"} an IA")
        this.name = name
        this.isIA = isIA
    }

    fun takeTurn(game: Game) {
        if (isIA) {
            if (hand.getHand().size == 0) return
            playLowestCardOrPass(game)
            if (hand.getHand().size == 0) game.playerFinished(this)
        } else {
            game.showCurrentCard()
            Logger.log("Do some human stuff")
        }
    }

    fun formattedHand() = hand.getHand().let {
        "$name's hand contains ${it.size} cards, which are: $it"
    }

    fun sortHand() = hand.sortHand()

    private fun playCard(card: Card) = hand.playCard(card)

    fun getName() = name

    private fun playLowestCardOrPass(game: Game) {
        game.getCurrentCard().let { card: Card? ->
           getFirstPlayableCard(card).let {
               if(it == null) {
                   Logger.log("$name cannot play, passes their turn")
                   game.playerPass(this)
               } else {
                   Logger.log("$name plays $it")
                   playCard(it)
                   game.playCard(it)
               }
           }
        }
    }

    private fun getFirstPlayableCard(cardToPlayOn: Card?) = hand.getHand().find { it canBePlayedOn cardToPlayOn }
}