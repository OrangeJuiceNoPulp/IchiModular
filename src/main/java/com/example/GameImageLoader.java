package com.example;

import com.example.Card.Value;

import javafx.scene.image.Image;


public class GameImageLoader {
    public enum HandCards {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN
    }

    public void clearImages() {
        imgBlank = null;
        imgCardBack = null;

        imgRedBackground = null;
        imgBlueBackground = null;
        imgYellowBackground = null;
        imgGreenBackground = null;
        imgPurpleBackground = null;
        imgOrangeBackground = null;

        imgLightWildBackground = null;
        imgWildRedBackground = null;
        imgWildBlueBackground = null;
        imgWildYellowBackground = null;
        imgWildGreenBackground = null;
        imgWildPurpleBackground = null;
        imgWildOrangeBackground = null;

        imgAquaBackground = null;
        imgLimeBackground = null;
        imgMagentaBackground = null;
        imgCurrantBackground = null;

        imgSemiWildBackground = null;
        imgDarkWildBackground = null;
        imgWildAquaBackground = null;
        imgWildLimeBackground = null;
        imgWildMagentaBackground = null;
        imgWildCurrantBackground = null;

        imgN9 = null;
        imgN8 = null;
        imgN7 = null;
        imgN6 = null;
        imgN5 = null;
        imgN4 = null;
        imgN3 = null;
        imgN2 = null;
        imgN1 = null;
        img0 = null;
        img1 = null;
        img2 = null;
        img3 = null;
        img4 = null;
        img5 = null;
        img6 = null;
        img7 = null;
        img8 = null;
        img9 = null;

        imgD2 = null;
        imgD4 = null;
        imgD5 = null;
        imgDrawMatch = null;

        imgFlip = null;
        imgReverse = null;
        imgSkip = null;

        imgTowerBuild = null;
        imgTowerDestroy = null;

        imgRed = null;
        imgBlue = null;
        imgYellow = null;
        imgGreen = null;
        imgOrange = null;
        imgPurple = null;

        imgAqua = null;
        imgLime = null;
        imgMagenta = null;
        imgCurrant = null;

        imgWild = null;
        imgSemiwild = null;

        imgZeroCards = null;
        imgOneCards = null;
        imgTwoCards = null;
        imgThreeCards = null;
        imgFourCards = null;
        imgFiveCards = null;
        imgSixCards = null;
        imgSevenCards = null;
    }

    private Image imgBlank = new Image(this.getClass().getResource("blankSquare.png").toExternalForm());
    private Image imgCardBack = new Image(this.getClass().getResource("cardBackSquare.png").toExternalForm());

    private Image imgRedBackground = new Image(this.getClass().getResource("redBackgroundSquare.png").toExternalForm());
    private Image imgBlueBackground = new Image(this.getClass().getResource("blueBackgroundSquare.png").toExternalForm());
    private Image imgYellowBackground = new Image(this.getClass().getResource("yellowBackgroundSquare.png").toExternalForm());
    private Image imgGreenBackground = new Image(this.getClass().getResource("greenBackgroundSquare.png").toExternalForm());
    private Image imgPurpleBackground = new Image(this.getClass().getResource("purpleBackgroundSquare.png").toExternalForm());
    private Image imgOrangeBackground = new Image(this.getClass().getResource("orangeBackgroundSquare.png").toExternalForm());

    private Image imgLightWildBackground = new Image(this.getClass().getResource("wildLightBackgroundSquare.png").toExternalForm());
    private Image imgWildRedBackground = new Image(this.getClass().getResource("wildRedBackgroundSquare.png").toExternalForm());
    private Image imgWildBlueBackground = new Image(this.getClass().getResource("wildBlueBackgroundSquare.png").toExternalForm());
    private Image imgWildYellowBackground = new Image(this.getClass().getResource("wildYellowBackgroundSquare.png").toExternalForm());
    private Image imgWildGreenBackground = new Image(this.getClass().getResource("wildGreenBackgroundSquare.png").toExternalForm());
    private Image imgWildPurpleBackground = new Image(this.getClass().getResource("wildPurpleBackgroundSquare.png").toExternalForm());
    private Image imgWildOrangeBackground = new Image(this.getClass().getResource("wildOrangeBackgroundSquare.png").toExternalForm());



