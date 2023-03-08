package com.example;

import java.util.Comparator;

public class CardLightValueComparator implements Comparator<Card> {
    // The card's ID number is determined by the color of the card and the value of the card.
    // The thousands place for the card's ID number determines the color, whereas the numbers before
    // the thousands place determine the card value. Thus the card's value is compared regardless of color
    // by using mod(1000).
    @Override
    public int compare(Card o1, Card o2) {
        if ((o1.getID(false) % 1000) < (o2.getID(false) % 1000)) {
            return -1;
        }
        else if ((o1.getID(false) % 1000) > (o2.getID(false) % 1000)) {
            return 1;
        }
        // else the card values are equal, so the cards are then sorted by color.
        else {
            CardLightColorComparator comparator = new CardLightColorComparator();
            return comparator.compare(o1, o2);
        }
    }
}
