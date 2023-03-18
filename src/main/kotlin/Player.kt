class Player {
    private val name: String
    public var hand: Hand = Hand()
    private val isIA: Boolean

    constructor(name: String, isIA: Boolean = false) {
        Logger.log("Creating new player: $name")
        Logger.log("$name ${if (isIA) "is" else "is not"} an IA")
        this.name = name
        this.isIA = isIA
    }

    fun takeTurn(game: Game) {
        Logger.log("$name takes turn...")
        if (isIA) {
            if(hand.getHand().size == 0) {
                game.playerLost(game.findPlayerIndex(this))
                return
            }
            game.playCard(playCard(0))
        } else {
            Logger.log("Do some human stuff")
        }
    }

    fun showHand() = hand.getHand().let {
        println("$name's hand contains ${it.size} cards, which are: $it")
    }

    fun sortHand() = hand.sortHand()

    fun playCard(cardIdx: Int): Card {
        val card = hand.playCard(cardIdx)
        Logger.log("$name plays $card")
        return card
    }
}