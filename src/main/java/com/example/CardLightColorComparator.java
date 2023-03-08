package com.example;

import java.util.Comparator;

public class CardLightColorComparator implements Comparator<Card>{
    @Override
    public int compare(Card o1, Card o2) {
        if (o1.getID(false) < o2.getID(false)) {
            return -1;
        }
        else if (o1.getID(false) > o2.getID(false)) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
