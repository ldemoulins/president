class Hand {
    private val cards = mutableListOf<Card>()
    private val name: String

    constructor(name: String = "") {
        Logger.log("adding player $name")
        this.name = name
    }

    fun addCard(card: Card) = cards.add(0, card)
    fun sortHand() {
        cards.sort()
    }

    fun showHand() = println("Hand $name contains ${cards.size} that are: $cards")

    fun playCard(index: Int) = cards.removeAt(index)
}