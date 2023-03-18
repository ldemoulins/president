class Hand {
    private val cards = mutableListOf<Card>()

    fun addCard(card: Card) = cards.add(0, card)
    fun sortHand() {
        cards.sort()
    }

    fun getHand() = cards

    fun playCard(index: Int) = cards.removeAt(index)
}