    private Image imgAquaBackground = new Image(this.getClass().getResource("aquaBackgroundSquare.png").toExternalForm());
    private Image imgLimeBackground = new Image(this.getClass().getResource("limeBackgroundSquare.png").toExternalForm());
    private Image imgMagentaBackground = new Image(this.getClass().getResource("magentaBackgroundSquare.png").toExternalForm());
    private Image imgCurrantBackground = new Image(this.getClass().getResource("currantBackgroundSquare.png").toExternalForm());

    private Image imgSemiWildBackground = new Image(this.getClass().getResource("wildSemiBackgroundSquare.png").toExternalForm());
    private Image imgDarkWildBackground = new Image(this.getClass().getResource("wildDarkBackgroundSquare.png").toExternalForm());
    private Image imgWildAquaBackground = new Image(this.getClass().getResource("wildAquaBackgroundSquare.png").toExternalForm());
    private Image imgWildLimeBackground = new Image(this.getClass().getResource("wildLimeBackgroundSquare.png").toExternalForm());
    private Image imgWildMagentaBackground = new Image(this.getClass().getResource("wildMagentaBackgroundSquare.png").toExternalForm());
    private Image imgWildCurrantBackground = new Image(this.getClass().getResource("wildCurrantBackgroundSquare.png").toExternalForm());

    private Image imgN9 = new Image(this.getClass().getResource("lightN9Square.png").toExternalForm());
    private Image imgN8 = new Image(this.getClass().getResource("lightN8Square.png").toExternalForm());
    private Image imgN7 = new Image(this.getClass().getResource("lightN7Square.png").toExternalForm());
    private Image imgN6 = new Image(this.getClass().getResource("lightN6Square.png").toExternalForm());
    private Image imgN5 = new Image(this.getClass().getResource("lightN5Square.png").toExternalForm());
    private Image imgN4 = new Image(this.getClass().getResource("lightN4Square.png").toExternalForm());
    private Image imgN3 = new Image(this.getClass().getResource("lightN3Square.png").toExternalForm());
    private Image imgN2 = new Image(this.getClass().getResource("lightN2Square.png").toExternalForm());
    private Image imgN1 = new Image(this.getClass().getResource("lightN1Square.png").toExternalForm());
    private Image img0 = new Image(this.getClass().getResource("light0Square.png").toExternalForm());
    private Image img1 = new Image(this.getClass().getResource("light1Square.png").toExternalForm());
    private Image img2 = new Image(this.getClass().getResource("light2Square.png").toExternalForm());
    private Image img3 = new Image(this.getClass().getResource("light3Square.png").toExternalForm());
    private Image img4 = new Image(this.getClass().getResource("light4Square.png").toExternalForm());
    private Image img5 = new Image(this.getClass().getResource("light5Square.png").toExternalForm());
    private Image img6 = new Image(this.getClass().getResource("light6Square.png").toExternalForm());
    private Image img7 = new Image(this.getClass().getResource("light7Square.png").toExternalForm());
    private Image img8 = new Image(this.getClass().getResource("light8Square.png").toExternalForm());
    private Image img9 = new Image(this.getClass().getResource("light9Square.png").toExternalForm());

    private Image imgD2 = new Image(this.getClass().getResource("lightDraw2Square.png").toExternalForm());
    private Image imgD4 = new Image(this.getClass().getResource("lightDraw4Square.png").toExternalForm());
    private Image imgD5 = new Image(this.getClass().getResource("lightDraw5Square.png").toExternalForm());
    private Image imgDrawMatch = new Image(this.getClass().getResource("lightDrawMatchSquare.png").toExternalForm());

    private Image imgFlip = new Image(this.getClass().getResource("lightFlipSquare.png").toExternalForm());
    private Image imgReverse = new Image(this.getClass().getResource("lightReverseSquare.png").toExternalForm());
    private Image imgSkip = new Image(this.getClass().getResource("lightSkipSquare.png").toExternalForm());

    private Image imgTowerBuild = new Image(this.getClass().getResource("lightTowerBuildSquare.png").toExternalForm());
    private Image imgTowerDestroy = new Image(this.getClass().getResource("lightTowerDestroySquare.png").toExternalForm());


