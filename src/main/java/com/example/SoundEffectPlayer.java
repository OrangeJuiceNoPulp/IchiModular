package com.example;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundEffectPlayer {
    public enum SoundEffectType {
        ERROR, DRAW, GAIN_POINTS, LOSE_POINTS, SKIP, REVERSE, PLAY, SHIELD, FLIP
    }

    private MediaPlayer errorPlayer;
    private MediaPlayer drawPlayer;
    private MediaPlayer gainPointsPlayer;
    private MediaPlayer losePointsPlayer;
    private MediaPlayer skipPlayer;
    private MediaPlayer reversePlayer;
    private MediaPlayer playPlayer;
    private MediaPlayer shieldPlayer;
    private MediaPlayer flipPlayer;

    public void playSoundEffect(SoundEffectType type) {
        //TODO implement me
        switch (type) {
            case DRAW:
                drawPlayer.stop();
                drawPlayer.play();
                break;
            case ERROR:
                errorPlayer.stop();
                errorPlayer.play();
                break;
            case GAIN_POINTS:
                gainPointsPlayer.stop();
                gainPointsPlayer.play();
                break;
            case LOSE_POINTS:
                losePointsPlayer.stop();
                losePointsPlayer.play();
                break;
            case REVERSE:
                reversePlayer.stop();
                reversePlayer.play();
                break;
            case SKIP:
                skipPlayer.stop();
                skipPlayer.play();
                break;
            case PLAY:
                playPlayer.stop();
                playPlayer.play();
                break;
            case SHIELD:
                shieldPlayer.stop();
                shieldPlayer.play();
                break;
            case FLIP:
                flipPlayer.stop();
                flipPlayer.play();
                break;
            default:
                break;
        }
    }

    public void close() {
        errorPlayer.stop();
        drawPlayer.stop();
        gainPointsPlayer.stop();
        losePointsPlayer.stop();
        skipPlayer.stop();
        reversePlayer.stop();
        playPlayer.stop();
        shieldPlayer.stop();
        flipPlayer.stop();

        errorPlayer.dispose();;
        drawPlayer.dispose();
        gainPointsPlayer.dispose();
        losePointsPlayer.dispose();
        skipPlayer.dispose();
        reversePlayer.dispose();
        playPlayer.dispose();
        shieldPlayer.dispose();
        flipPlayer.dispose();
    }

    public SoundEffectPlayer() {
        errorPlayer = new MediaPlayer(new Media(this.getClass().getResource("IchiErrorSound.wav").toExternalForm()));
        errorPlayer.setOnEndOfMedia(new Runnable() { 
            public void run() {
                errorPlayer.stop();
            }
        });
        
        drawPlayer = new MediaPlayer(new Media(this.getClass().getResource("IchiDrawSound.wav").toExternalForm()));
        drawPlayer.setOnEndOfMedia(new Runnable() { 
            public void run() {
                drawPlayer.stop();
            }
        });

        gainPointsPlayer = new MediaPlayer(new Media(this.getClass().getResource("IchiGainPointsSound.wav").toExternalForm()));
        gainPointsPlayer.setOnEndOfMedia(new Runnable() { 
            public void run() {
                gainPointsPlayer.stop();
            }
        });

        losePointsPlayer = new MediaPlayer(new Media(this.getClass().getResource("IchiLosePointsSound.wav").toExternalForm()));
        losePointsPlayer.setOnEndOfMedia(new Runnable() { 
            public void run() {
                losePointsPlayer.stop();
            }
        });

        skipPlayer = new MediaPlayer(new Media(this.getClass().getResource("IchiSkipSound.wav").toExternalForm()));
        skipPlayer.setOnEndOfMedia(new Runnable() { 
            public void run() {
                skipPlayer.stop();
            }
        });

        reversePlayer = new MediaPlayer(new Media(this.getClass().getResource("IchiReverseSound.wav").toExternalForm()));
        reversePlayer.setOnEndOfMedia(new Runnable() { 
            public void run() {
                reversePlayer.stop();
            }
        });

        playPlayer = new MediaPlayer(new Media(this.getClass().getResource("IchiPlaySound.wav").toExternalForm()));
        playPlayer.setOnEndOfMedia(new Runnable() { 
            public void run() {
                playPlayer.stop();
            }
        });

        shieldPlayer = new MediaPlayer(new Media(this.getClass().getResource("IchiShieldSound.wav").toExternalForm()));
        shieldPlayer.setOnEndOfMedia(new Runnable() { 
            public void run() {
                shieldPlayer.stop();
            }
        });

        flipPlayer = new MediaPlayer(new Media(this.getClass().getResource("IchiFlipSound.wav").toExternalForm()));
        flipPlayer.setOnEndOfMedia(new Runnable() { 
            public void run() {
                flipPlayer.stop();
            }
        });

    }
}
