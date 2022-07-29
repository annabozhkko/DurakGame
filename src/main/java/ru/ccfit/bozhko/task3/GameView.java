package ru.ccfit.bozhko.task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JPanel implements ActionListener{
    final private GameModel gameModel;

    public GameView(GameModel gameModel){
        this.gameModel = gameModel;
        setBackground(Color.gray);
        setFocusable(true);
    }

    private void printAttackCard(Card card, Graphics2D graphics2D){
        Image cardImage;
        ImageIcon iia = new ImageIcon("src/main/resources/cards/" + card.getCardSuit() + "/" + card.getCardValue() + ".JPG");
        cardImage = iia.getImage();
        graphics2D.drawImage(cardImage, 370, 236, 57,88,this);
    }

    private void printFightBackCard(Card card, Graphics2D graphics2D){
        Image cardImage;
        ImageIcon iia = new ImageIcon("src/main/resources/cards/" + card.getCardSuit() + "/" + card.getCardValue() + ".JPG");
        cardImage = iia.getImage();
        graphics2D.drawImage(cardImage, 370, 256, 57,88,this);
    }

    private void printCardInDeck(Card card, Graphics2D graphics2D, int x, int y){
        Image cardImage;
        ImageIcon iia = new ImageIcon("src/main/resources/cards/" + card.getCardSuit() + "/" + card.getCardValue() + ".JPG");
        cardImage = iia.getImage();
        graphics2D.drawImage(cardImage, x, y, 57,88,this);
    }

    private void printBotCard(Graphics2D graphics2D, int x, int y){
        Image cardImage;
        ImageIcon iia = new ImageIcon("src/main/resources/cards/shirt.png");
        cardImage = iia.getImage();
        graphics2D.drawImage(cardImage, x, y, 57,88,this);
    }

    private void printDeckBot(Graphics2D graphics2D){
        Card cards[] = gameModel.getGamerBot().getCards();
        for(int i = 0; i < gameModel.getGamerBot().getCountCards(); ++i){
            if(cards[i].getCardStatus() == CardStatus.attack)
                printAttackCard(cards[i], graphics2D);
            if(cards[i].getCardStatus() == CardStatus.fightBack)
                printFightBackCard(cards[i], graphics2D);
            if(cards[i].getCardStatus() == CardStatus.inGamerDeck)
                printBotCard(graphics2D, (100 - 10 * (gameModel.getGamerBot().getCountCards() - 6)) * (1 + i), 50);
        }
    }

    private void printDeckPanel(Graphics2D graphics2D){
        Card cards[] = gameModel.getGamerPanel().getCards();
        for(int i = 0; i < gameModel.getGamerPanel().getCountCards(); ++i){
            if(cards[i].getCardStatus() == CardStatus.attack)
                printAttackCard(cards[i], graphics2D);
            if(cards[i].getCardStatus() == CardStatus.fightBack)
                printFightBackCard(cards[i], graphics2D);
            if(cards[i].getCardStatus() == CardStatus.inGamerDeck)
                printCardInDeck(cards[i], graphics2D, (100 - 10 * (gameModel.getGamerPanel().getCountCards() - 6)) * (1 + i), 430);

            graphics2D.drawString(Integer.toString(i + 1), (100 - 10 * (gameModel.getGamerPanel().getCountCards() - 6)) * (1 + i) + 22, 540);
        }
    }

    @Override
    public void paint(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;

        if(gameModel.getAttackingGamer() == gameModel.getGamerPanel()){
            printDeckPanel(graphics2D);
            printDeckBot(graphics2D);
        }
        else {
            printDeckBot(graphics2D);
            printDeckPanel(graphics2D);
        }

        if(gameModel.getCountDeckCards() > 0) {
            ImageIcon iia = new ImageIcon("src/main/resources/cards/shirt.png");
            Image deck = iia.getImage();
            graphics2D.drawImage(deck, 40, 248, 57, 88, this);
            graphics2D.drawString(Integer.toString(gameModel.getCountDeckCards()), 62, 358);
        }

        ImageIcon iiaa = new ImageIcon("src/main/resources/cards/" + gameModel.getTrump() + ".png");
        Image trumpCard = iiaa.getImage();
        graphics2D.drawImage(trumpCard,48, 272, 40,40,this);

        if(gameModel.isGameOver()){
            int fontSize = 20;
            Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
            graphics2D.setFont(f);
            graphics2D.drawString("Game is over", 330, 300);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){}
}
