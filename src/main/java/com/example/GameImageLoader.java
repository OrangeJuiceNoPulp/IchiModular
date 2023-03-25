package com.example;

import com.example.Card.Value;

import javafx.scene.image.Image;


public class GameImageLoader {
    public enum HandCards {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN
    }

    private Image imgBlank = new Image(this.getClass().getResourceAsStream("blankSquare.png"));
    private Image imgCardBack = new Image(this.getClass().getResourceAsStream("cardBackSquare.png"));

    private Image imgRedBackground = new Image(this.getClass().getResourceAsStream("redBackgroundSquare.png"));
    private Image imgBlueBackground = new Image(this.getClass().getResourceAsStream("blueBackgroundSquare.png"));
    private Image imgYellowBackground = new Image(this.getClass().getResourceAsStream("yellowBackgroundSquare.png"));
    private Image imgGreenBackground = new Image(this.getClass().getResourceAsStream("greenBackgroundSquare.png"));
    private Image imgPurpleBackground = new Image(this.getClass().getResourceAsStream("purpleBackgroundSquare.png"));
    private Image imgOrangeBackground = new Image(this.getClass().getResourceAsStream("orangeBackgroundSquare.png"));

    private Image imgLightWildBackground = new Image(this.getClass().getResourceAsStream("wildLightBackgroundSquare.png"));
    private Image imgWildRedBackground = new Image(this.getClass().getResourceAsStream("wildRedBackgroundSquare.png"));
    private Image imgWildBlueBackground = new Image(this.getClass().getResourceAsStream("wildBlueBackgroundSquare.png"));
    private Image imgWildYellowBackground = new Image(this.getClass().getResourceAsStream("wildYellowBackgroundSquare.png"));
    private Image imgWildGreenBackground = new Image(this.getClass().getResourceAsStream("wildGreenBackgroundSquare.png"));
    private Image imgWildPurpleBackground = new Image(this.getClass().getResourceAsStream("wildPurpleBackgroundSquare.png"));
    private Image imgWildOrangeBackground = new Image(this.getClass().getResourceAsStream("wildOrangeBackgroundSquare.png"));



    private Image imgAquaBackground = new Image(this.getClass().getResourceAsStream("aquaBackgroundSquare.png"));
    private Image imgLimeBackground = new Image(this.getClass().getResourceAsStream("limeBackgroundSquare.png"));
    private Image imgMagentaBackground = new Image(this.getClass().getResourceAsStream("magentaBackgroundSquare.png"));
    private Image imgCurrantBackground = new Image(this.getClass().getResourceAsStream("currantBackgroundSquare.png"));

    private Image imgSemiWildBackground = new Image(this.getClass().getResourceAsStream("wildSemiBackgroundSquare.png"));
    private Image imgDarkWildBackground = new Image(this.getClass().getResourceAsStream("wildDarkBackgroundSquare.png"));
    private Image imgWildAquaBackground = new Image(this.getClass().getResourceAsStream("wildAquaBackgroundSquare.png"));
    private Image imgWildLimeBackground = new Image(this.getClass().getResourceAsStream("wildLimeBackgroundSquare.png"));
    private Image imgWildMagentaBackground = new Image(this.getClass().getResourceAsStream("wildMagentaBackgroundSquare.png"));
    private Image imgWildCurrantBackground = new Image(this.getClass().getResourceAsStream("wildCurrantBackgroundSquare.png"));



    private Image imgZeroCards = new Image(this.getClass().getResourceAsStream("zeroCardsSquare.png"));
    private Image imgOneCards = new Image(this.getClass().getResourceAsStream("oneCardsSquare.png"));
    private Image imgTwoCards = new Image(this.getClass().getResourceAsStream("twoCardsSquare.png"));
    private Image imgThreeCards = new Image(this.getClass().getResourceAsStream("threeCardsSquare.png"));
    private Image imgFourCards = new Image(this.getClass().getResourceAsStream("fourCardsSquare.png"));
    private Image imgFiveCards = new Image(this.getClass().getResourceAsStream("fiveCardsSquare.png"));
    private Image imgSixCards = new Image(this.getClass().getResourceAsStream("sixCardsSquare.png"));
    private Image imgSevenCards = new Image(this.getClass().getResourceAsStream("sevenCardsSquare.png"));

    public Image getCardBack() {
        return imgCardBack;
    }

    public Image getBlankSquare() {
        return imgBlank;
    }

    public Image getColorText(Card.DarkColor color) {
        return imgBlank; //TODO Make the right images and return them
    }

    public Image getColorText(Card.LightColor color) {
        return imgBlank;  //TODO Make the right images and return them
    }

    public Image getCardBackground(Card.DarkColor color) {
        switch (color) {
            case AQUA:
                return imgAquaBackground;
            case CURRANT:
                return imgCurrantBackground;
            case LIME:
                return imgLimeBackground;
            case MAGENTA:
                return imgMagentaBackground;
            case SEMIWILD:
                return imgSemiWildBackground;
            case WILD:
                return imgDarkWildBackground;
            case WILD_AQUA:
                return imgWildAquaBackground;
            case WILD_CURRANT:
                return imgWildCurrantBackground;
            case WILD_LIME:
                return imgWildLimeBackground;
            case WILD_MAGENTA:
                return imgWildMagentaBackground;
            default:
                return imgBlank;
        }
    }

    public Image getCardBackground(Card.LightColor color) {
        switch (color) {
            case BLUE:
                return imgBlueBackground;
            case GREEN:
                return imgGreenBackground;
            case ORANGE:
                return imgOrangeBackground;
            case PURPLE:
                return imgPurpleBackground;
            case RED:
                return imgRedBackground;
            case WILD:
                return imgLightWildBackground;
            case WILD_BLUE:
                return imgWildBlueBackground;
            case WILD_GREEN:
                return imgWildGreenBackground;
            case WILD_ORANGE:
                return imgWildOrangeBackground;
            case WILD_PURPLE:
                return imgWildPurpleBackground;
            case WILD_RED:
                return imgWildRedBackground;
            case WILD_YELLOW:
                return imgWildYellowBackground;
            case YELLOW:
                return imgYellowBackground;
            default:
                return imgBlank;
        }
    }

    public Image getHandCards(HandCards numberOfCards) {
        switch (numberOfCards) {
            case ZERO:
                return imgZeroCards;
            case ONE:
                return imgOneCards;
            case TWO:
                return imgTwoCards;
            case THREE:
                return imgThreeCards;
            case FOUR:
                return imgFourCards;
            case FIVE:
                return imgFiveCards;
            case SIX:
                return imgSixCards;
            case SEVEN:
                return imgSevenCards;
            default:
                return imgZeroCards;
        }
    }

    public Image getValueImage(Value value, boolean isDarkMode) {
        if (isDarkMode) {
            return imgBlank; //TODO Make the right images and return them
        }
        else {
            return imgBlank; //TODO Make the right images and return them
        }
    }

}
