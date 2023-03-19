package fr.ldemoulins.game.states

import fr.ldemoulins.game.player.Player
import fr.ldemoulins.utils.Logger

class PlayersState {
    private val playerList: MutableList<Player>
    private var playerIt: Iterator<Player>
    private var currentPlayer: Player

    constructor(playerList: MutableList<Player>) {
        this.playerList = playerList
        this.playerIt = this.playerList.iterator()
        this.currentPlayer = this.playerIt.next()
    }

    fun nextPlayer() {
        if(!playerIt.hasNext()) playerIt = playerList.iterator()
        currentPlayer = playerIt.next()
    }

    fun getPlayer() = currentPlayer
    fun getNumberOfPlayers() = playerList.size

    fun logHands() {
        playerList.forEach {
            Logger.log(it.formattedHand())
        }
    }
}