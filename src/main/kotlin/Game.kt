class Game {
    private val players: MutableList<Hand>
    private var ladderBoard: ArrayList<Int>;

    constructor(players: MutableList<Hand>) {
        this.players = players
        this.ladderBoard = ArrayList(0)
    }

    fun playerLost(playerIdx: Int) {
        this.ladderBoard.add(playerIdx)
    }

    fun isGameEnded() = this.ladderBoard.size == this.players.size - 1

    fun showResults() {
        if(!isGameEnded()) {
            println("The game is not finished yet.")
            println("Players out of game already: $ladderBoard")
        } else {
            println("Ladder board:")
            for(i in 0..ladderBoard.size) {

            }
        }
    }
}