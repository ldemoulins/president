fun main(args: Array<String>) {
    val deck = Deck()
    deck.showDeck()

    val hand = Hand()
    hand.addCard(deck.drawCard())
    hand.addCard(deck.drawCard())
    hand.addCard(deck.drawCard())

    hand.showHand()
    deck.showDeck()
}