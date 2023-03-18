package fr.ldemoulins.game.cards

import kotlin.random.Random

class Deck {
    private val cards = mutableListOf<Card>()
    constructor() {
        var firstDone = false
        Card.values().forEach {
            for (i in 0..3){
                if(firstDone) {
                    cards.add(Random.nextInt(0, cards.size), it)
                } else {
                    cards.add(0, it)
                    firstDone = true
                }
            }
        }
    }

    fun drawCard(): Card {
        return this.cards.removeAt(0);
    }

    fun showDeck() {
        println("fr.ldemoulins.Game.Cards.Deck is: ${this.cards}");
        println("fr.ldemoulins.Game.Cards.Deck size is: ${this.cards.size}");
    }

    fun hasCards() = cards.size > 0
}