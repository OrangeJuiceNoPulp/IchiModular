package com.example;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CardDisplaySquare extends StackPane {
    private GameImageLoader imageLoader;

    private ImageView colorBackground;
    private ImageView cardValue;
    private ImageView cardColor;


    public void scaleToHand(double width, double height) {
        if (colorBackground != null) {
            colorBackground.setFitHeight(height);
            colorBackground.setFitWidth(width);
        }
        if (cardValue != null) {
            cardValue.setFitHeight(height);
            cardValue.setFitWidth(width);
        }
        if (cardColor != null) {
            cardColor.setFitHeight(height);
            cardColor.setFitWidth(width);
        }
        
        //this.setHeight(height); //TODO fix if broken
        //this.setWidth(width);
    }

    public void updateDisplay(Card.LightColor color, Card.Value value) {

        colorBackground.setImage(imageLoader.getCardBackground(color));
        cardColor.setImage(imageLoader.getColorText(color));
        cardValue.setImage(imageLoader.getValueImage(value, false));

    }

    public CardDisplaySquare(Card.LightColor color, Card.Value value, GameImageLoader imageLoader) {
        super();
        this.imageLoader = imageLoader;

        colorBackground = new ImageView(imageLoader.getCardBack());
        cardValue = new ImageView(imageLoader.getBlankSquare());
        cardColor = new ImageView(imageLoader.getBlankSquare());

        colorBackground.setPreserveRatio(true);
        cardValue.setPreserveRatio(true);
        cardColor.setPreserveRatio(true);

        updateDisplay(color, value);

        this.getChildren().add(colorBackground);
        this.getChildren().add(cardColor);
        this.getChildren().add(cardValue);
        
        this.setAlignment(Pos.CENTER);
    }

    public void updateDisplay(Card card) {
        if (card != null) {
            if (card.getGame().getIsDarkMode()) {
                updateDisplay(card.getActiveDarkColor(), card.getDarkValue());
            }
            else {
                updateDisplay(card.getActiveLightColor(), card.getLightValue());
            }
        }
        else {
            colorBackground.setImage(imageLoader.getCardBack());
            cardColor.setImage(imageLoader.getBlankSquare());
            cardValue.setImage(imageLoader.getBlankSquare());
        }
    }

    public void updateDisplay(Card.DarkColor color, Card.Value value) {

        colorBackground.setImage(imageLoader.getCardBackground(color));
        cardColor.setImage(imageLoader.getColorText(color));
        cardValue.setImage(imageLoader.getValueImage(value, true));

    }

    public CardDisplaySquare(Card.DarkColor color, Card.Value value, GameImageLoader imageLoader) {
        super();
        this.imageLoader = imageLoader;

        colorBackground = new ImageView(imageLoader.getCardBack());
        cardValue = new ImageView(imageLoader.getBlankSquare());
        cardColor = new ImageView(imageLoader.getBlankSquare());

        colorBackground.setPreserveRatio(true);
        cardValue.setPreserveRatio(true);
        cardColor.setPreserveRatio(true);

        updateDisplay(color, value);

        this.getChildren().add(colorBackground);
        this.getChildren().add(cardColor);
        this.getChildren().add(cardValue);
        
        this.setAlignment(Pos.CENTER);
    }

    public CardDisplaySquare(GameImageLoader imageLoader) {
        super();
        this.imageLoader = imageLoader;

        colorBackground = new ImageView(imageLoader.getCardBack());
        cardValue = new ImageView(imageLoader.getBlankSquare());
        cardColor = new ImageView(imageLoader.getBlankSquare());

        colorBackground.setPreserveRatio(true);
        cardValue.setPreserveRatio(true);
        cardColor.setPreserveRatio(true);

        this.getChildren().add(colorBackground);
        this.getChildren().add(cardColor);
        this.getChildren().add(cardValue);
        
        this.setAlignment(Pos.CENTER);
    }
    
    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        colorBackground.setFitHeight(height);
        cardValue.setFitHeight(height);
        cardColor.setFitHeight(height);

    }
    public void setWidth(double width) {
        super.setWidth(width);
        colorBackground.setFitWidth(width);
        cardValue.setFitWidth(width);
        cardColor.setFitWidth(width);
    }

}