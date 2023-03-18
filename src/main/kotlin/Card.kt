enum class Card {
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JOCKEY,
    QUEEN,
    KING,
    ACE
}

infix fun Card.canBePlayedOn(card: Card): Boolean {
    return this.ordinal >= card.ordinal
}