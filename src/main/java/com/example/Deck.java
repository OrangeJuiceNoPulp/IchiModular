package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Deck {
    
    private ObservableList<Card> pile; // The pile of cards from which cards are drawn
    private ObservableList<Card> burn; // The pile of cards where cards go once played 
                                       //or discarded so that they may be returned to the draw pile after shuffling
    private Random rand;
    private Game game;

    public boolean burnCard(Card card) {
        if ((card.getLightColor() == Card.LightColor.WILD) || 
        (card.getDarkColor() == Card.DarkColor.WILD) || 
        (card.getDarkColor() == Card.DarkColor.SEMIWILD)) {
            card.resetWild();
        }
        return burn.add(card);
    }

    public ObservableList<Card> getPile() {
        return this.pile;
    }

    public ObservableList<Card> getBurn() {
        return this.burn;
    }

    public Card drawCard() {
        if (pile.isEmpty()) {
            refillDeck();
        }
        if (pile.isEmpty()) {
            return Card.makeRandomCard(game);
        }
        return pile.remove(0);
    }

    private boolean refillDeck() {
        // TODO - IMPLEMENT THIS METHOD
        boolean status = true;
        if (burn.isEmpty()) {
            status = false;
        }
        else {
            ObservableList<Card> temp = pile;
            pile = burn;
            burn = temp;
            shuffle();
            //Swaps the draw pile with the burn pile
            //Shuffles the draw pile
        }
        return status;
    }

    public void shuffle() {
        Collections.shuffle(pile, rand);
    }

    public void addLightPairs(int numRed, int numBlue, int numGreen, int numYellow, int numOrange, int numPurple, Card.Value cardValue, ArrayList<LightPair> lightSides){
        for (int i = 0; i < numRed; i ++) {
            lightSides.add(new LightPair(Card.LightColor.RED, cardValue));
        }
        for (int i = 0; i < numBlue; i ++) {
            lightSides.add(new LightPair(Card.LightColor.BLUE, cardValue));
        }
        for (int i = 0; i < numGreen; i ++) {
            lightSides.add(new LightPair(Card.LightColor.GREEN, cardValue));
        }
        for (int i = 0; i < numYellow; i ++) {
            lightSides.add(new LightPair(Card.LightColor.YELLOW, cardValue));
        }
        for (int i = 0; i < numOrange; i ++) {
            lightSides.add(new LightPair(Card.LightColor.ORANGE, cardValue));
        }
        for (int i = 0; i < numPurple; i ++) {
            lightSides.add(new LightPair(Card.LightColor.PURPLE, cardValue));
        }
    }

    public void addLightPairs(int numRed, int numBlue, int numGreen, int numYellow, int numOrange, int numPurple, int numWild, Card.Value cardValue, ArrayList<LightPair> lightSides){
        for (int i = 0; i < numRed; i ++) {
            lightSides.add(new LightPair(Card.LightColor.RED, cardValue));
        }
        for (int i = 0; i < numBlue; i ++) {
            lightSides.add(new LightPair(Card.LightColor.BLUE, cardValue));
        }
        for (int i = 0; i < numGreen; i ++) {
            lightSides.add(new LightPair(Card.LightColor.GREEN, cardValue));
        }
        for (int i = 0; i < numYellow; i ++) {
            lightSides.add(new LightPair(Card.LightColor.YELLOW, cardValue));
        }
        for (int i = 0; i < numOrange; i ++) {
            lightSides.add(new LightPair(Card.LightColor.ORANGE, cardValue));
        }
        for (int i = 0; i < numPurple; i ++) {
            lightSides.add(new LightPair(Card.LightColor.PURPLE, cardValue));
        }
        for (int i = 0; i < numWild; i ++) {
            lightSides.add(new LightPair(Card.LightColor.WILD, cardValue));
        }
    }

    private void addDarkPairs(int numCyan, int numMagenta, int numLime, int numMaroon, Card.Value cardValue, ArrayList<DarkPair> darkSides) {
        for (int i = 0; i < numCyan; i ++) {
            darkSides.add(new DarkPair(Card.DarkColor.AQUA, cardValue));
        }
        for (int i = 0; i < numMagenta; i ++) {
            darkSides.add(new DarkPair(Card.DarkColor.MAGENTA, cardValue));
        }
        for (int i = 0; i < numLime; i ++) {
            darkSides.add(new DarkPair(Card.DarkColor.LIME, cardValue));
        }
        for (int i = 0; i < numMaroon; i ++) {
            darkSides.add(new DarkPair(Card.DarkColor.CURRANT, cardValue));
        }
    }

    private void addDarkPairs(int numCyan, int numMagenta, int numLime, int numMaroon, int numSemiWild, int numWild, Card.Value cardValue, ArrayList<DarkPair> darkSides) {
        for (int i = 0; i < numCyan; i ++) {
            darkSides.add(new DarkPair(Card.DarkColor.AQUA, cardValue));
        }
        for (int i = 0; i < numMagenta; i ++) {
            darkSides.add(new DarkPair(Card.DarkColor.MAGENTA, cardValue));
        }
        for (int i = 0; i < numLime; i ++) {
            darkSides.add(new DarkPair(Card.DarkColor.LIME, cardValue));
        }
        for (int i = 0; i < numMaroon; i ++) {
            darkSides.add(new DarkPair(Card.DarkColor.CURRANT, cardValue));
        }
        for (int i = 0; i < numWild; i ++) {
            darkSides.add(new DarkPair(Card.DarkColor.WILD, cardValue));
        }
        for (int i = 0; i < numSemiWild; i ++) {
            darkSides.add(new DarkPair(Card.DarkColor.SEMIWILD, cardValue));
        }
    }

    public Deck(Game game, Random rand) {
        burn = FXCollections.observableArrayList(new ArrayList<Card>(632));
        pile = FXCollections.observableArrayList(new ArrayList<Card>(632));

        this.rand = rand;

        this.game = game;

        ArrayList<LightPair> lightSides = new ArrayList<LightPair>();
        ArrayList<DarkPair> darkSides = new ArrayList<DarkPair>();

        // LIGHT SIDE GENERATION



        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.N_NINE, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.N_EIGHT, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.N_SEVEN, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.N_SIX, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.N_FIVE, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.N_FOUR, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.N_THREE, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.N_TWO, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.N_ONE, lightSides);
        addLightPairs(2, 2, 2, 2, 0, 0, Card.Value.ZERO, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.ONE, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.TWO, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.THREE, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.FOUR, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.FIVE, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.SIX, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.SEVEN, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.EIGHT, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.NINE, lightSides);

        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.FLIP, lightSides);
        addLightPairs(4, 4, 4, 4, 0, 0, Card.Value.REVERSE, lightSides);




        // FOR TESTING ROTATE / SWAP


        // addLightPairs(4, 0, 0, 0, 0, 0, Card.Value.ONE, lightSides);
        // addLightPairs(1, 1, 1, 1, 0, 0, Card.Value.ROTATE, lightSides);
        // addLightPairs(0, 3, 0, 0, 0, 0, Card.Value.ONE, lightSides);
        // addLightPairs(1, 1, 1, 1, 0, 0, Card.Value.ROTATE, lightSides);
        // addLightPairs(0, 0, 3, 0, 0, 0, Card.Value.ONE, lightSides);
        // addLightPairs(1, 1, 1, 1, 0, 0, Card.Value.ROTATE, lightSides);
        // addLightPairs(0, 0, 0, 3, 0, 0, Card.Value.ONE, lightSides);
        // addLightPairs(1, 1, 1, 1, 0, 0, Card.Value.ROTATE, lightSides);
        // addLightPairs(32, 32, 32, 32, 0, 0, Card.Value.ONE, lightSides);


        // FOR TESTING DISCARD ALL

        // addLightPairs(4, 0, 0, 0, 0, 0, Card.Value.ONE, lightSides);
        // addLightPairs(1, 1, 1, 1, 0, 0, Card.Value.DISCARD_ALL, lightSides);
        // addLightPairs(0, 3, 0, 0, 0, 0, Card.Value.ONE, lightSides);
        // addLightPairs(1, 1, 1, 1, 0, 0, Card.Value.DISCARD_ALL, lightSides);
        // addLightPairs(0, 0, 3, 0, 0, 0, Card.Value.ONE, lightSides);
        // addLightPairs(1, 1, 1, 1, 0, 0, Card.Value.DISCARD_ALL, lightSides);
        // addLightPairs(0, 0, 0, 3, 0, 0, Card.Value.ONE, lightSides);
        // addLightPairs(1, 1, 1, 1, 0, 0, Card.Value.DISCARD_ALL, lightSides);
        // addLightPairs(32, 32, 32, 32, 0, 0, Card.Value.ONE, lightSides);


        // OTHER TESTING


        // addLightPairs(1, 0, 0, 0, 0, 0, Card.Value.DRAW_5, lightSides);
        // addLightPairs(0, 0, 0, 0, 0, 0, 7, Card.Value.WILD_DRAW_4, lightSides);
        // addLightPairs(0, 7, 0, 0, 0, 0, Card.Value.DRAW_5, lightSides);
        // addLightPairs(0, 0, 7, 0, 0, 0, Card.Value.DRAW_5, lightSides);
        // addLightPairs(0, 0, 0, 7, 0, 0, Card.Value.ONE, lightSides);
        // addLightPairs(32, 32, 32, 32, 0, 0, Card.Value.ONE, lightSides);



        // //addLightPairs(0, 0, 0, 0, 0, 0, 64, Card.Value.WILD_REGULAR, lightSides);
        // addLightPairs(1, 0, 0, 0, 0, 0, Card.Value.DRAW_5, lightSides);
        // addLightPairs(0, 0, 0, 0, 0, 0, 7, Card.Value.WILD_DRAW_4, lightSides);
        // //addLightPairs(1, 1, 1, 1, 0, 0, Card.Value.SKIP, lightSides);
        // addLightPairs(0, 7, 0, 0, 0, 0, Card.Value.DRAW_5, lightSides);
        // //addLightPairs(1, 1, 1, 1, 0, 0, Card.Value.SKIP, lightSides);
        // addLightPairs(0, 0, 7, 0, 0, 0, Card.Value.DRAW_5, lightSides);
        // //addLightPairs(1, 1, 1, 1, 0, 0, Card.Value.SKIP, lightSides);
        // addLightPairs(0, 0, 0, 7, 0, 0, Card.Value.ONE, lightSides);
        // //addLightPairs(1, 1, 1, 1, 0, 0, Card.Value.SKIP, lightSides);
        // addLightPairs(32, 32, 32, 32, 0, 0, Card.Value.ONE, lightSides);
        // //addLightPairs(4, 4, 0, 0, 0, 0, Card.Value.TWO, lightSides);
        // //addLightPairs(64, 64, 0, 0, 0, 0, Card.Value.TOWER_BUILD, lightSides);
        // //addLightPairs(64, 64, 0, 0, 0, 0, Card.Value.TOWER_DESTROY, lightSides);


        // DARK SIDE GENERATION

        

        addDarkPairs(4, 4, 4, 4, Card.Value.N_NINE, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.N_EIGHT, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.N_SEVEN, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.N_SIX, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.N_FIVE, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.N_FOUR, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.N_THREE, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.N_TWO, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.N_ONE, darkSides);
        addDarkPairs(2, 2, 2, 2, Card.Value.ZERO, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.ONE, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.TWO, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.THREE, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.FOUR, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.FIVE, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.SIX, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.SEVEN, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.EIGHT, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.NINE, darkSides);

        addDarkPairs(4, 4, 4, 4, Card.Value.FLIP, darkSides);
        addDarkPairs(4, 4, 4, 4, Card.Value.REVERSE, darkSides);


        // FOR TESTING ROTATE / SWAP

        // addDarkPairs(4, 0, 0, 0, Card.Value.ONE, darkSides);
        // addDarkPairs(1, 1, 1, 1, Card.Value.ROTATE, darkSides);
        // addDarkPairs(0, 3, 0, 0, Card.Value.ONE, darkSides);
        // addDarkPairs(1, 1, 1, 1, Card.Value.ROTATE, darkSides);
        // addDarkPairs(0, 0, 3, 0, Card.Value.ONE, darkSides);
        // addDarkPairs(1, 1, 1, 1, Card.Value.ROTATE, darkSides);
        // addDarkPairs(0, 0, 0, 3, Card.Value.ONE, darkSides);
        // addDarkPairs(1, 1, 1, 1, Card.Value.ROTATE, darkSides);
        // addDarkPairs(32, 32, 32, 32, Card.Value.ONE, darkSides);


        // FOR TESTING DISCARD ALL

        // addDarkPairs(4, 0, 0, 0, Card.Value.ONE, darkSides);
        // addDarkPairs(1, 1, 1, 1, Card.Value.DISCARD_ALL, darkSides);
        // addDarkPairs(0, 3, 0, 0, Card.Value.ONE, darkSides);
        // addDarkPairs(1, 1, 1, 1, Card.Value.DISCARD_ALL, darkSides);
        // addDarkPairs(0, 0, 3, 0, Card.Value.ONE, darkSides);
        // addDarkPairs(1, 1, 1, 1, Card.Value.DISCARD_ALL, darkSides);
        // addDarkPairs(0, 0, 0, 3, Card.Value.ONE, darkSides);
        // addDarkPairs(1, 1, 1, 1, Card.Value.DISCARD_ALL, darkSides);
        // addDarkPairs(32, 32, 32, 32, Card.Value.ONE, darkSides);



        // OTHER TESTING
        // addDarkPairs(1, 0, 0, 0, Card.Value.SKIP_ALL, darkSides);
        // addDarkPairs(0, 0, 0, 0, 0, 3, Card.Value.WILD_REGULAR, darkSides);
        // addDarkPairs(0, 0, 0, 0, 4, 0, Card.Value.ONE, darkSides);
        // addDarkPairs(0, 7, 0, 0, Card.Value.SKIP_ALL, darkSides);
        // addDarkPairs(0, 0, 7, 0, Card.Value.SKIP_ALL, darkSides);
        // addDarkPairs(0, 0, 0, 7, Card.Value.SKIP_ALL, darkSides);
        // addDarkPairs(32, 32, 32, 32, Card.Value.SKIP_ALL, darkSides);



        // // addDarkPairs(0, 0, 0, 0, 0, 64, Card.Value.WILD_REGULAR, darkSides);
        // addDarkPairs(1, 0, 0, 0, Card.Value.SKIP_ALL, darkSides);
        // addDarkPairs(0, 0, 0, 0, 0, 3, Card.Value.WILD_REGULAR, darkSides);
        // addDarkPairs(0, 0, 0, 0, 4, 0, Card.Value.ONE, darkSides);
        // //addDarkPairs(1, 1, 1, 1, Card.Value.SKIP, darkSides);
        // addDarkPairs(0, 7, 0, 0, Card.Value.SKIP_ALL, darkSides);
        // //addDarkPairs(1, 1, 1, 1, Card.Value.SKIP, darkSides);
        // addDarkPairs(0, 0, 7, 0, Card.Value.SKIP_ALL, darkSides);
        // //addDarkPairs(1, 1, 1, 1, Card.Value.SKIP, darkSides);
        // addDarkPairs(0, 0, 0, 7, Card.Value.SKIP_ALL, darkSides);
        // //addDarkPairs(1, 1, 1, 1, Card.Value.SKIP, darkSides);
        // addDarkPairs(32, 32, 32, 32, Card.Value.SKIP_ALL, darkSides);
        // //addDarkPairs(1, 1, 0, 0, Card.Value.TWO, darkSides);
        // //addDarkPairs(64, 64, 0, 0, Card.Value.TOWER_BUILD, darkSides);
        // //addDarkPairs(64, 64, 0, 0, Card.Value.TOWER_DESTROY, darkSides);

        // CARD GENERATION

        Collections.shuffle(darkSides, rand);
        Collections.shuffle(lightSides, rand);

        for (int i = 0; i < Math.min(lightSides.size(), darkSides.size()); i ++) {
            LightPair light = lightSides.get(i);
            DarkPair dark = darkSides.get(i);
            pile.add(new Card(light.color, light.value, dark.color, dark.value, game));
        }

    }

    private class LightPair {
        private Card.LightColor color;
        private Card.Value value;
        public LightPair(Card.LightColor color, Card.Value value) {
            this.color = color;
            this.value = value;
        }
    }
    private class DarkPair {
        private Card.DarkColor color;
        private Card.Value value;
        public DarkPair(Card.DarkColor color, Card.Value value) {
            this.color = color;
            this.value = value;
        }
    }

}
