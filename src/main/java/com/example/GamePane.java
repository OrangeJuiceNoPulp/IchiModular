package com.example;

import java.util.ArrayList;

import com.example.Deck.DeckType;
import com.example.SoundEffectPlayer.SoundEffectType;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePane extends StackPane {
    private GameInnerPane gameTable;
    private Pane animationPane;

    private CardDisplaySquare playedCardDisplay;

    private ArrayList<Animation> animationQueue;

    private Stage stage;

    private boolean doSoundEffects;
    private boolean doBackgroundMusic;

    private SoundEffectPlayer soundEffectPlayer;
    private MediaPlayer backgroundMediaPlayer;

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

    public void addPlayCardAnimation(int playerNum, int numOfPlayers, boolean isDarkMode, Card playedCard) {
        CardDisplaySquare playedCardDisplay = playedCard.getSquareCardDisplay(isDarkMode);
        playedCardDisplay.setVisible(false);

        double cardDimensions = Math.min(this.getScene().getHeight() * 0.1, this.getScene().getWidth()* 0.1);
        playedCardDisplay.setHeight(cardDimensions);

        animationPane.getChildren().add(playedCardDisplay);
        Timeline cardPlayingAnimation = new Timeline();

        double xCord = getStartXCord(playerNum, numOfPlayers, playedCardDisplay.getWidth());
        double yCord = getStartYCord(playerNum, numOfPlayers, playedCardDisplay.getHeight());

        double finalXCord = getCenterXCord(playedCardDisplay.getWidth());
        double finalYCord = getCenterYCord(playedCardDisplay.getHeight());

        cardPlayingAnimation.setCycleCount(1);
        cardPlayingAnimation.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(0), new KeyValue(playedCardDisplay.translateXProperty(), xCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(0), new KeyValue(playedCardDisplay.translateYProperty(), yCord, Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(1), new KeyValue(playedCardDisplay.visibleProperty(), true, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(2500), new KeyValue(playedCardDisplay.translateXProperty(), finalXCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(2500), new KeyValue(playedCardDisplay.translateYProperty(), finalYCord, Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(2500), new KeyValue(playedCardDisplay.visibleProperty(), false, Interpolator.LINEAR))
            );
        animationQueue.add(cardPlayingAnimation);
    }

    public void refreshPlayerHand() {
        gameTable.refreshPlayerHand();
    }

    public void playAnimationsInQueue() {
        System.out.println("Playing " + animationQueue.size() + " animations");

        animationQueue.add(new TranslateTransition(Duration.millis(500), playedCardDisplay));

        for (int i = 0; i < animationQueue.size(); i++) {
            if (i == (animationQueue.size() - 1)) {
                animationQueue.get(i).setOnFinished(e -> {
                    gameTable.getGame().getCurrentPlayer().endTurn();
                    //animationPane.getChildren().clear();
                    gameTable.refreshPane();
                });
            }
            else {
                Animation nextAnimation = animationQueue.get(i+1);
                animationQueue.get(i).setOnFinished(e -> {
                    nextAnimation.play();
                });
            }
        }

        if (animationQueue.isEmpty()) {
            animationQueue.add(new TranslateTransition(Duration.millis(500), playedCardDisplay));
        }

        Animation firstAnimation = animationQueue.get(0);
        animationQueue.clear();
        firstAnimation.play();
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
