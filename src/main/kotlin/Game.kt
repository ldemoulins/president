class Game {
    private val players: MutableList<Player>
    private var ladderBoard: ArrayList<Int>
    private var currentCard: Card? = null
    private var currentPlayerIdx = 0

    constructor(players: MutableList<Player>) {
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
        if (currentCard != null) {
            println("Current card is $currentCard")
        } else {
            println("No card has been played yet")
        }
    }

    fun playCard(card: Card) {
        currentCard = card
    }

    fun startGame() {
        while (!isGameEnded()) {
            players[currentPlayerIdx].let { player ->
                player.takeTurn(this)
            }
        }
    }

    fun nextPlayer(): Player {
        currentPlayerIdx = (currentPlayerIdx + 1) % players.size
        return players[currentPlayerIdx]
    }

    fun findPlayerIndex(player: Player): Int {
        for(i in 0 until players.size) {
            if(player == players[i]) {
                return i
            }
        }
        throw Error("Player not found")
    }
}