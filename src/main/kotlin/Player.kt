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
            if(hand.getHand().size == 0) return
            Logger.log("$name takes turn...")
            game.playCard(playCard(0))
            if(hand.getHand().size == 0) game.playerFinished(this)
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

    fun getName() = name
}