package com.example;

import java.util.ArrayList;

import com.example.Deck.DeckType;
import com.example.SoundEffectPlayer.SoundEffectType;

import javafx.animation.Animation;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class GamePane extends StackPane {
    private GameInnerPane gameTable;
    private Pane animationPane;

    private ArrayList<Animation> animationQueue;

    private Stage stage;

    private boolean doSoundEffects;
    private boolean doBackgroundMusic;

    private SoundEffectPlayer soundEffectPlayer;
    private MediaPlayer backgroundMediaPlayer;

    public void playAnimationsInQueue() {
        Animation firstAnimation = animationQueue.get(0);

        for (int i = 0; i < animationQueue.size(); i++) {
            if (i == (animationQueue.size() - 1)) {
                animationQueue.get(i).setOnFinished(e -> {
                    gameTable.getGame().getCurrentPlayer().endTurn();
                });
            }
            else {
                Animation nextAnimation = animationQueue.get(i+1);
                animationQueue.get(i).setOnFinished(e -> {
                    nextAnimation.play();
                });
            }
        }

        animationQueue.clear();
        firstAnimation.play();
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
        //animationPane.setMouseTransparent(true);
        this.getChildren().add(gameTable);
        //this.getChildren().add(animationPane);

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
