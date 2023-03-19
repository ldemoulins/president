# president

A terminal based president card game in Kotlin

# Rules

For now, the ruleset is simplified, and goes as follows:
- Only one game is played (no winner/looser card exchange after a game)
- Only one card can be played at a time (no pair or more)
- First player to start a round plays the card they wants
- Whenever a card is already in game, player must play a card with a value equal or above to the one in game
- If a two is played, the round is closed and the player that played the two can play again
- If four of the same card is played in a row (for instance four Queens in a row), the player that played the last card closes the round and plays again
- First player with empty hand wins

# How to start

To start, do the following:
```bash
java -jar president.jar -n PlayerName
```

where `PlayerName` is your chose player name.

Additionnaly you can use the `-v` option to have more in game debug info


# Good to know
As per 18/03/2023 the game is playable in solo with "bots" (3 non human players that always play the lowest card they can play)
In the future, there might be some more possibilities, like local mutliplayer (multiple humans on the same single instance), or network multiplayer. We'll see how it goes.
