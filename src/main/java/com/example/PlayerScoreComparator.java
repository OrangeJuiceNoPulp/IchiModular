package com.example;

import java.util.Comparator;

public class PlayerScoreComparator implements Comparator<Player>{
    @Override
    public int compare(Player o1, Player o2) {
        if (o1.getOverallScore() > o2.getOverallScore()) {
            return -1;
        }
        else if (o1.getOverallScore() < o2.getOverallScore()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}