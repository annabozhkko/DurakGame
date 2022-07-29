package ru.ccfit.bozhko.task3;

enum CardSuit{
    DIAMONDS,
    HEARTS,
    CLUBS,
    SPADES
}

enum CardValue{
    SIX,
    SEVEN,
    EIGHT,
    NIGHT,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE
}

enum CardStatus{
    inDeck,
    inGamerDeck,
    attack,
    fightBack,
    remove
}


public class Card {
    private CardSuit suit;
    private CardValue value;
    private CardStatus cardStatus = CardStatus.inDeck;

    public CardSuit getCardSuit(){
        return  suit;
    }

    public CardValue getCardValue(){
        return value;
    }

    public void setSuitAndValue(CardSuit suit, CardValue value){
        this.suit = suit;
        this.value = value;
    }

    public CardStatus getCardStatus(){
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus){
        this.cardStatus = cardStatus;
    }

    /*
    public enum CardStatus{
        inDeck,
        inGamerDeck,
        attack,
        fightBack,
        remove
    }

    public enum CardSuit{
        DIAMONDS,
        HEARTS,
        CLUBS,
        SPADES
    }

    public enum CardValue{
        SIX,
        SEVEN,
        EIGHT,
        NIGHT,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE
    }

     */
}
