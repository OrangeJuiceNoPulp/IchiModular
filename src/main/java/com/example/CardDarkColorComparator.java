package com.example;

import java.util.Comparator;

public class CardDarkColorComparator implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        if (o1.getID(true) < o2.getID(true)) {
            return -1;
        }
        else if (o1.getID(true) > o2.getID(true)) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
