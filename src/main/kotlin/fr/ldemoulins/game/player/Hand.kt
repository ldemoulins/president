package fr.ldemoulins.game.player

import fr.ldemoulins.game.cards.Card

class Hand {
    private val cards = mutableListOf<Card>()

    fun addCard(card: Card) = cards.add(0, card)
    fun sortHand() {
        cards.sort()
    }

    fun getHand() = cards

    fun playCard(card: Card) = cards.remove(card)
}