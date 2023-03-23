package com.example;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundEffectPlayer {
    public enum SoundEffectType {
        ERROR, DRAW, GAIN_POINTS, LOSE_POINTS
    }

    private MediaPlayer errorPlayer;
    private MediaPlayer drawPlayer;
    private MediaPlayer gainPointsPlayer;
    private MediaPlayer losePointsPlayer;

    public void playSoundEffect(SoundEffectType type) {
        //TODO implement me
        switch (type) {
            case DRAW:
                drawPlayer.play();
                break;
            case ERROR:
                errorPlayer.play();
                break;
            case GAIN_POINTS:
                gainPointsPlayer.play();
                break;
            case LOSE_POINTS:
                losePointsPlayer.play();
                break;
            default:
                break;
        }
    }

    public SoundEffectPlayer() {
        errorPlayer = new MediaPlayer(new Media(this.getClass().getResource("ErrorSound.wav").toExternalForm()));
        errorPlayer.setOnEndOfMedia(new Runnable() { 
            public void run() {
                errorPlayer.stop();
            }
        });
        
        drawPlayer = new MediaPlayer(new Media(this.getClass().getResource("ErrorSound.wav").toExternalForm()));
        gainPointsPlayer = new MediaPlayer(new Media(this.getClass().getResource("ErrorSound.wav").toExternalForm()));
        losePointsPlayer = new MediaPlayer(new Media(this.getClass().getResource("ErrorSound.wav").toExternalForm()));
    }
}
