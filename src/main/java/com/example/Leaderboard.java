package com.example;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Leaderboard {

    private File filePlayers;
    private File fileScores;

    public ArrayList<PlayerScorePair> readFromLeaderboard(int numOfPlayers, int numOfRounds) {
        initializeLeaderboardFiles(numOfPlayers, numOfRounds);
        ArrayList<PlayerScorePair> playerScorePairs = readFromLeaderboard(filePlayers, fileScores, numOfPlayers, numOfRounds);
        playerScorePairs.sort(new PlayerScorePairComparator());

        writeToLeaderboard(filePlayers, fileScores, playerScorePairs, numOfPlayers, numOfRounds);
        return playerScorePairs;
    }

    // Returns the player and score data, and will add new entries if needed, but
    // does not sort it.
    private ArrayList<PlayerScorePair> readFromLeaderboard(File leaderboardPlayerFile, File leaderboardScoreFile, int numOfPlayers, int numOfRounds) {
        ArrayList<PlayerScorePair> playerScorePairs = new ArrayList<PlayerScorePair>(11);

        if ((leaderboardPlayerFile != null) && (leaderboardScoreFile != null)) {
            try {
                Scanner scnrPlayers = new Scanner(leaderboardPlayerFile);
                Scanner scnrScores = new Scanner(leaderboardScoreFile);

                while ((scnrPlayers.hasNextLine()) && (scnrScores.hasNextLine())) {
                    String playerData = scnrPlayers.nextLine();
                    int scoreData;
                    try {
                        scoreData = Integer.parseInt(scnrScores.nextLine());
                    } catch (NumberFormatException numEx) {
                        scoreData = 0;
                    }
                    playerScorePairs.add(new PlayerScorePair(playerData, scoreData));
                }

                scnrPlayers.close();
                scnrScores.close();

                // If for some reason, there are less than 10 players listed in the file
                NameGenerator nameGen = new NameGenerator();
                while (playerScorePairs.size() < 10) {
                    Random rand = new Random();
                    playerScorePairs.add(new PlayerScorePair(nameGen.getRandomName(1) + " (Bot)", rand.nextInt(401) + 100));
                }

                return playerScorePairs;

            } catch (Exception ex) { 
                // If the leaderboard cannot be loaded, a new one is created.
                NameGenerator nameGen = new NameGenerator();
                for (int i = 0; i < 10; i++) {
                    Random rand = new Random();
                    playerScorePairs.add(new PlayerScorePair(nameGen.getRandomName(1)  + " (Bot)", rand.nextInt(401) + 100));
                    // Adds random names with random scores to the newly created leaderboard.
                }
                playerScorePairs.sort(new PlayerScorePairComparator());
                writeToLeaderboard(leaderboardPlayerFile, leaderboardScoreFile, playerScorePairs, numOfPlayers, numOfRounds);
                return playerScorePairs;
            }
        }
        // If the leaderboard cannot be loaded, a new one is created.
        else {
            NameGenerator nameGen = new NameGenerator();
            for (int i = 0; i < 10; i++) {
                Random rand = new Random();
                playerScorePairs.add(new PlayerScorePair(nameGen.getRandomName(1)  + " (Bot)", rand.nextInt(401) + 100));
                // Adds random names with random scores to the newly created leaderboard.
            }
            playerScorePairs.sort(new PlayerScorePairComparator());
            writeToLeaderboard(leaderboardPlayerFile, leaderboardScoreFile, playerScorePairs, numOfPlayers, numOfRounds);
            return playerScorePairs;
        }
    }

    private void writeToLeaderboard(File leaderboardPlayerFile, File leaderboardScoreFile,
            ArrayList<PlayerScorePair> newScorePairs, int numOfPlayers, int numOfRounds) {

        FileWriter playerFileWriter = null;
        FileWriter scoreFileWriter = null;

        try {
            playerFileWriter = new FileWriter(leaderboardPlayerFile);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            scoreFileWriter = new FileWriter(leaderboardScoreFile);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            for (PlayerScorePair pair : newScorePairs) {
                playerFileWriter.write(pair.playerName+ "\n"); 
                scoreFileWriter.write(pair.score + "\n");
                System.out.println("write success");
            }
        }
        catch (Exception ex) {
            System.out.println("write failure");
        }
        
        try {
            playerFileWriter.close();
        }
        catch (Exception ex) {

        }    

        try {
            scoreFileWriter.close();
        }
        catch (Exception ex) {

        }    


    }

    private void initializeLeaderboardFiles(int numOfPlayers, int numOfRounds) {
        try {
            if (numOfPlayers == 2) {
                if (numOfRounds == 1) {
                    filePlayers = new File(this.getClass().getResource("Leaderboard2Player1RoundNames.txt").toURI());
                    fileScores = new File(this.getClass().getResource("Leaderboard2Player1RoundScores.txt").toURI());
                } else if (numOfRounds == 3) {
                    filePlayers = new File(this.getClass().getResource("Leaderboard2Player3RoundNames.txt").toURI());
                    fileScores = new File(this.getClass().getResource("Leaderboard2Player3RoundScores.txt").toURI());
                }
                // Else there are 5 rounds
                else {
                    filePlayers = new File(this.getClass().getResource("Leaderboard2Player5RoundNames.txt").toURI()).getAbsoluteFile();
                    fileScores = new File(this.getClass().getResource("Leaderboard2Player5RoundScores.txt").toURI());
                }
            } else if (numOfPlayers == 3) {
                if (numOfRounds == 1) {
                    filePlayers = new File(this.getClass().getResource("Leaderboard3Player1RoundNames.txt").toURI());
                    fileScores = new File(this.getClass().getResource("Leaderboard3Player1RoundScores.txt").toURI());
                } else if (numOfRounds == 3) {
                    filePlayers = new File(this.getClass().getResource("Leaderboard3Player3RoundNames.txt").toURI());
                    fileScores = new File(this.getClass().getResource("Leaderboard3Player3RoundScores.txt").toURI());
                }
                // Else there are 5 rounds
                else {
                    filePlayers = new File(this.getClass().getResource("Leaderboard3Player5RoundNames.txt").toURI());
                    fileScores = new File(this.getClass().getResource("Leaderboard3Player5RoundScores.txt").toURI());
                }
            }
            // Else there are 4 players
            else {
                if (numOfRounds == 1) {
                    filePlayers = new File(this.getClass().getResource("Leaderboard4Player1RoundNames.txt").toURI());
                    fileScores = new File(this.getClass().getResource("Leaderboard4Player1RoundScores.txt").toURI());
                } else if (numOfRounds == 3) {
                    filePlayers = new File(this.getClass().getResource("Leaderboard4Player3RoundNames.txt").toURI());
                    fileScores = new File(this.getClass().getResource("Leaderboard4Player3RoundScores.txt").toURI());
                }
                // Else there are 5 rounds
                else {
                    filePlayers = new File(this.getClass().getResource("Leaderboard4Player5RoundNames.txt").toURI());
                    fileScores = new File(this.getClass().getResource("Leaderboard4Player5RoundScores.txt").toURI());
                }
            }
            System.out.println("loading success");
        }
        catch (Exception ex) {
            System.out.println("loading failed");

            filePlayers = null;
            fileScores = null;
        }
        
    }

    public void updateLeaderboard(ArrayList<Player> players, int numOfPlayers, int numOfRounds) throws Exception {
        initializeLeaderboardFiles(numOfPlayers, numOfRounds);

        ArrayList<PlayerScorePair> playerScorePairs = readFromLeaderboard(filePlayers, fileScores, numOfPlayers, numOfRounds);

        for (Player player : players) {
            playerScorePairs.add(new PlayerScorePair(player.getTrueName(), player.getOverallScore()));
        }
        playerScorePairs.sort(new PlayerScorePairComparator());
        
        while (playerScorePairs.size() > 10) {
            playerScorePairs.remove(playerScorePairs.size() - 1);
        }

        writeToLeaderboard(filePlayers, fileScores, playerScorePairs, numOfPlayers, numOfRounds);
    }

    public class PlayerScorePair {
        private String playerName;
        private int score;

        public int getScore() {
            return this.score;
        }
        public String getName() {
            return this.playerName;
        }

        public PlayerScorePair(String player, int score) {
            this.playerName = player;
            this.score = score;
        }
    }

    public class PlayerScorePairComparator implements Comparator<PlayerScorePair> {
        @Override
        public int compare(PlayerScorePair o1, PlayerScorePair o2) {
            if (o1.score > o2.score) {
                return -1;
            } else if (o1.score < o2.score) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
