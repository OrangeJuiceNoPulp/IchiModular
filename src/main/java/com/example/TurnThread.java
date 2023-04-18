package com.example;

public class TurnThread extends Thread {
    private Player currentPlayer;
    public void run() {
        currentPlayer.startTurn();
    }

    public TurnThread(Player player) {
        this.currentPlayer = player;
    }
}
