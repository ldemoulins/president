class Hand {
    private var cards = mutableListOf<Card>()
    private val name: String

    constructor(name: String = "") {
        this.name = name
    }

    fun addCard(card: Card) = cards.add(0, card)
    fun sortHand() {
        cards.sort()
    }

    fun showHand() = println("Hand $name is: $cards")

    fun playCard(index: Int) = cards.removeAt(index)
}