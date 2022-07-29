package ru.ccfit.bozhko.task3;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class GameController extends KeyAdapter implements ActionListener {
    final private Gamer gamerBot;
    final private GamerPanel gamerPanel;
    private CardSuit trump;
    private Gamer attackingGamer;
    private Gamer fightBackGamer;
    private Card[] deckCards;
    private int countDeckCards = 36;
    final private Timer timer = new Timer(1000, this);
    final private GameView gamePanel;
    final private GameModel gameModel;
    private boolean isAttack = false;
    private boolean isFightBack = false;
    private Card cardAttack;
    private boolean cardIsTaken = false;

    GameController(GameModel gameModel, GameView gamePanel) {
        this.gamePanel = gamePanel;
        this.gameModel = gameModel;
        timer.start();
        gamerBot = gameModel.getGamerBot();
        gamerPanel = gameModel.getGamerPanel();
        deckCards = gameModel.getDeckCards();
        gamePanel.addKeyListener(this);
        initGame();
        gameModel.setAttackingGamer(attackingGamer);
    }

    private void fillDeckCards() {
        int index = 0;
        for (CardSuit suit : CardSuit.values()) {
            for (CardValue value : CardValue.values()) {
                deckCards[index].setSuitAndValue(suit, value);
                index++;
            }
        }
    }

    private void setTrump() {
        int indexSuit = new Random().nextInt(CardSuit.values().length);
        trump = CardSuit.values()[indexSuit];
        gameModel.setTrump(trump);
    }

    private void updateDeckCards(Gamer gamer) {
        gamer.updateCards();
        if(countDeckCards == 0)
            return;
        for(int i = gamer.getCountCards(); i < 6; ++i) {
            int index = new Random().nextInt(36);
            if (deckCards[index].getCardStatus() == CardStatus.inDeck) {
                gamer.takeCard(deckCards[index]);
                countDeckCards--;
            } else
                i--;
        }
        gameModel.setCountDeckCards(countDeckCards);
    }

    void initGame() {
        attackingGamer = gamerPanel;
        fightBackGamer = gamerBot;
        for (int i = 0; i < countDeckCards; ++i) {
            deckCards[i] = new Card();
        }

        fillDeckCards();
        setTrump();
        updateDeckCards(gamerBot);
        updateDeckCards(gamerPanel);
        isAttack = true;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        gamerPanel.setIndexSelectedCard(key - 49);
        gamerPanel.setSelection(true);
    }

    private void attackPlay(){
        if(attackingGamer == gamerPanel){
            if(gamerPanel.isCardSelected()){
                gamerPanel.setSelection(false);
                cardAttack = attackingGamer.attack(gamerPanel.getIndexSelectedCard(), trump);
                isAttack = false;
                isFightBack = true;
            }
        }
        else{
            cardAttack = attackingGamer.attack(0, trump);
            isAttack = false;
            isFightBack = true;
        }
    }

    private void fightBackPlay(){
        if(fightBackGamer == gamerPanel){ //игрок пользователь
            if(gamerPanel.isFightBackCard(cardAttack, trump)){ //может отбиться
                if(gamerPanel.isCardSelected()){  //выбрал карту
                    if(gamerPanel.getIndexSelectedCard() == -39){ //хочет взять
                        cardIsTaken = true;
                        gamerPanel.setSelection(false);
                        isFightBack = false;
                        return;
                    }
                    if(fightBackGamer.fightBack(gamerPanel.getIndexSelectedCard(), cardAttack, trump)) { //карта верная
                        cardIsTaken = false;
                        gamerPanel.setSelection(false);
                        isFightBack = false;
                    }
                }
            }
            else {
                cardIsTaken = true;
                isFightBack = false;
            }
        }
        else{
            cardIsTaken = fightBackGamer.fightBack(0, cardAttack, trump) ? false : true;
            isFightBack = false;
        }
    }

    private void updateGame(){
        updateDeckCards(attackingGamer);
        if(!cardIsTaken) {
            updateDeckCards(fightBackGamer);
            attackingGamer = fightBackGamer;
            fightBackGamer = (fightBackGamer == gamerBot) ? gamerPanel : gamerBot;
        }
        else{
            fightBackGamer.takeCard(cardAttack);
        }

        isAttack = true;
        if(attackingGamer.getCountCards() == 0 || fightBackGamer.getCountCards() == 0)
            gameModel.gameOver();
    }

    public void actionPerformed(ActionEvent e) {
        if(gameModel.isGameOver())
            return;

        gameModel.setAttackingGamer(attackingGamer);
        if(isAttack)
            attackPlay();
        else if(isFightBack)
            fightBackPlay();
        else
            updateGame();

        gamePanel.repaint();
    }
}
