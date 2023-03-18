import java.util.Objects

class Game {
    private val players: MutableList<Player>
    private var ladderBoard: ArrayList<Player>
    private var currentCard: Card? = null
    private var currentPlayerIdx = 0
    private var playerHavePassed: ArrayList<Player> = ArrayList()
    private var lastPlayerThatTookTurnIdx = 0

    constructor(players: MutableList<Player>) {
        this.players = players
        this.players.forEach {it.sortHand()}
        this.ladderBoard = ArrayList(0)
    }

    fun playerFinished(player: Player) {
        if(ladderBoard.find { it == player } == null)
            ladderBoard.add(player)
    }

    private fun isGameEnded() = ladderBoard.size >= players.size

    fun showResults() {
        if (!isGameEnded()) {
            println("The game is not finished yet.")
            println("Players out of game already: $ladderBoard")
        } else {
            println("Order:")
            ladderBoard.forEachIndexed { index, player ->
                println("${index+1}: ${player.getName()}")
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

    fun getCurrentCard() = currentCard

    fun playCard(card: Card): Boolean {

        return if(currentCard == null || card canBePlayedOn currentCard!!) {
            currentCard = card
            true
        } else {
            false
        }
    }

    fun startGame() {
        players.forEach { player ->
            Logger.log(player.formattedHand())
        }
        while (!isGameEnded()) {
            if(!haveAllPlayersPassed()) {
                players[currentPlayerIdx].let { player ->
                    if(!hasPlayerPassed(player)) {
                        player.takeTurn(this)
                        lastPlayerThatTookTurnIdx = currentPlayerIdx
                    }
                }
                nextPlayer()
            } else {
                playerHavePassed = ArrayList()
                currentCard = null
                currentPlayerIdx = lastPlayerThatTookTurnIdx
            }
        }
    }

    private fun nextPlayer(): Player {
        currentPlayerIdx = (currentPlayerIdx + 1) % players.size
        return players[currentPlayerIdx]
    }

    fun playerPass(player: Player) {
        if(!hasPlayerPassed(player)) {
            playerHavePassed.add(player)
        }
    }

    private fun haveAllPlayersPassed() = (playerHavePassed.size + ladderBoard.size) == players.size

    private fun hasPlayerPassed(player: Player) = playerHavePassed.find { player == it } != null

}