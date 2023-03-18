import kotlin.math.round

class RoundState {
    private var playedCards: MutableList<Card> = mutableListOf()
    private val game: Game
    private var roundCount = 0

    constructor(game: Game) {
        this.game = game
    }

    fun newRound() {
        playedCards = mutableListOf()
        game.resetPlayerPassed()
        roundCount ++
    }

    fun getRoundCount() = roundCount

    fun getLastPlayedCard(): Card? {
        return if(playedCards.isEmpty()) {
            null
        } else {
            playedCards.last()
        }
    }

    fun playCard(card: Card) {
        playedCards.add(card)
    }

    fun shouldRoundStop(): Boolean {
        if(getLastPlayedCard() == Card.TWO) {
            Logger.log("Last card play was a TWO, new round...\n")
            return true
        }
        if(areFourLastCardEquals()) {
            Logger.log("All 4 ${getLastPlayedCard()} have been played in succession, new round...\n")
            return true
        }
        return false
    }

    private fun areFourLastCardEquals(): Boolean {
        if(playedCards.size < 4) return false
        val idx = playedCards.lastIndex
        return playedCards[idx] == playedCards[idx-3]
    }
}