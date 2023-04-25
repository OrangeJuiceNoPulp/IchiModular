package com.example;

import java.util.ArrayList;

import com.example.Deck.DeckType;
import com.example.SoundEffectPlayer.SoundEffectType;
import com.example.Tower.TowerPosition;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePane extends StackPane {
    private static final int DEFAULT_WAIT_TIME = 1500;

    private int delayTime;

    private GameInnerPane gameTable;
    private Pane animationPane;

    private ArrayList<Animation> animationQueue;

    private Stage stage;

    private boolean doSoundEffects;
    private boolean doBackgroundMusic;

    private SoundEffectPlayer soundEffectPlayer;
    private MediaPlayer backgroundMediaPlayer;

    public int getDelayTime() {
        return this.delayTime;
    }

    public void resetDelayTime() {
        this.delayTime = 0;
    }

    private double getPlayerXCord(int playerNum, int numOfPlayers, double textWidth) {
        //double xCord = gameTable.getPlayerPositionInScene(playerNum, numOfPlayers).getX();
        if (numOfPlayers == 2) {
            return ((this.getScene().getWidth() / 2.0) * 0.7);
        }
        else if (playerNum == 1) {
            return ((this.getScene().getWidth() / 2.0) * 0.7);
        }
        else if (playerNum == 3) {
            return ((this.getScene().getWidth() / 2.0) * 0.7);
        }
        else if (playerNum == 4) {
            return 0 + (textWidth / 2.0);
        }
        else {
            return((this.getScene().getWidth()) * 0.7);
        }
        //return xCord;
    }

    private double getPlayerYCord(int playerNum, int numOfPlayers, double textHeight) {
        //double yCord = gameTable.getPlayerPositionInScene(playerNum, numOfPlayers).getY();
        if ((numOfPlayers == 2) && (playerNum == 2)) {
            return 0 + (textHeight / 2.0);
        }
        else if (playerNum == 1) {
            return ((this.getScene().getHeight()) * 0.55);
        }
        else if (playerNum == 3) {
            return 0 + (textHeight / 2.0);
        }
        else if (playerNum == 4) {
            return ((this.getScene().getHeight() / 2.0) * 0.7);
        }
        else {
            return ((this.getScene().getHeight() / 2.0) * 0.7);
        }
        //return yCord;
    }

    private double getStartXCord(int playerNum, int numOfPlayers, double cardWidth) {
        if (numOfPlayers == 2) {
            return ((this.getScene().getWidth() / 2.0) - (cardWidth / 2.0));
        }
        else if (playerNum == 1) {
            return ((this.getScene().getWidth() / 2.0) - (cardWidth / 2.0));
        }
        else if (playerNum == 3) {
            return ((this.getScene().getWidth() / 2.0) - (cardWidth / 2.0));
        }
        else if (playerNum == 4) {
            return (this.getScene().getWidth() * 0.1);
        }
        else {
            return (this.getScene().getWidth() * 0.9 - (cardWidth / 2.0));
        }
    }

    private double getStartYCord(int playerNum, int numOfPlayers, double cardHeight) {
        if ((numOfPlayers == 2) && (playerNum == 2)) {
            return ((this.getScene().getHeight() * 0.1));
        }
        else if (playerNum == 1) {
            return ((this.getScene().getHeight() * 0.9) - (cardHeight / 2.0));
        }
        else if (playerNum == 3) {
            return ((this.getScene().getHeight() * 0.1));
        }
        else if (playerNum == 4) {
            return ((this.getScene().getHeight() / 2.0) - (cardHeight / 2.0));
        }
        else {
            return ((this.getScene().getHeight() / 2.0) - (cardHeight / 2.0));
        }
    }

    private double getCenterXCord(double cardWidth) {
        return ((this.getScene().getWidth() * 0.4) - (cardWidth / 2.0));
    }

    private double getCenterYCord(double cardHeight) {
        return ((this.getScene().getHeight() / 2.0) - (cardHeight / 2.0));
    }

    private double getTowerXCord(TowerPosition position) {
        return gameTable.getTowerPositionInScene(position).getX();
    }

    private double getTowerYCord(TowerPosition position) {
        return gameTable.getTowerPositionInScene(position).getY();
    }

    public void addPointChangeAnimation(int playerNum, int numOfPlayers, int numOfPoints) {
        Timeline pointsAnimation = new Timeline();
        pointsAnimation.setCycleCount(1);

        String pointsString;
        if (numOfPoints >= 0) {
            pointsString = "+" + numOfPoints;
        }
        else {
            pointsString = String.format("%d", numOfPoints);
        }

        Label txtPointsChange = new Label(pointsString);

        double textDimensions = Math.min(this.getScene().getHeight() * 0.2, this.getScene().getWidth()* 0.2);

        txtPointsChange.setFont(new Font("Perpetua Bold Italic", Math.max(textDimensions * 0.9, 14)));
        txtPointsChange.setTextFill(Color.WHITESMOKE);
        txtPointsChange.setEffect(new DropShadow(1000 * 0.125, Color.BLACK));


        txtPointsChange.setVisible(false);

        animationPane.getChildren().add(txtPointsChange);

        double finalXCord = getPlayerXCord(playerNum, numOfPlayers, txtPointsChange.getWidth());
        double finalYCord = getPlayerYCord(playerNum, numOfPlayers, txtPointsChange.getHeight());


        pointsAnimation.getKeyFrames().addAll(
            new KeyFrame(Duration.millis(0), new KeyValue(txtPointsChange.translateXProperty(), finalXCord, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(0), new KeyValue(txtPointsChange.translateYProperty(), finalYCord, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(0), e -> {
                if (numOfPoints >= 0) {
                    soundEffectPlayer.playSoundEffect(SoundEffectType.GAIN_POINTS);
                }
                else {
                    soundEffectPlayer.playSoundEffect(SoundEffectType.LOSE_POINTS);
                }
            }, new KeyValue(txtPointsChange.visibleProperty(), true, Interpolator.LINEAR)),
            new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(txtPointsChange.translateXProperty(), finalXCord, Interpolator.LINEAR)),
            new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(txtPointsChange.translateYProperty(), finalYCord, Interpolator.LINEAR)),
            new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(txtPointsChange.visibleProperty(), false, Interpolator.LINEAR))
        );

        animationQueue.add(pointsAnimation);
        this.delayTime += DEFAULT_WAIT_TIME;
    }

    public void addReverseAnimation(boolean clockwise) {
        Timeline reverseAnimation = new Timeline();
        reverseAnimation.setCycleCount(1);

        ImageView imgReverse;
        double imageDimensions = Math.min(this.getScene().getHeight() * 0.8, this.getScene().getWidth()* 0.8);

        int amountToRotate;
        if (clockwise) {
            amountToRotate = 360;

            imgReverse = new ImageView(gameTable.getGame().getImageLoader().getReverse());
            imgReverse.setPreserveRatio(true);

            imgReverse.setFitHeight(imageDimensions);
            imgReverse.setFitWidth(imageDimensions);
            imgReverse.setVisible(false);

            animationPane.getChildren().add(imgReverse);
        }
        else {
            amountToRotate = -360;

            imgReverse = new ImageView(gameTable.getGame().getImageLoader().getBackwardsReverse());
            imgReverse.setPreserveRatio(true);

            imgReverse.setFitHeight(imageDimensions);
            imgReverse.setFitWidth(imageDimensions);
            imgReverse.setVisible(false);

            animationPane.getChildren().add(imgReverse);
        }

        double xCord = ((this.getScene().getWidth() / 2.0) - (imageDimensions / 2.0));
        double yCord = ((this.getScene().getHeight() / 2.0) - (imageDimensions / 2.0));

        imgReverse.setX(xCord);
        imgReverse.setY(yCord);

        reverseAnimation.getKeyFrames().addAll(
            new KeyFrame(Duration.millis(0), e -> {
                soundEffectPlayer.playSoundEffect(SoundEffectType.REVERSE);
            }, new KeyValue(imgReverse.visibleProperty(), true, Interpolator.LINEAR)),
            new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(imgReverse.rotateProperty(), amountToRotate, Interpolator.EASE_IN)),
            new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(imgReverse.visibleProperty(), false, Interpolator.LINEAR))
        );

        animationQueue.add(reverseAnimation);
        this.delayTime += DEFAULT_WAIT_TIME;
    }

    public void addFlipAnimation(boolean isNowDarkMode) {
        Timeline flipAnimation = new Timeline();
        flipAnimation.setCycleCount(1);

        ImageView imgFlip = new ImageView(gameTable.getGame().getImageLoader().getFlip());
        imgFlip.setPreserveRatio(true);

        double imageDimensions = Math.min(this.getScene().getHeight() * 0.8, this.getScene().getWidth()* 0.8);

        imgFlip.setFitHeight(imageDimensions);
        imgFlip.setFitWidth(imageDimensions);
        imgFlip.setVisible(false);
        
        animationPane.getChildren().add(imgFlip);

        int initialRotation;
        int finalRotation;

        if (isNowDarkMode) {
            initialRotation = 0;
            finalRotation = 180;
        }
        else {
            initialRotation = -180;
            finalRotation = 0;
        }

        double xCord = ((this.getScene().getWidth() / 2.0) - (imageDimensions / 2.0));
        double yCord = ((this.getScene().getHeight() / 2.0) - (imageDimensions / 2.0));

        imgFlip.setX(xCord);
        imgFlip.setY(yCord);

        flipAnimation.getKeyFrames().addAll(
            new KeyFrame(Duration.millis(0), new KeyValue(imgFlip.rotateProperty(), initialRotation, Interpolator.EASE_IN)),
            new KeyFrame(Duration.millis(1), e -> {
                soundEffectPlayer.playSoundEffect(SoundEffectType.FLIP);
            }, new KeyValue(imgFlip.visibleProperty(), true, Interpolator.LINEAR)),
            new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(imgFlip.rotateProperty(), finalRotation, Interpolator.EASE_IN)),
            new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(imgFlip.visibleProperty(), false, Interpolator.LINEAR))
        );

        animationQueue.add(flipAnimation);
        this.delayTime += DEFAULT_WAIT_TIME;
    }

    public void addRotateAnimation(int numOfPlayers, boolean clockwise) {
        if (numOfPlayers == 2) {
            addSwapAnimation(2, 1, 2);
            return;
        }
        else if (numOfPlayers == 3) {
            CardDisplaySquare card1Display = new CardDisplaySquare(gameTable.getGame().getImageLoader());
            card1Display.setVisible(false);
    
            CardDisplaySquare card2Display = new CardDisplaySquare(gameTable.getGame().getImageLoader());
            card2Display.setVisible(false);

            CardDisplaySquare card3Display = new CardDisplaySquare(gameTable.getGame().getImageLoader());
            card3Display.setVisible(false);
    
    
            double cardDimensions = Math.min(this.getScene().getHeight() * 0.1, this.getScene().getWidth()* 0.1);
            card1Display.setHeight(cardDimensions);
            card2Display.setHeight(cardDimensions);
            card3Display.setHeight(cardDimensions);
    
            animationPane.getChildren().addAll(card1Display, card2Display, card3Display);
            Timeline cardDrawingAnimation = new Timeline();
    
            double xCord1 = getStartXCord(1, numOfPlayers, card1Display.getWidth());
            double yCord1 = getStartYCord(1, numOfPlayers, card1Display.getHeight());

            double xCord2 = getStartXCord(2, numOfPlayers, card1Display.getWidth());
            double yCord2 = getStartYCord(2, numOfPlayers, card1Display.getHeight());

            double xCord3 = getStartXCord(3, numOfPlayers, card1Display.getWidth());
            double yCord3 = getStartYCord(3, numOfPlayers, card1Display.getHeight());
    
            cardDrawingAnimation.setCycleCount(1);
            if (clockwise) {
                cardDrawingAnimation.getKeyFrames().addAll(
                    new KeyFrame(Duration.millis(0), new KeyValue(card1Display.translateXProperty(), xCord1, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card1Display.translateYProperty(), yCord1, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card2Display.translateXProperty(), xCord2, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card2Display.translateYProperty(), yCord2, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card3Display.translateXProperty(), xCord3, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card3Display.translateYProperty(), yCord3, Interpolator.EASE_OUT)),
    
                    new KeyFrame(Duration.millis(1), new KeyValue(card1Display.visibleProperty(), true, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(1), new KeyValue(card2Display.visibleProperty(), true, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(1), new KeyValue(card3Display.visibleProperty(), true, Interpolator.LINEAR)),
                    
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card1Display.translateXProperty(), xCord3, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card1Display.translateYProperty(), yCord3, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card2Display.translateXProperty(), xCord1, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card2Display.translateYProperty(), yCord1, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card3Display.translateXProperty(), xCord2, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card3Display.translateYProperty(), yCord2, Interpolator.LINEAR)),
    
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card1Display.visibleProperty(), false, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card2Display.visibleProperty(), false, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card3Display.visibleProperty(), false, Interpolator.LINEAR))
                );
            }
            else {
                cardDrawingAnimation.getKeyFrames().addAll(
                    new KeyFrame(Duration.millis(0), new KeyValue(card1Display.translateXProperty(), xCord1, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card1Display.translateYProperty(), yCord1, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card2Display.translateXProperty(), xCord2, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card2Display.translateYProperty(), yCord2, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card3Display.translateXProperty(), xCord3, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card3Display.translateYProperty(), yCord3, Interpolator.EASE_OUT)),
    
                    new KeyFrame(Duration.millis(1), new KeyValue(card1Display.visibleProperty(), true, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(1), new KeyValue(card2Display.visibleProperty(), true, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(1), new KeyValue(card3Display.visibleProperty(), true, Interpolator.LINEAR)),
                    
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card1Display.translateXProperty(), xCord2, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card1Display.translateYProperty(), yCord2, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card2Display.translateXProperty(), xCord3, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card2Display.translateYProperty(), yCord3, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card3Display.translateXProperty(), xCord1, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card3Display.translateYProperty(), yCord1, Interpolator.LINEAR)),
    
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card1Display.visibleProperty(), false, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card2Display.visibleProperty(), false, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card3Display.visibleProperty(), false, Interpolator.LINEAR))
                );
            }
            
    
            animationQueue.add(cardDrawingAnimation);
            this.delayTime += DEFAULT_WAIT_TIME;
        }
        else {
            CardDisplaySquare card1Display = new CardDisplaySquare(gameTable.getGame().getImageLoader());
            card1Display.setVisible(false);
    
            CardDisplaySquare card2Display = new CardDisplaySquare(gameTable.getGame().getImageLoader());
            card2Display.setVisible(false);

            CardDisplaySquare card3Display = new CardDisplaySquare(gameTable.getGame().getImageLoader());
            card3Display.setVisible(false);

            CardDisplaySquare card4Display = new CardDisplaySquare(gameTable.getGame().getImageLoader());
            card3Display.setVisible(false);
    
    
            double cardDimensions = Math.min(this.getScene().getHeight() * 0.1, this.getScene().getWidth()* 0.1);
            card1Display.setHeight(cardDimensions);
            card2Display.setHeight(cardDimensions);
            card3Display.setHeight(cardDimensions);
            card4Display.setHeight(cardDimensions);
    
            animationPane.getChildren().addAll(card1Display, card2Display, card3Display, card4Display);
            Timeline cardDrawingAnimation = new Timeline();
    
            double xCord1 = getStartXCord(1, numOfPlayers, card1Display.getWidth());
            double yCord1 = getStartYCord(1, numOfPlayers, card1Display.getHeight());

            double xCord2 = getStartXCord(2, numOfPlayers, card1Display.getWidth());
            double yCord2 = getStartYCord(2, numOfPlayers, card1Display.getHeight());

            double xCord3 = getStartXCord(3, numOfPlayers, card1Display.getWidth());
            double yCord3 = getStartYCord(3, numOfPlayers, card1Display.getHeight());
    
            double xCord4 = getStartXCord(4, numOfPlayers, card1Display.getWidth());
            double yCord4 = getStartYCord(4, numOfPlayers, card1Display.getHeight());

            cardDrawingAnimation.setCycleCount(1);
            if (clockwise) {
                cardDrawingAnimation.getKeyFrames().addAll(
                    new KeyFrame(Duration.millis(0), new KeyValue(card1Display.translateXProperty(), xCord1, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card1Display.translateYProperty(), yCord1, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card2Display.translateXProperty(), xCord2, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card2Display.translateYProperty(), yCord2, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card3Display.translateXProperty(), xCord3, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card3Display.translateYProperty(), yCord3, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card4Display.translateXProperty(), xCord4, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card4Display.translateYProperty(), yCord4, Interpolator.EASE_OUT)),
    
                    new KeyFrame(Duration.millis(1), new KeyValue(card1Display.visibleProperty(), true, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(1), new KeyValue(card2Display.visibleProperty(), true, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(1), new KeyValue(card3Display.visibleProperty(), true, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(1), new KeyValue(card4Display.visibleProperty(), true, Interpolator.LINEAR)),
                    
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card1Display.translateXProperty(), xCord4, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card1Display.translateYProperty(), yCord4, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card2Display.translateXProperty(), xCord1, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card2Display.translateYProperty(), yCord1, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card3Display.translateXProperty(), xCord2, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card3Display.translateYProperty(), yCord2, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card4Display.translateXProperty(), xCord3, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card4Display.translateYProperty(), yCord3, Interpolator.LINEAR)),
    
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card1Display.visibleProperty(), false, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card2Display.visibleProperty(), false, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card3Display.visibleProperty(), false, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card4Display.visibleProperty(), false, Interpolator.LINEAR))
                );
            }
            else {
                cardDrawingAnimation.getKeyFrames().addAll(
                    new KeyFrame(Duration.millis(0), new KeyValue(card1Display.translateXProperty(), xCord1, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card1Display.translateYProperty(), yCord1, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card2Display.translateXProperty(), xCord2, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card2Display.translateYProperty(), yCord2, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card3Display.translateXProperty(), xCord3, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card3Display.translateYProperty(), yCord3, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card4Display.translateXProperty(), xCord4, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(0), new KeyValue(card4Display.translateYProperty(), yCord4, Interpolator.EASE_OUT)),
    
                    new KeyFrame(Duration.millis(1), new KeyValue(card1Display.visibleProperty(), true, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(1), new KeyValue(card2Display.visibleProperty(), true, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(1), new KeyValue(card3Display.visibleProperty(), true, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(1), new KeyValue(card4Display.visibleProperty(), true, Interpolator.LINEAR)),
                    
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card1Display.translateXProperty(), xCord2, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card1Display.translateYProperty(), yCord2, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card2Display.translateXProperty(), xCord3, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card2Display.translateYProperty(), yCord3, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card3Display.translateXProperty(), xCord4, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card3Display.translateYProperty(), yCord4, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card4Display.translateXProperty(), xCord1, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card4Display.translateYProperty(), yCord1, Interpolator.LINEAR)),
    
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card1Display.visibleProperty(), false, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card2Display.visibleProperty(), false, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card3Display.visibleProperty(), false, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card4Display.visibleProperty(), false, Interpolator.LINEAR))
                );
            }
            
    
            animationQueue.add(cardDrawingAnimation);
            this.delayTime += DEFAULT_WAIT_TIME;
        }
    }

    public void addSwapAnimation(int numOfPlayers, int currentPlayerNum, int targetPlayerNum) {
        CardDisplaySquare card1Display = new CardDisplaySquare(gameTable.getGame().getImageLoader());
        card1Display.setVisible(false);

        CardDisplaySquare card2Display = new CardDisplaySquare(gameTable.getGame().getImageLoader());
        card2Display.setVisible(false);


        double cardDimensions = Math.min(this.getScene().getHeight() * 0.1, this.getScene().getWidth()* 0.1);
        card1Display.setHeight(cardDimensions);
        card2Display.setHeight(cardDimensions);

        animationPane.getChildren().addAll(card1Display, card2Display);
        Timeline cardDrawingAnimation = new Timeline();

        double xCord = getStartXCord(currentPlayerNum, numOfPlayers, card1Display.getWidth());
        double yCord = getStartYCord(currentPlayerNum, numOfPlayers, card1Display.getHeight());

        double finalXCord = getStartXCord(targetPlayerNum, numOfPlayers, card1Display.getWidth());
        double finalYCord = getStartYCord(targetPlayerNum, numOfPlayers, card1Display.getHeight());

        cardDrawingAnimation.setCycleCount(1);
        cardDrawingAnimation.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(0), new KeyValue(card1Display.translateXProperty(), xCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(0), new KeyValue(card1Display.translateYProperty(), yCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(0), new KeyValue(card2Display.translateXProperty(), finalXCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(0), new KeyValue(card2Display.translateYProperty(), finalYCord, Interpolator.EASE_OUT)),

                new KeyFrame(Duration.millis(1), new KeyValue(card1Display.visibleProperty(), true, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(1), new KeyValue(card2Display.visibleProperty(), true, Interpolator.LINEAR)),
                
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card1Display.translateXProperty(), finalXCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card1Display.translateYProperty(), finalYCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card2Display.translateXProperty(), xCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card2Display.translateYProperty(), yCord, Interpolator.LINEAR)),

                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card1Display.visibleProperty(), false, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(card2Display.visibleProperty(), false, Interpolator.LINEAR))
            );

        animationQueue.add(cardDrawingAnimation);
        this.delayTime += DEFAULT_WAIT_TIME;
    }

    public void addShieldAnimation() {
        Timeline shieldAnimation = new Timeline();
        shieldAnimation.setCycleCount(1);

        ImageView imgShield = new ImageView(gameTable.getGame().getImageLoader().getShield());
        imgShield.setPreserveRatio(true);

        double imageDimensions = Math.min(this.getScene().getHeight() * 0.8, this.getScene().getWidth()* 0.8);

        imgShield.setFitHeight(imageDimensions);
        imgShield.setFitWidth(imageDimensions);
        imgShield.setVisible(false);

        animationPane.getChildren().add(imgShield);

        double xCord = ((this.getScene().getWidth() / 2.0) - (imageDimensions / 2.0));
        double yCord = ((this.getScene().getHeight() / 2.0) - (imageDimensions / 2.0));

        imgShield.setX(xCord);
        imgShield.setY(yCord);

        int waitTimeInterval = DEFAULT_WAIT_TIME / 10;

        shieldAnimation.getKeyFrames().addAll(
            new KeyFrame(Duration.millis(0), e -> {
                soundEffectPlayer.playSoundEffect(SoundEffectType.SHIELD);
            },new KeyValue(imgShield.visibleProperty(), true, Interpolator.LINEAR)),
            new KeyFrame(Duration.millis(0), new KeyValue(imgShield.scaleXProperty(), 1.0, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(0), new KeyValue(imgShield.scaleYProperty(), 1.0, Interpolator.EASE_OUT)),

            new KeyFrame(Duration.millis(1 * waitTimeInterval), new KeyValue(imgShield.scaleXProperty(), 0.9, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(1 * waitTimeInterval), new KeyValue(imgShield.scaleYProperty(), 0.9, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(2 * waitTimeInterval), new KeyValue(imgShield.scaleXProperty(), 1.0, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(2 * waitTimeInterval), new KeyValue(imgShield.scaleYProperty(), 1.0, Interpolator.EASE_OUT)),

            new KeyFrame(Duration.millis(3 * waitTimeInterval), new KeyValue(imgShield.scaleXProperty(), 0.9, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(3 * waitTimeInterval), new KeyValue(imgShield.scaleYProperty(), 0.9, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(4 * waitTimeInterval), new KeyValue(imgShield.scaleXProperty(), 1.0, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(4 * waitTimeInterval), new KeyValue(imgShield.scaleYProperty(), 1.0, Interpolator.EASE_OUT)),

            new KeyFrame(Duration.millis(5 * waitTimeInterval), new KeyValue(imgShield.scaleXProperty(), 0.9, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(5 * waitTimeInterval), new KeyValue(imgShield.scaleYProperty(), 0.9, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(6 * waitTimeInterval), new KeyValue(imgShield.scaleXProperty(), 1.0, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(6 * waitTimeInterval), new KeyValue(imgShield.scaleYProperty(), 1.0, Interpolator.EASE_OUT)),

            new KeyFrame(Duration.millis(7 * waitTimeInterval), new KeyValue(imgShield.scaleXProperty(), 0.9, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(7 * waitTimeInterval), new KeyValue(imgShield.scaleYProperty(), 0.9, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(8 * waitTimeInterval), new KeyValue(imgShield.scaleXProperty(), 1.0, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(8 * waitTimeInterval), new KeyValue(imgShield.scaleYProperty(), 1.0, Interpolator.EASE_OUT)),

            new KeyFrame(Duration.millis(9 * waitTimeInterval), new KeyValue(imgShield.scaleXProperty(), 0.9, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(9 * waitTimeInterval), new KeyValue(imgShield.scaleYProperty(), 0.9, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(10 * waitTimeInterval), new KeyValue(imgShield.scaleXProperty(), 1.0, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(10 * waitTimeInterval), new KeyValue(imgShield.scaleYProperty(), 1.0, Interpolator.EASE_OUT)),

            new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(imgShield.visibleProperty(), false, Interpolator.LINEAR))
        );

        animationQueue.add(shieldAnimation);
        this.delayTime += DEFAULT_WAIT_TIME;
    }

    public void addSkipAnimation() {
        Timeline skipAnimation = new Timeline();
        skipAnimation.setCycleCount(1);

        ImageView imgSkip = new ImageView(gameTable.getGame().getImageLoader().getSkip());
        imgSkip.setPreserveRatio(true);

        double imageDimensions = Math.min(this.getScene().getHeight() * 0.8, this.getScene().getWidth()* 0.8);

        imgSkip.setFitHeight(imageDimensions);
        imgSkip.setFitWidth(imageDimensions);
        imgSkip.setVisible(false);

        animationPane.getChildren().add(imgSkip);

        double xCord = ((this.getScene().getWidth() / 2.0) - (imageDimensions / 2.0));
        double yCord = ((this.getScene().getHeight() / 2.0) - (imageDimensions / 2.0));

        imgSkip.setX(xCord);
        imgSkip.setY(yCord);

        skipAnimation.getKeyFrames().addAll(
            new KeyFrame(Duration.millis(0), e -> {
                soundEffectPlayer.playSoundEffect(SoundEffectType.SKIP);
            },new KeyValue(imgSkip.visibleProperty(), true, Interpolator.LINEAR)),
            new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(imgSkip.visibleProperty(), false, Interpolator.LINEAR))
        );

        animationQueue.add(skipAnimation);
        this.delayTime += DEFAULT_WAIT_TIME;
    }

    public void addThiefAnimationPart1(int numOfPlayers, int currentPlayerNum, int targetPlayerNum) {
        ImageView imgThief = new ImageView(gameTable.getGame().getImageLoader().getThief());
        imgThief.setVisible(false);

        double thiefDimensions = Math.min(this.getScene().getHeight() * 0.2, this.getScene().getWidth()* 0.2);
        imgThief.setFitHeight(thiefDimensions);
        imgThief.setFitWidth(thiefDimensions);

        animationPane.getChildren().add(imgThief);
        Timeline thiefAnimation = new Timeline();

        double xCord = getStartXCord(currentPlayerNum, numOfPlayers, thiefDimensions);
        double yCord = getStartYCord(currentPlayerNum, numOfPlayers, thiefDimensions);

        double finalXCord = getStartXCord(targetPlayerNum, numOfPlayers, thiefDimensions);
        double finalYCord = getStartYCord(targetPlayerNum, numOfPlayers, thiefDimensions);

        thiefAnimation.setCycleCount(1);
        thiefAnimation.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(0), new KeyValue(imgThief.translateXProperty(), xCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(0), new KeyValue(imgThief.translateYProperty(), yCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(1), new KeyValue(imgThief.visibleProperty(), true, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(imgThief.translateXProperty(), finalXCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(imgThief.translateYProperty(), finalYCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(imgThief.visibleProperty(), false, Interpolator.LINEAR))
            );

        animationQueue.add(thiefAnimation);
        this.delayTime += DEFAULT_WAIT_TIME;
    }

    public void addThiefAnimationPart2(int numOfPlayers, int currentPlayerNum, int targetPlayerNum) {
        ImageView imgThief = new ImageView(gameTable.getGame().getImageLoader().getThief());
        imgThief.setVisible(false);

        double thiefDimensions = Math.min(this.getScene().getHeight() * 0.2, this.getScene().getWidth()* 0.2);
        imgThief.setFitHeight(thiefDimensions);
        imgThief.setFitWidth(thiefDimensions);

        animationPane.getChildren().add(imgThief);
        Timeline thiefAnimation = new Timeline();

        double xCord = getStartXCord(targetPlayerNum, numOfPlayers, thiefDimensions);
        double yCord = getStartYCord(targetPlayerNum, numOfPlayers, thiefDimensions);

        double finalXCord = getStartXCord(currentPlayerNum, numOfPlayers, thiefDimensions);
        double finalYCord = getStartYCord(currentPlayerNum, numOfPlayers, thiefDimensions);

        thiefAnimation.setCycleCount(1);
        thiefAnimation.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(0), new KeyValue(imgThief.translateXProperty(), xCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(0), new KeyValue(imgThief.translateYProperty(), yCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(1), new KeyValue(imgThief.visibleProperty(), true, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(imgThief.translateXProperty(), finalXCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(imgThief.translateYProperty(), finalYCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(imgThief.visibleProperty(), false, Interpolator.LINEAR))
            );

        animationQueue.add(thiefAnimation);
        this.delayTime += DEFAULT_WAIT_TIME;
    }

    public void addDisputeAnimation(int numOfPlayers, ArrayList<Player> players, int winningPlayerIndex, ArrayList<Card> cards, boolean isDarkMode) {
        Timeline cardDisputeAnimation = new Timeline();
        cardDisputeAnimation.setCycleCount(1);

        for (int i = 0; i < players.size(); i++) {
            Player currentPlayer = players.get(i);
            Card disputeCard = cards.get(i);
            CardDisplaySquare disputeCardDisplay = disputeCard.getSquareCardDisplay(isDarkMode);
            double cardDimensions = Math.min(this.getScene().getHeight() * 0.1, this.getScene().getWidth()* 0.1);
            disputeCardDisplay.setHeight(cardDimensions);

            disputeCardDisplay.setVisible(false);
            animationPane.getChildren().add(disputeCardDisplay);

            Point2D startingLocation = gameTable.getDrawPilePosition();

            double xCord = startingLocation.getX();
            double yCord = startingLocation.getY();

            double finalXCord = (getStartXCord(currentPlayer.getPlayerNum(), numOfPlayers, disputeCardDisplay.getWidth()) + getCenterXCord(disputeCardDisplay.getWidth())) / 2.0;
            double finalYCord = (getStartYCord(currentPlayer.getPlayerNum(), numOfPlayers, disputeCardDisplay.getHeight()) + getCenterYCord(disputeCardDisplay.getHeight())) / 2.0;

            cardDisputeAnimation.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(0), new KeyValue(disputeCardDisplay.translateXProperty(), xCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(0), new KeyValue(disputeCardDisplay.translateYProperty(), yCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(0), new KeyValue(disputeCardDisplay.visibleProperty(), true, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(disputeCardDisplay.translateXProperty(), finalXCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(disputeCardDisplay.translateYProperty(), finalYCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(2 * DEFAULT_WAIT_TIME), new KeyValue(disputeCardDisplay.translateXProperty(), finalXCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(2 * DEFAULT_WAIT_TIME), new KeyValue(disputeCardDisplay.translateYProperty(), finalYCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(2 * DEFAULT_WAIT_TIME), new KeyValue(disputeCardDisplay.scaleXProperty(), 1.0, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(2 * DEFAULT_WAIT_TIME), new KeyValue(disputeCardDisplay.scaleYProperty(), 1.0, Interpolator.EASE_OUT))
            );

            if (i == winningPlayerIndex) {
                cardDisputeAnimation.getKeyFrames().addAll(
                    new KeyFrame(Duration.millis(3 * DEFAULT_WAIT_TIME - 1), new KeyValue(disputeCardDisplay.translateXProperty(), getCenterXCord(disputeCardDisplay.getWidth()), Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(3 * DEFAULT_WAIT_TIME - 1), new KeyValue(disputeCardDisplay.translateYProperty(), getCenterYCord(disputeCardDisplay.getHeight()), Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(3 * DEFAULT_WAIT_TIME - 1), new KeyValue(disputeCardDisplay.scaleXProperty(), 3.0, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(3 * DEFAULT_WAIT_TIME - 1), new KeyValue(disputeCardDisplay.scaleYProperty(), 3.0, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(3 * DEFAULT_WAIT_TIME), new KeyValue(disputeCardDisplay.visibleProperty(), false, Interpolator.LINEAR))
                );
            }
            else {
                cardDisputeAnimation.getKeyFrames().addAll(
                    new KeyFrame(Duration.millis(3 * DEFAULT_WAIT_TIME - 1), new KeyValue(disputeCardDisplay.translateXProperty(), xCord, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(3 * DEFAULT_WAIT_TIME - 1), new KeyValue(disputeCardDisplay.translateYProperty(), yCord, Interpolator.EASE_OUT)),
                    new KeyFrame(Duration.millis(3 * DEFAULT_WAIT_TIME - 1), new KeyValue(disputeCardDisplay.scaleXProperty(), 0.5, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(3 * DEFAULT_WAIT_TIME - 1), new KeyValue(disputeCardDisplay.scaleYProperty(), 0.5, Interpolator.LINEAR)),
                    new KeyFrame(Duration.millis(3 * DEFAULT_WAIT_TIME), new KeyValue(disputeCardDisplay.visibleProperty(), false, Interpolator.LINEAR))
                );
            }
        }
        animationQueue.add(cardDisputeAnimation);
        this.delayTime += 3 * DEFAULT_WAIT_TIME;
    }

    public void addRedistributeHandsAnimation(int numOfPlayers) {
        Timeline cardDisputeAnimation = new Timeline();
        cardDisputeAnimation.setCycleCount(1);

        for (int i = 0; i < numOfPlayers; i++) {
            CardDisplaySquare distributedCardDisplay = new CardDisplaySquare(gameTable.getGame().getImageLoader());
            double cardDimensions = Math.min(this.getScene().getHeight() * 0.1, this.getScene().getWidth()* 0.1);
            distributedCardDisplay.setHeight(cardDimensions);

            distributedCardDisplay.setVisible(false);
            animationPane.getChildren().add(distributedCardDisplay);

            double xCord = getStartXCord(i + 1, numOfPlayers, distributedCardDisplay.getWidth());
            double yCord = getStartYCord(i + 1, numOfPlayers, distributedCardDisplay.getHeight());

            double finalXCord = this.getScene().getWidth() / 2.0 - distributedCardDisplay.getWidth() / 2.0;
            double finalYCord = this.getScene().getHeight() / 2.0 - distributedCardDisplay.getHeight() / 2.0;

            cardDisputeAnimation.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(0), new KeyValue(distributedCardDisplay.translateXProperty(), xCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(0), new KeyValue(distributedCardDisplay.translateYProperty(), yCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(1), new KeyValue(distributedCardDisplay.visibleProperty(), true, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(distributedCardDisplay.translateXProperty(), finalXCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(distributedCardDisplay.translateYProperty(), finalYCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(2 * DEFAULT_WAIT_TIME), new KeyValue(distributedCardDisplay.translateXProperty(), xCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(2 * DEFAULT_WAIT_TIME), new KeyValue(distributedCardDisplay.translateYProperty(), yCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(2 * DEFAULT_WAIT_TIME), new KeyValue(distributedCardDisplay.visibleProperty(), false, Interpolator.LINEAR))
            );
        }
        animationQueue.add(cardDisputeAnimation);
        this.delayTime += 2 * DEFAULT_WAIT_TIME;
    }

    public void addPlayCardAnimation(int playerNum, int numOfPlayers, boolean isDarkMode, Card playedCard, TowerPosition position) {
        CardDisplaySquare playedCardDisplay = playedCard.getSquareCardDisplay(isDarkMode);
        playedCardDisplay.setVisible(false);

        double cardDimensions = Math.min(this.getScene().getHeight() * 0.1, this.getScene().getWidth()* 0.1);
        playedCardDisplay.setHeight(cardDimensions);

        animationPane.getChildren().add(playedCardDisplay);
        Timeline cardPlayingAnimation = new Timeline();

        double xCord = getStartXCord(playerNum, numOfPlayers, playedCardDisplay.getWidth());
        double yCord = getStartYCord(playerNum, numOfPlayers, playedCardDisplay.getHeight());

        double finalXCord = getTowerXCord(position);
        double finalYCord = getTowerYCord(position);



        cardPlayingAnimation.setCycleCount(1);
        cardPlayingAnimation.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(0), new KeyValue(playedCardDisplay.translateXProperty(), xCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(0), new KeyValue(playedCardDisplay.translateYProperty(), yCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(0), e -> {
                    soundEffectPlayer.playSoundEffect(SoundEffectType.PLAY);
                }, new KeyValue(playedCardDisplay.visibleProperty(), true, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(playedCardDisplay.translateXProperty(), finalXCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), e -> {
                    refreshGameTable();
                }, new KeyValue(playedCardDisplay.translateYProperty(), finalYCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(playedCardDisplay.visibleProperty(), false, Interpolator.LINEAR))
            );

        animationQueue.add(cardPlayingAnimation);
        this.delayTime += DEFAULT_WAIT_TIME;
    }

    public void addDrawCardAnimation(int playerNum, int numOfPlayers) {
        CardDisplaySquare playedCardDisplay = new CardDisplaySquare(gameTable.getGame().getImageLoader());
        playedCardDisplay.setVisible(false);

        double cardDimensions = Math.min(this.getScene().getHeight() * 0.1, this.getScene().getWidth()* 0.1);
        playedCardDisplay.setHeight(cardDimensions);

        animationPane.getChildren().add(playedCardDisplay);
        Timeline cardDrawingAnimation = new Timeline();

        Point2D startingLocation = gameTable.getDrawPilePosition();

        double xCord = startingLocation.getX();
        double yCord = startingLocation.getY();

        double finalXCord = getStartXCord(playerNum, numOfPlayers, playedCardDisplay.getWidth());
        double finalYCord = getStartYCord(playerNum, numOfPlayers, playedCardDisplay.getHeight());

        cardDrawingAnimation.setCycleCount(1);
        cardDrawingAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
            soundEffectPlayer.playSoundEffect(SoundEffectType.DRAW);
        }, new KeyValue(playedCardDisplay.visibleProperty(), false, Interpolator.LINEAR)));
        cardDrawingAnimation.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(0), new KeyValue(playedCardDisplay.translateXProperty(), xCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(0), new KeyValue(playedCardDisplay.translateYProperty(), yCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(1), new KeyValue(playedCardDisplay.visibleProperty(), true, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(playedCardDisplay.translateXProperty(), finalXCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(playedCardDisplay.translateYProperty(), finalYCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(DEFAULT_WAIT_TIME), new KeyValue(playedCardDisplay.visibleProperty(), false, Interpolator.LINEAR))
            );

        animationQueue.add(cardDrawingAnimation);
        this.delayTime += DEFAULT_WAIT_TIME;
    }

    public void addDrawCardAnimation(int playerNum, int numOfPlayers, int numOfCards) {
        CardDisplaySquare playedCardDisplay = new CardDisplaySquare(gameTable.getGame().getImageLoader());
        playedCardDisplay.setVisible(false);

        double cardDimensions = Math.min(this.getScene().getHeight() * 0.1, this.getScene().getWidth()* 0.1);
        playedCardDisplay.setHeight(cardDimensions);

        animationPane.getChildren().add(playedCardDisplay);
        Timeline cardDrawingAnimation = new Timeline();

        Point2D startingLocation = gameTable.getDrawPilePosition();

        double xCord = startingLocation.getX();
        double yCord = startingLocation.getY();

        double finalXCord = getStartXCord(playerNum, numOfPlayers, playedCardDisplay.getWidth());
        double finalYCord = getStartYCord(playerNum, numOfPlayers, playedCardDisplay.getHeight());

        int drawDuration = DEFAULT_WAIT_TIME / numOfCards;

        cardDrawingAnimation.setCycleCount(numOfCards);
        cardDrawingAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
            soundEffectPlayer.playSoundEffect(SoundEffectType.DRAW);
        }, new KeyValue(playedCardDisplay.visibleProperty(), false, Interpolator.LINEAR)));
        cardDrawingAnimation.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(0), new KeyValue(playedCardDisplay.translateXProperty(), xCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(0), new KeyValue(playedCardDisplay.translateYProperty(), yCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(1), new KeyValue(playedCardDisplay.visibleProperty(), true, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(drawDuration), new KeyValue(playedCardDisplay.translateXProperty(), finalXCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(drawDuration), new KeyValue(playedCardDisplay.translateYProperty(), finalYCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(drawDuration), new KeyValue(playedCardDisplay.visibleProperty(), false, Interpolator.LINEAR))
            );

        animationQueue.add(cardDrawingAnimation);
        this.delayTime += DEFAULT_WAIT_TIME;
    }

    public void refreshPlayerHand() {
        gameTable.refreshPlayerHand();
    }

    public void playAnimationsInQueue() {
        if (!animationQueue.isEmpty()) {
            Animation firstAnimation = animationQueue.get(0);

            for (int i = 0; i < animationQueue.size(); i++) {
                if (i == (animationQueue.size() - 1)) {
                    animationQueue.get(i).setOnFinished(e -> {
                        refreshGameTable();
                        animationPane.getChildren().clear();
                    });
                }
                else {
                    Animation nextAnimation = animationQueue.get(i+1);
                    animationQueue.get(i).setOnFinished(e -> {
                        nextAnimation.play();
                    });
                }
            }
    
            firstAnimation.play();
            animationQueue.clear();
        }
        else {
            refreshGameTable();
        }
    }

    public void setGameNull() {
        this.gameTable.setGameNull();
        this.gameTable = null;
        this.stage = null;
        this.animationPane = null;
        this.animationQueue = null;
        soundEffectPlayer.close();
        this.soundEffectPlayer = null;
        this.backgroundMediaPlayer.dispose();
        this.backgroundMediaPlayer = null;
    }

    public void closeSoundResources() {
        this.backgroundMediaPlayer.stop();
        this.backgroundMediaPlayer.dispose();
        this.soundEffectPlayer.close();
    }

    public void playSoundEffect(SoundEffectType type) {
        if (doSoundEffects) {
            this.soundEffectPlayer.playSoundEffect(type);
        }
    }

    public void playBackgroundMusic() {
        if (doBackgroundMusic) {
            this.backgroundMediaPlayer.play();
        }
    }

    public Stage getStage() {
        return this.stage;
    }

    public void refreshGameTable() {
        gameTable.refreshPane();
    }

    public Card getSelectedCard() {
        return gameTable.getSelectedCard();
    }

    public GameInnerPane getGameTable() {
        return this.gameTable;
    }

    public void startGame() {
        gameTable.getGame().startFirstPlayerTurn();
    }

    public GamePane(int numOfRounds, int numOfPlayers, Stage stage, boolean doBackgroundMusic, boolean doSoundEffects, DeckType deckType, String userName) {
        super();
        this.delayTime = 0;
        this.soundEffectPlayer = new SoundEffectPlayer();
        this.animationQueue = new ArrayList<Animation>();
        this.doBackgroundMusic = doBackgroundMusic;
        this.doSoundEffects = doSoundEffects;
        this.stage = stage;
        this.gameTable = new GameInnerPane(numOfRounds, numOfPlayers, deckType, userName, this);
        this.animationPane = new Pane();
        animationPane.setMouseTransparent(true);
        this.getChildren().add(gameTable);
        this.getChildren().add(animationPane);

        this.backgroundMediaPlayer = new MediaPlayer(new Media(this.getClass().getResource("BackgroundMusicLoop.wav").toExternalForm()));
        backgroundMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        playBackgroundMusic();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        gameTable.setMinHeight(height);
        gameTable.setMaxHeight(height);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        gameTable.setMinWidth(width);
        gameTable.setMaxWidth(width);
    }
}
