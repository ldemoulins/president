enum class Card {
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
    ACE,
    TWO
}

infix fun Card.canBePlayedOn(card: Card?): Boolean {
    if(card == null) return true
    return this.ordinal >= card.ordinal
}