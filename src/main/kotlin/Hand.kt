class Hand {
    private var cards = mutableListOf<Card>()

    fun addCard(card: Card) {
        cards.add(0, card)
    }

    fun showHand() {
        println("Hand is: $cards")
    }
}