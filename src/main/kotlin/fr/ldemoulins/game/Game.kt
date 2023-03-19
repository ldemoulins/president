package fr.ldemoulins.game

import fr.ldemoulins.game.cards.Card
import fr.ldemoulins.utils.Logger
import fr.ldemoulins.game.player.PlayerIA
import fr.ldemoulins.game.states.PlayersState
import fr.ldemoulins.game.states.RoundState

class Game(players: MutableList<PlayerIA>) {
    private var ladderBoard: ArrayList<PlayerIA>
    private var playerHavePassed: ArrayList<PlayerIA> = ArrayList()
    private var roundState = RoundState(this)
    private val playersState: PlayersState

    init {
        players.forEach { it.sortHand() }
        this.playersState = PlayersState(players)
        this.ladderBoard = ArrayList(0)
        roundState.newRound()
    }

    fun playerFinished(player: PlayerIA) {
        if (ladderBoard.find { it == player } == null) {
            ladderBoard.add(player)
            Logger.log("${player.name} has finished!")
        }
    }

    private fun isGameEnded() = ladderBoard.size >= playersState.getNumberOfPlayers()

    fun showResults() {
        if (!isGameEnded()) {
            println("The game is not finished yet.")
            println("Players out of game already: $ladderBoard")
        } else {
            println("game finished in ${roundState.getRoundCount()} rounds")
            println("Order:")
            ladderBoard.forEachIndexed { index, player ->
                println("${index + 1}: ${player.name}")
            }
        }
    }

    fun getCurrentCard() = roundState.getLastPlayedCard()

    fun playCard(card: Card) {
        roundState.playCard(card)
    }

    fun startGame() {
        println("Starting game... Good luck!")
        playersState.logHands()

        while (!isGameEnded()) {
            if (haveAllPlayersPassed()) {
                roundState.newRound()
                continue
            }

            playersState.getPlayer().let {
                if (!hasPlayerPassed(it)) it.takeTurn(this)
                if (roundState.shouldRoundStop()) {
                    roundState.newRound()
                } else {
                    nextPlayer()
                }
            }

        }
    }


    private fun nextPlayer() {
        playersState.nextPlayer()
    }

    fun playerPass(player: PlayerIA) {
        if (!hasPlayerPassed(player)) playerHavePassed.add(player)
    }

    private fun haveAllPlayersPassed() = (playerHavePassed.size + ladderBoard.size) == playersState.getNumberOfPlayers()

    private fun hasPlayerPassed(player: PlayerIA) = playerHavePassed.find { player == it } != null

    fun resetPlayerPassed() {
        playerHavePassed = arrayListOf()
    }
}