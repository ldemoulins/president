fun main(args: Array<String>) {
    val deck = Deck()

    val numberOfHands = 4
    val hands = arrayListOf<Hand>()
    for(i in 1..numberOfHands) hands.add(Hand("$i"))

    var idx = 0
    while (deck.hasCards()) {
        hands.get(idx).addCard(deck.drawCard())
        idx = (idx + 1) % numberOfHands
    }

    hands.forEach { hand ->
        hand.sortHand()
        hand.showHand()
    }
}