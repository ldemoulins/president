package fr.ldemoulins.game.states

import fr.ldemoulins.game.player.PlayerIA
import fr.ldemoulins.utils.Logger

class PlayersState(private val playerList: MutableList<PlayerIA>) {
    private var playerIt: Iterator<PlayerIA>
    private var currentPlayer: PlayerIA

    init {
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