    private Image imgRed = new Image(this.getClass().getResource("lightRedSquare.png").toExternalForm());
    private Image imgBlue = new Image(this.getClass().getResource("lightBlueSquare.png").toExternalForm());
    private Image imgGreen = new Image(this.getClass().getResource("lightGreenSquare.png").toExternalForm());
    private Image imgYellow = new Image(this.getClass().getResource("lightYellowSquare.png").toExternalForm());
    private Image imgOrange = new Image(this.getClass().getResource("lightOrangeSquare.png").toExternalForm());
    private Image imgPurple = new Image(this.getClass().getResource("lightPurpleSquare.png").toExternalForm());

    private Image imgAqua = new Image(this.getClass().getResource("lightAquaSquare.png").toExternalForm());
    private Image imgLime = new Image(this.getClass().getResource("lightLimeSquare.png").toExternalForm());
    private Image imgMagenta = new Image(this.getClass().getResource("lightMagentaSquare.png").toExternalForm());
    private Image imgCurrant = new Image(this.getClass().getResource("lightCurrantSquare.png").toExternalForm());

    private Image imgWild = new Image(this.getClass().getResource("lightWildSquare.png").toExternalForm());
    private Image imgSemiwild = new Image(this.getClass().getResource("lightSemiwildSquare.png").toExternalForm());



    private Image imgZeroCards = new Image(this.getClass().getResource("zeroCardsSquare.png").toExternalForm());
    private Image imgOneCards = new Image(this.getClass().getResource("oneCardsSquare.png").toExternalForm());
    private Image imgTwoCards = new Image(this.getClass().getResource("twoCardsSquare.png").toExternalForm());
    private Image imgThreeCards = new Image(this.getClass().getResource("threeCardsSquare.png").toExternalForm());
    private Image imgFourCards = new Image(this.getClass().getResource("fourCardsSquare.png").toExternalForm());
    private Image imgFiveCards = new Image(this.getClass().getResource("fiveCardsSquare.png").toExternalForm());
    private Image imgSixCards = new Image(this.getClass().getResource("sixCardsSquare.png").toExternalForm());
    private Image imgSevenCards = new Image(this.getClass().getResource("sevenCardsSquare.png").toExternalForm());

    public Image getCardBack() {
        return imgCardBack;
    }

    public Image getBlankSquare() {
        return imgBlank;
    }

    public Image getColorText(Card.DarkColor color) {
        switch (color) {
            case AQUA:
                return imgAqua;
            case CURRANT:
                return imgCurrant;
            case LIME:
                return imgLime;
            case MAGENTA:
                return imgMagenta;
            case SEMIWILD:
                return imgSemiwild;
            case WILD:
                return imgWild;
            case WILD_AQUA:
                return imgAqua;
            case WILD_CURRANT:
                return imgCurrant;
            case WILD_LIME:
                return imgLime;
            case WILD_MAGENTA:
                return imgMagenta;
            default:
                return imgBlank;
        }

    }

