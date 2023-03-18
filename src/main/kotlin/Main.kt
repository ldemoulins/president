fun main(args: Array<String>) {
    val deck = Deck()

    val hand = Hand()
    hand.addCard(deck.drawCard())
    hand.addCard(deck.drawCard())
    hand.addCard(deck.drawCard())

    hand.showHand()
}