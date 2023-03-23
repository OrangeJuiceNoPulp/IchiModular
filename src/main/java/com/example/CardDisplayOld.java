package com.example;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CardDisplayOld extends StackPane {

    private ImageView colorBackground;
    private ImageView modeBorder;
    private ImageView modeBackground;
    private ImageView colorAccent;
    private ImageView cardValue;
    private ImageView cardColor;

    // private double idealWidth;
    // private double idealHeight;

    // public void scaleToHand() {
    //     scaleToHand(84, 120);
    // }

    public void scaleToHand(double width, double height) {
        if (colorBackground != null) {
            colorBackground.setFitHeight(height);
            colorBackground.setFitWidth(width);
        }
        if (modeBorder != null) {
            modeBorder.setFitHeight(height);
            modeBorder.setFitWidth(width);
        }
        if (modeBackground != null) {
            modeBackground.setFitHeight(height);
            modeBackground.setFitWidth(width);
        }
        if (colorAccent != null) {
            colorAccent.setFitHeight(height);
            colorAccent.setFitWidth(width);
        }
        if (cardValue != null) {
            cardValue.setFitHeight(height);
            cardValue.setFitWidth(width);
        }
        if (cardColor != null) {
            cardColor.setFitHeight(height);
            cardColor.setFitWidth(width);
        }
        
        this.setHeight(height);
        this.setWidth(width);
    }

    public void updateDisplay(Card.LightColor color, Card.Value value) {
        modeBorder = new ImageView(new Image(this.getClass().getResourceAsStream("lightBorder.png")));
        modeBackground = new ImageView(new Image(this.getClass().getResourceAsStream("lightBackground.png")));

        switch (color) {
            case BLUE:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("blueBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("blueAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
            case GREEN:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("greenBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("greenAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
            case ORANGE:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("orangeBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("orangeAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
            case PURPLE:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("purpleBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("purpleAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
            case RED:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("redBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("redAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
            case WILD:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("lightWildBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("lightWildAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
            case WILD_BLUE:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("lightWildBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("blueAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
            case WILD_GREEN:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("lightWildBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("greenAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
            case WILD_ORANGE:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("lightWildBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("orangeAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
            case WILD_PURPLE:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("lightWildBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("purpleAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
            case WILD_RED:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("lightWildBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("redAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
            case WILD_YELLOW:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("lightWildBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("yellowAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
            case YELLOW:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("yellowBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("yellowAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
            default:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("lightWildBackground.png"))); //TODO
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("lightWildAccent.png"))); //TODO
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
        }

        switch (value) {
            case ONE:
                cardValue = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png"))); //TODO
                break;
            case TWO:
                cardValue = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png"))); //TODO
                break;
            default:
                cardValue = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png"))); //TODO
                break;
            
        }

        this.getChildren().clear();

        this.getChildren().add(colorBackground);
        this.getChildren().add(modeBorder);
        this.getChildren().add(modeBackground);
        this.getChildren().add(colorAccent);
        this.getChildren().add(cardValue);
        this.getChildren().add(cardColor);
    }

    public CardDisplayOld(Card.LightColor color, Card.Value value) {
        super();

        colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("cardBack.png")));
        modeBorder = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
        modeBackground = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
        colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
        cardValue = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
        cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));

        updateDisplay(color, value);
        //this.setAlignment(Pos.CENTER);

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
            colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("cardBack.png")));
            modeBorder = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
            modeBackground = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
            colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
            cardValue = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
            cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));

            this.getChildren().clear();
        
            this.getChildren().add(colorBackground);
            this.getChildren().add(modeBorder);
            this.getChildren().add(modeBackground);
            this.getChildren().add(colorAccent);
            this.getChildren().add(cardValue);
            this.getChildren().add(cardColor);
        }
    }

    public void updateDisplay(Card.DarkColor color, Card.Value value) {
        modeBorder = new ImageView(new Image(this.getClass().getResourceAsStream("darkBorder.png")));
        modeBackground = new ImageView(new Image(this.getClass().getResourceAsStream("darkBackground.png")));

        switch (color) {
            case LIME:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("limeBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("limeAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png")));
                break;
            case MAGENTA:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("magentaBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("magentaAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("magentaText.png")));
                break;
            case AQUA:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("aquaBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("aquaAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("aquaText.png")));
                break;
            case CURRANT:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("currantBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("currantAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("currantText.png")));
                break;
            case WILD:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("darkWildBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("darkWildAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
            case WILD_CURRANT:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("darkWildBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("currantAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("currantText.png")));
                break;
            case WILD_LIME:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("darkWildBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("limeAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png")));
                break;
            case WILD_MAGENTA:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("darkWildBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("magentaAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("magentaText.png")));
                break;
            case WILD_AQUA:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("darkWildBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("aquaAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("aquaText.png")));
                break;
            case SEMIWILD:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("semiWildBackground.png")));
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("darkWildAccent.png")));
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("aquaText.png"))); //TODO
                break;
            default:
                colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("wildBackground.png"))); //TODO
                colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("blueAccent.png"))); //TODO
                cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("limeText.png"))); //TODO
                break;
        }

        switch (value) {
            case ONE:
                cardValue = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png"))); //TODO
                break;
            case TWO:
                cardValue = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png"))); //TODO
                break;
            default:
                cardValue = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png"))); //TODO
                break;
            
        }

        this.getChildren().clear();
        
            this.getChildren().add(colorBackground);
            this.getChildren().add(modeBorder);
            this.getChildren().add(modeBackground);
            this.getChildren().add(colorAccent);
            this.getChildren().add(cardValue);
            this.getChildren().add(cardColor);
    }

    public CardDisplayOld(Card.DarkColor color, Card.Value value) {
        super();

        colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("cardBack.png")));
        modeBorder = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
        modeBackground = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
        colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
        cardValue = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
        cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));

        updateDisplay(color, value);
        //this.setAlignment(Pos.CENTER);

    }

    public CardDisplayOld() {
        super();

        colorBackground = new ImageView(new Image(this.getClass().getResourceAsStream("cardBack.png")));
        modeBorder = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
        modeBackground = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
        colorAccent = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
        cardValue = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));
        cardColor = new ImageView(new Image(this.getClass().getResourceAsStream("blank.png")));

        this.getChildren().add(colorBackground);
        this.getChildren().add(modeBorder);
        this.getChildren().add(modeBackground);
        this.getChildren().add(colorAccent);
        this.getChildren().add(cardValue);
        this.getChildren().add(cardColor);
        //this.setAlignment(Pos.CENTER);
    }
    
    // @Override
    // public void setHeight(double height) {
    //     super.setHeight(height);
    //     this.idealHeight = height;

    //     double minimumScale = Math.min(idealWidth / 7.0, idealHeight / 10.0);
    //     super.setHeight(minimumScale * 10.0);

    //     scaleToHand(minimumScale * 7.0, minimumScale * 10.0);

    // }
    // public void setWidth(double width) {
        
    //     this.idealWidth = width;

    //     double minimumScale = Math.min(idealWidth / 7.0, idealHeight / 10.0);
    //     super.setWidth(minimumScale * 7.0);

    //     scaleToHand(minimumScale * 7.0, minimumScale * 10.0);
    // }

}