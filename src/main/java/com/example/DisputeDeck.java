package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class DisputeDeck {
    private ArrayList<Card> disputeDeck;
    private Random rand;
    private Game game;

    public ArrayList<Card> drawDisputeCards(int numOfDisputers) {
        ArrayList<Card> drawnCards = new ArrayList<Card>();

        // Puts the specified number of dispute cards into an ArrayList
        for (int i = 0; i < numOfDisputers; i++) {
            drawnCards.add(disputeDeck.remove(0));
        }
        drawNewDeck(); // Resets the dispute deck for the next use
        return drawnCards;
    }

    private void drawNewDeck() {
        disputeDeck.clear();

        ArrayList<Card.LightColor> lightColorList = new ArrayList<Card.LightColor>();
        ArrayList<Card.DarkColor> darkColorList = new ArrayList<Card.DarkColor>();
        ArrayList<Card.Value> numberValueList = new ArrayList<Card.Value>();

        lightColorList.add(Card.LightColor.RED);
        lightColorList.add(Card.LightColor.ORANGE);
        lightColorList.add(Card.LightColor.YELLOW);
        lightColorList.add(Card.LightColor.GREEN);
        lightColorList.add(Card.LightColor.BLUE);
        lightColorList.add(Card.LightColor.PURPLE);

        darkColorList.add(Card.DarkColor.AQUA);
        darkColorList.add(Card.DarkColor.LIME);
        darkColorList.add(Card.DarkColor.MAGENTA);
        darkColorList.add(Card.DarkColor.CURRANT);

        numberValueList.add(Card.Value.N_NINE);
        numberValueList.add(Card.Value.N_EIGHT);
        numberValueList.add(Card.Value.N_SEVEN);
        numberValueList.add(Card.Value.N_SIX);
        numberValueList.add(Card.Value.N_FIVE);
        numberValueList.add(Card.Value.N_FOUR);
        numberValueList.add(Card.Value.N_THREE);
        numberValueList.add(Card.Value.N_TWO);
        numberValueList.add(Card.Value.N_ONE);
        numberValueList.add(Card.Value.ZERO);
        numberValueList.add(Card.Value.ONE);
        numberValueList.add(Card.Value.TWO);
        numberValueList.add(Card.Value.THREE);
        numberValueList.add(Card.Value.FOUR);
        numberValueList.add(Card.Value.FIVE);
        numberValueList.add(Card.Value.SIX);
        numberValueList.add(Card.Value.SEVEN);
        numberValueList.add(Card.Value.EIGHT);
        numberValueList.add(Card.Value.NINE);

        for (Card.Value val : numberValueList) {
            disputeDeck.add(new Card(lightColorList.get(rand.nextInt(lightColorList.size())), val, darkColorList.get(rand.nextInt(darkColorList.size())), val, game));
        }

        Collections.shuffle(disputeDeck, rand);
    }
    
    public DisputeDeck(Random rand, Game game) {
        this.rand = rand;
        this.game = game;
        disputeDeck = new ArrayList<Card>();

        drawNewDeck();
    }
}