    public Image getColorText(Card.LightColor color) {
        switch (color) {
            case BLUE:
                return imgBlue;
            case GREEN:
                return imgGreen;
            case ORANGE:
                return imgOrange;
            case PURPLE:
                return imgPurple;
            case RED:
                return imgRed;
            case WILD:
                return imgWild;
            case WILD_BLUE:
                return imgBlue;
            case WILD_GREEN:
                return imgGreen;
            case WILD_ORANGE:
                return imgOrange;
            case WILD_PURPLE:
                return imgPurple;
            case WILD_RED:
                return imgRed;
            case WILD_YELLOW:
                return imgYellow;
            case YELLOW:
                return imgYellow;
            default:
                return imgBlank;

        }

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
            switch (value) {
                case N_NINE:
                    return imgN9;
                case N_EIGHT:
                    return imgN8;
                case N_SEVEN:
                    return imgN7;
                case N_SIX:
                    return imgN6;
                case N_FIVE:
                    return imgN5;
                case N_FOUR:
                    return imgN4;
                case N_THREE:
                    return imgN3;
                case N_TWO:
                    return imgN2;
                case N_ONE:
                    return imgN1;
                case ZERO:
                    return img0;
                case ONE:
                    return img1;
                case TWO:
                    return img2;
                case THREE:
                    return img3;
                case FOUR:
                    return img4;
                case FIVE:
                    return img5;
                case SIX:
                    return img6;
                case SEVEN:
                    return img7;
                case EIGHT:
                    return img8;
                case NINE:
                    return img9;
                case DISCARD_ALL:
                    break;
                case DRAW_2:
                    return imgD2;
                case DRAW_5:
                    return imgD5;
                case FLIP:
                    return imgFlip;
                case JACKPOT:
                    break;
                case PRESS_1:
                    break;
                case PRESS_3:
                    break;
                case REVERSE:
                    return imgReverse;
                case ROTATE:
                    break;
                case SKIP:
                    return imgSkip;
                case SKIP_ALL:
                    break;
                case SPIN_1:
                    break;
                case SPIN_2:
                    break;
                case SWAP:
                    break;
                case TARGET_DRAW_1:
                    break;
                case TARGET_DRAW_2:
                    break;
                case THIEF:
                    break;
                case TOWER_BUILD:
                    return imgTowerBuild;
                case TOWER_DESTROY:
                    return imgTowerDestroy;
                case WILD_DRAW_4:
                    return imgD4;
                case WILD_DRAW_TO_MATCH:
                    return imgDrawMatch;
                case WILD_EVERYONE_PRESS:
                    break;
                case WILD_JACKPOT:
                    break;
                case WILD_PRESS_2:
                    break;
                case WILD_PRESS_LOSE:
                    break;
                case WILD_REDISTRIBUTE:
                    break;
                case WILD_REGULAR:
                    return imgBlank;
                case WILD_REVERSE:
                    return imgReverse;
                case WILD_SHIELD:
                    break;
                case WILD_SKIP:
                    return imgSkip;
                case WILD_SPIN_2:
                    break;
                case WILD_SPIN_3:
                    break;
                case WILD_SWAP:
                    break;
                case WILD_TIMES_2:
                    break;
                default:
                    return imgBlank;
            }


            return imgBlank; //TODO Make the right images and return them
        }
        else {
            switch (value) {
                case N_NINE:
                    return imgN9;
                case N_EIGHT:
                    return imgN8;
                case N_SEVEN:
                    return imgN7;
                case N_SIX:
                    return imgN6;
                case N_FIVE:
                    return imgN5;
                case N_FOUR:
                    return imgN4;
                case N_THREE:
                    return imgN3;
                case N_TWO:
                    return imgN2;
                case N_ONE:
                    return imgN1;
                case ZERO:
                    return img0;
                case ONE:
                    return img1;
                case TWO:
                    return img2;
                case THREE:
                    return img3;
                case FOUR:
                    return img4;
                case FIVE:
                    return img5;
                case SIX:
                    return img6;
                case SEVEN:
                    return img7;
                case EIGHT:
                    return img8;
                case NINE:
                    return img9;
                case DISCARD_ALL:
                    break;
                case DRAW_2:
                    return imgD2;
                case DRAW_5:
                    return imgD5;
                case FLIP:
                    return imgFlip;
                case JACKPOT:
                    break;
                case PRESS_1:
                    break;
                case PRESS_3:
                    break;
                case REVERSE:
                    return imgReverse;
                case ROTATE:
                    break;
                case SKIP:
                    return imgSkip;
                case SKIP_ALL:
                    break;
                case SPIN_1:
                    break;
                case SPIN_2:
                    break;
                case SWAP:
                    break;
                case TARGET_DRAW_1:
                    break;
                case TARGET_DRAW_2:
                    break;
                case THIEF:
                    break;
                case TOWER_BUILD:
                    return imgTowerBuild;
                case TOWER_DESTROY:
                    return imgTowerDestroy;
                case WILD_DRAW_4:
                    return imgD4;
                case WILD_DRAW_TO_MATCH:
                    return imgDrawMatch;
                case WILD_EVERYONE_PRESS:
                    break;
                case WILD_JACKPOT:
                    break;
                case WILD_PRESS_2:
                    break;
                case WILD_PRESS_LOSE:
                    break;
                case WILD_REDISTRIBUTE:
                    break;
                case WILD_REGULAR:
                    return imgBlank;
                case WILD_REVERSE:
                    return imgReverse;
                case WILD_SHIELD:
                    break;
                case WILD_SKIP:
                    return imgSkip;
                case WILD_SPIN_2:
                    break;
                case WILD_SPIN_3:
                    break;
                case WILD_SWAP:
                    break;
                case WILD_TIMES_2:
                    break;
                default:
                    return imgBlank;
            }



            return imgBlank; //TODO Make the right images and return them
        }
    }

}
