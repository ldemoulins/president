package fr.ldemoulins.game

import fr.ldemoulins.game.cards.Card
import fr.ldemoulins.utils.Logger
import fr.ldemoulins.game.player.Player

class Game(private val players: MutableList<Player>) {
    private var ladderBoard: ArrayList<Player>
    private var currentPlayerIt: Iterator<Player>
    private var currentPlayer: Player
    private var playerHavePassed: ArrayList<Player> = ArrayList()
    private var round = RoundState(this)

    init {
        this.players.forEach { it.sortHand() }
        this.currentPlayerIt = players.iterator()
        this.currentPlayer = this.currentPlayerIt.next()
        this.ladderBoard = ArrayList(0)
        round.newRound()
    }

    fun playerFinished(player: Player) {
        if (ladderBoard.find { it == player } == null) {
            ladderBoard.add(player)
            Logger.log("${player.getName()} has finished!")
        }
    }

    private fun isGameEnded() = ladderBoard.size >= players.size

    fun showResults() {
        if (!isGameEnded()) {
            println("The game is not finished yet.")
            println("Players out of game already: $ladderBoard")
        } else {
            println("game finished in ${round.getRoundCount()} rounds")
            println("Order:")
            ladderBoard.forEachIndexed { index, player ->
                println("${index + 1}: ${player.getName()}")
            }
        }
    }

    fun showCurrentCard() {
        round.getLastPlayedCard().let { currentCard ->
            if (currentCard != null) {
                println("Current card is $currentCard")
            } else {
                println("No card has been played yet")
            }
        }
    }

    fun getCurrentCard() = round.getLastPlayedCard()

    fun playCard(card: Card) {
        round.playCard(card)
    }

    fun startGame() {
        println("Starting game... Good luck!")
        players.forEach { player ->
            Logger.log(player.formattedHand())
        }
        while (!isGameEnded()) {
            if (haveAllPlayersPassed()) {
                round.newRound()
                continue
            }

            if (!hasPlayerPassed(currentPlayer)) currentPlayer.takeTurn(this)
            if (round.shouldRoundStop()) {
                round.newRound()
            } else {
                nextPlayer()
            }
        }
    }


    private fun nextPlayer() {
        if (!currentPlayerIt.hasNext()) currentPlayerIt = players.iterator()
        currentPlayer = currentPlayerIt.next()
    }

    fun playerPass(player: Player) {
        if (!hasPlayerPassed(player)) playerHavePassed.add(player)
    }

    private fun haveAllPlayersPassed() = (playerHavePassed.size + ladderBoard.size) == players.size

    private fun hasPlayerPassed(player: Player) = playerHavePassed.find { player == it } != null

    fun resetPlayerPassed() {
        playerHavePassed = arrayListOf()
    }
}