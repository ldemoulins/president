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
            when(it.uppercase()) {
                "H" -> displayHelp()
                "Q" -> quitGame()
                "S" -> seeHand()
                "SEE" -> seeHand()
                "L" -> seeLastCard(game)
                "LAST" -> seeLastCard(game)
                "P" -> playACard(game)
                "N" -> passTurn(game)

                "TWO" -> playSelectedCard("TWO", game)
                "THREE" -> playSelectedCard("THREE", game)
                "FOUR" -> playSelectedCard("FOUR", game)
                "FIVE" -> playSelectedCard("FIVE", game)
                "SIX" -> playSelectedCard("SIX", game)
                "SEVEN" -> playSelectedCard("SEVEN", game)
                "EIGHT" -> playSelectedCard("EIGHT", game)
                "NINE" -> playSelectedCard("NINE", game)
                "TEN" -> playSelectedCard("TEN", game)
                "JOCKEY" -> playSelectedCard("JOCKEY", game)
                "QUEEN" -> playSelectedCard("QUEEN", game)
                "KING" -> playSelectedCard("KING", game)
                "ACE" -> playSelectedCard("ACE", game)

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
        println("Enter one of your card to play:")
        playSelectedCard(readln().uppercase(), game)
    }

    private fun playSelectedCard(cardName: String, game: Game) {
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

    private fun passTurn(game: Game) {
        game.playerPass(this)
        turnDone = true
    }
}