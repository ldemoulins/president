fun main(args: Array<String>) {
    Logger.activateDebugLogger()
    val deck = Deck()

    val numberOfHands = 4
    val playerNames = arrayOf("Apava", "Ldemoul", "Geroges", "L'autre teube")
    val players = arrayListOf<Player>()
    for(i in 1..numberOfHands) players.add(Player(playerNames.get(i-1), true))

    var idx = 0
    while (deck.hasCards()) {
        players.get(idx).hand.addCard(deck.drawCard())
        idx = (idx + 1) % numberOfHands
    }

    val game = Game(players)
    game.startGame()

    game.showResults()
}