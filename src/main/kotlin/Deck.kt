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
        println("Deck is: ${this.cards}");
        println("Deck size is: ${this.cards.size}");
    }
}