class Player {
    private val name: String
    var hand: Hand = Hand()
    private val isIA: Boolean

    constructor(name: String, isIA: Boolean = false) {
        Logger.log("Creating new player: $name")
        Logger.log("$name ${if (isIA) "is" else "is not"} an IA")
        this.name = name
        this.isIA = isIA
    }

    fun takeTurn(game: Game) {
        if (isIA) {
            if (hand.getHand().size == 0) return
            Logger.log("$name takes turn...")
            playLowestCardOrPass(game)
            if (hand.getHand().size == 0) game.playerFinished(this)
        } else {
            game.showCurrentCard()
            Logger.log("Do some human stuff")
        }
    }

    fun formattedHand() = hand.getHand().let {
        "$name's hand contains ${it.size} cards, which are: $it"
    }

    fun sortHand() = hand.sortHand()

    fun playCard(cardIdx: Int): Card {
        val card = hand.playCard(cardIdx)
        Logger.log("$name plays $card\n")
        return card
    }

    private fun checkCard(cardIdx: Int) = hand.getHand()[cardIdx]

    fun getName() = name

    private fun playLowestCardOrPass(game: Game) {
        game.getCurrentCard().let { card: Card? ->
            if (card == null) {
                game.playCard(playCard(0))
            } else {
                var i = 0
                while (i < hand.getHand().size && !(checkCard(i) canBePlayedOn card!!)) {
                    i++
                }
                if (i < hand.getHand().size) {
                    game.playCard(playCard(i))
                } else {
                    game.playerPass(this)
                }
            }
        }
    }
}