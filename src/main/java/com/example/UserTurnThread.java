package com.example;

public class UserTurnThread extends Thread {
    private Player currentPlayer;
    public void run() {
        currentPlayer.endTurn();
    }
    public UserTurnThread(Player player) {
        this.currentPlayer = player;
    }
}
