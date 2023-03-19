package fr.ldemoulins.game.player

import fr.ldemoulins.game.cards.Card
import fr.ldemoulins.game.cards.canBePlayedOn
import fr.ldemoulins.game.Game
import fr.ldemoulins.utils.Logger

class PlayerIA(name: String) : Player(name) {

    override fun takeTurn(game: Game) {
        if (hand.getHand().size == 0) return
        playLowestCardOrPass(game)
        if (hand.getHand().size == 0) game.playerFinished(this)

    }

    private fun playLowestCardOrPass(game: Game) {
        game.getCurrentCard().let { card: Card? ->
           getFirstPlayableCard(card).let {
               if(it == null) {
                   Logger.log("$name cannot play, passes their turn")
                   game.playerPass(this)
               } else {
                   Logger.log("$name plays $it")
                   playCard(it)
                   game.playCard(it)
               }
           }
        }
    }

    private fun getFirstPlayableCard(cardToPlayOn: Card?) = hand.getHand().find { it canBePlayedOn cardToPlayOn }
}