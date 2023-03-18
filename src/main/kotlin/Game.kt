class Game {
    private val players: MutableList<Hand>
    private var ladderBoard: ArrayList<Int>
    private var currentCard: Card? = null

    constructor(players: MutableList<Hand>) {
        this.players = players
        this.ladderBoard = ArrayList(0)
    }

    fun playerLost(playerIdx: Int) {
        ladderBoard.add(playerIdx)
    }

    fun isGameEnded() = ladderBoard.size >= players.size - 1

    fun showResults() {
        if (!isGameEnded()) {
            println("The game is not finished yet.")
            println("Players out of game already: $ladderBoard")
        } else {
            println("Ladder board:")
            ladderBoard.reversed().forEach {
                println(it)
            }
        }
    }

    fun showCurrentCard() {
        if(currentCard != null) {
            println("Current card is $currentCard")
        } else {
            println("No card has been played yet")
        }
    }

    fun playCard(card: Card) {
        Logger.log("Playing card $card")
        currentCard = card
    }
}