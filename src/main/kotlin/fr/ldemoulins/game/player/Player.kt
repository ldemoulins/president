package fr.ldemoulins.game.player

import fr.ldemoulins.game.Game
import fr.ldemoulins.game.cards.Card

abstract class Player(val name: String) {
    val hand: Hand = Hand()

    abstract fun takeTurn(game: Game)

    fun sortHand() {
        hand.sortHand()
    }

    fun playCard(card: Card) {
        hand.playCard(card)
    }

    fun formattedHand() = hand.getHand().let { "$name's hand contains ${it.size} cards, which are: $it" }
}