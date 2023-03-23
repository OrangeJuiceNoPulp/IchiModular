package com.example;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class HandDisplay extends StackPane {
    private Image img0Card;
    private Image img1Card;
    private Image img2Card;
    private Image img3Card;
    private Image img4Card;
    private Image img5Card;
    private Image img6Card;
    private Image img7Card;

    private double inputHeight;
    private double inputWidth;

    private ImageView cardDisplay;
    private Player player;
    private Game game;
    private int playerNum;

    public void fitToSize(double width, double height) {
        inputHeight = height;
        inputWidth = width;
        cardDisplay.setFitHeight(height);
        cardDisplay.setFitWidth(width);
        
        this.setEffect(new DropShadow(0.075 * inputHeight, Color.BLACK));

        this.setMinSize(inputWidth, inputHeight);
        this.setMaxSize(inputWidth, inputHeight);
    }

    public void updateDisplay() {
        int numCards = 0; 
        if (player == null) {
            player = game.getPlayer(playerNum);
        }

        //System.out.println("Player is " + player.getPlayerName() + "\nNum Cards is " + player.getHandSize());
        
        if (player != null) {
            numCards = player.getHandSize();
        }

        if (numCards < 1) {
            cardDisplay.setImage(img0Card);
        }
        else if (numCards == 1) {
            cardDisplay.setImage(img1Card);
        }
        else if (numCards == 2) {
            cardDisplay.setImage(img2Card);
        }
        else if (numCards == 3) {
            cardDisplay.setImage(img3Card);
        }
        else if (numCards == 4) {
            cardDisplay.setImage(img4Card);
        }
        else if (numCards == 5) {
            cardDisplay.setImage(img5Card);
        }
        else if (numCards == 6) {
            cardDisplay.setImage(img6Card);
        }
        else if (numCards >= 7) {
            cardDisplay.setImage(img7Card);
        }

        cardDisplay.setFitHeight(inputHeight);
        cardDisplay.setFitWidth(inputWidth);
        
        this.setMinSize(inputWidth, inputHeight);
        this.setMaxSize(inputWidth, inputHeight);
    }

    public HandDisplay(Game game, int playerNum) {
        super();
        img0Card = new Image(this.getClass().getResourceAsStream("zeroCards.png"));
        img1Card = new Image(this.getClass().getResourceAsStream("oneCards.png"));
        img2Card = new Image(this.getClass().getResourceAsStream("twoCards.png"));
        img3Card = new Image(this.getClass().getResourceAsStream("threeCards.png"));
        img4Card = new Image(this.getClass().getResourceAsStream("fourCards.png"));
        img5Card = new Image(this.getClass().getResourceAsStream("fiveCards.png"));
        img6Card = new Image(this.getClass().getResourceAsStream("sixCards.png"));
        img7Card = new Image(this.getClass().getResourceAsStream("sevenCards.png"));

        inputHeight = 70;
        inputWidth = 100;

        this.playerNum = playerNum;
        this.game = game;


        cardDisplay = new ImageView(img0Card);

        this.setEffect(new DropShadow(0.075 * inputHeight, Color.BLACK));

        this.getChildren().add(cardDisplay);
        updateDisplay();
    }
}
