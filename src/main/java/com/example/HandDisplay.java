package com.example;

import com.example.GameImageLoader.HandCards;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class HandDisplay extends StackPane {
    private GameImageLoader imageLoader;

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
        
        if (player != null) {
            numCards = player.getHandSize();
        }

        if (numCards < 1) {
            cardDisplay.setImage(imageLoader.getHandCards(HandCards.ZERO));
        }
        else if (numCards == 1) {
            cardDisplay.setImage(imageLoader.getHandCards(HandCards.ONE));
        }
        else if (numCards == 2) {
            cardDisplay.setImage(imageLoader.getHandCards(HandCards.TWO));
        }
        else if (numCards == 3) {
            cardDisplay.setImage(imageLoader.getHandCards(HandCards.THREE));
        }
        else if (numCards == 4) {
            cardDisplay.setImage(imageLoader.getHandCards(HandCards.FOUR));
        }
        else if (numCards == 5) {
            cardDisplay.setImage(imageLoader.getHandCards(HandCards.FIVE));
        }
        else if (numCards == 6) {
            cardDisplay.setImage(imageLoader.getHandCards(HandCards.SIX));
        }
        else if (numCards >= 7) {
            cardDisplay.setImage(imageLoader.getHandCards(HandCards.SEVEN));
        }

        cardDisplay.setFitHeight(inputHeight);
        cardDisplay.setFitWidth(inputWidth);
        
        this.setMinSize(inputWidth, inputHeight);
        this.setMaxSize(inputWidth, inputHeight);
    }

    public HandDisplay(Game game, int playerNum, GameImageLoader imageLoader) {
        super();
        this.imageLoader = imageLoader;

        inputHeight = 70;
        inputWidth = 100;

        this.playerNum = playerNum;
        this.game = game;


        cardDisplay = new ImageView(imageLoader.getHandCards(HandCards.ZERO));

        this.setEffect(new DropShadow(0.075 * inputHeight, Color.BLACK));

        this.getChildren().add(cardDisplay);
        updateDisplay();
    }
}
