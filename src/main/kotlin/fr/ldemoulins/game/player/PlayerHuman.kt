package fr.ldemoulins.game.player

import fr.ldemoulins.game.Game
import fr.ldemoulins.game.cards.Card
import kotlin.system.exitProcess

class PlayerHuman(name: String) : Player(name) {
    private var turnDone = false
    private val cardShortcuts = mapOf(
        Pair("1", "ACE"),
        Pair("2", "TWO"),
        Pair("3", "THREE"),
        Pair("4", "FOUR"),
        Pair("5", "FIVE"),
        Pair("6", "SIX"),
        Pair("7", "SEVEN"),
        Pair("8", "EIGHT"),
        Pair("9", "NINE"),
        Pair("10", "TEN"),
        Pair("J", "JOCKEY"),
        Pair("Q", "QUEEN"),
        Pair("K", "KING")
    )

    override fun takeTurn(game: Game) {
        println("Your turn to play.")
        turnDone = false
        while (!turnDone) {
            println("Enter a command (type help or h for help): ")
            getCommand(game)
        }
    }

    private fun getCommand(game: Game) {
        readln().let {
            val cmd = it.uppercase()
            if (isStringInCards(cmd)) {
                playSelectedCard(cmd, game);
            } else if (isStringInShortcuts(cmd)) {
                playSelectedCard(getCardFromShortCut(cmd), game)
            } else {
                when (cmd) {
                    "H" -> displayHelp()
                    "E" -> quitGame()
                    "EXIT" -> quitGame()
                    "S" -> seeHand()
                    "SEE" -> seeHand()
                    "L" -> seeLastCard(game)
                    "LAST" -> seeLastCard(game)
                    "P" -> playACard(game)
                    "N" -> passTurn(game)
                    "SHORTCUTS" -> displayShortcuts()
                    "SHORTCUT" -> displayShortcuts()
                    else -> displayHelp()
                }
            }

        }
    }

    private fun isStringInCards(potentialCardName: String) = Card.values().find { it.name == potentialCardName } != null

    private fun isStringInShortcuts(potentialCardName: String) = cardShortcuts.keys.find { potentialCardName == it } != null

    private fun getCardFromShortCut(cardName: String): String {
        return cardShortcuts.keys.find { cardName == it }.let {
            cardShortcuts[it!!] ?: ""
        }
    }

    private fun displayHelp() {
        println(
            """
            h, help : Display current help message
            e, exit : Quit the game
            s, see  : See your hand
            l, last : See the last card played 
            p       : Go to the play a card menu
            n       : pass your turn
            
            You can also play a card directly, either by typing its name (e.g. five) or its shortcut (e.g. 5)
            To see the list of shortcuts, type shortcuts
        """.trimIndent()
        )
    }

    private fun displayShortcuts() {
        println(
            """
            The shortcuts are: 
            1  : ACE
            2  : TWO
            3  : THREE
            4  : FOUR
            5  : FIVE
            6  : SIX
            7  : SEVEN
            8  : EIGHT
            9  : NINE
            10 : TEN
            J  : JOCKEY
            Q  : QUEEN
            K  : KING
        """.trimIndent()
        )
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
            if (card != null) {
                if (game.canPlayCard(card)) {
                    println("Playing $card")
                    game.playCard(card, this)
                    if (hand.getHand().size == 0) {
                        game.playerFinished(this)
                    }
                    turnDone = true
                } else {
                    println("Cannot play card $card on top of ${game.getCurrentCard()}")
                }
            } else {
                println("You do not have the card $cardName")
            }
        }
    }

    private fun passTurn(game: Game) {
        game.playerPass(this)
        turnDone = true
    }
}