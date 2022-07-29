package ru.ccfit.bozhko.task3;

public class GameModel {
    final private GamerBot gamerBot = new GamerBot();
    final private GamerPanel gamerPanel = new GamerPanel();
    private Gamer attackingGamer;
    private CardSuit trump;
    private Card[] deckCards = new Card[36];
    private int countDeckCards = 36;
    private boolean isGameOver = false;

    public GamerBot getGamerBot() {
        return gamerBot;
    }

    public GamerPanel getGamerPanel() {
        return gamerPanel;
    }

    public Card[] getDeckCards() {
        return deckCards;
    }

    public CardSuit getTrump() {
        return trump;
    }

    public int getCountDeckCards() {
        return countDeckCards;
    }

    public void setAttackingGamer(Gamer attackingGamer){
        this.attackingGamer = attackingGamer;
    }

    public void setTrump(CardSuit trump) {
        this.trump = trump;
    }

    public void setCountDeckCards(int countDeckCards) {
        this.countDeckCards = countDeckCards;
    }

    public Gamer getAttackingGamer(){
        return attackingGamer;
    }

    public void gameOver(){
        isGameOver = true;
    }

    public boolean isGameOver(){
        return isGameOver;
    }
}
