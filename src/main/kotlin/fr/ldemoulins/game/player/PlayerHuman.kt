package fr.ldemoulins.game.player

import fr.ldemoulins.game.Game
import kotlin.system.exitProcess

class PlayerHuman(name: String): Player(name) {
    private var turnDone = false
    override fun takeTurn(game: Game) {
        println("Your turn to play.")
        turnDone = false
        while(!turnDone) {
            println("Enter a command (type help or h for help): ")
            getCommand(game)
        }
    }

    private fun getCommand(game: Game) {
        readln().let {
            when(it) {
                "h" -> displayHelp()
                "q" -> quitGame()
                "s" -> seeHand()
                "see" -> seeHand()
                "l" -> seeLastCard(game)
                "last" -> seeLastCard(game)
                "p" -> playACard(game)
                "n" -> passTurn(game)
                else -> displayHelp()
            }
        }
    }

    private fun displayHelp() {
        println("""
            h, help : Display current help message
            q       : Quit the game
            s, see  : See your hand
            l, last : See the last card played 
            p       : Go to the play a card menu
            n       : pass your turn
        """.trimIndent())
    }

    private fun quitGame() {
        exitProcess(0)
    }

    private fun seeHand() {
        println("${hand.getHand()}")
    }

    private fun seeLastCard(game: Game) {
        game.seeLastCard()
    }

    private fun playACard(game: Game) {
        readln().uppercase().let {cardName ->
            hand.getHand().find { it.name == cardName }.let { card ->
                if(card != null) {
                    if(game.canPlayCard(card)) {
                        println("Playing $card")
                        playCard(card)
                        game.playCard(card)
                        if(hand.getHand().size == 0) {
                            game.playerFinished(this)
                        }
                        turnDone = true
                    }else {
                        println("Cannot play card $card on top of ${game.getCurrentCard()}")
                    }
                } else {
                    println("Could not find card $cardName")
                }
            }
        }
    }

    private fun passTurn(game: Game) {
        game.playerPass(this)
        turnDone = true
    }
}