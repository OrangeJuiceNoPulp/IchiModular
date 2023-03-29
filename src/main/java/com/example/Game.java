package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.example.Deck.DeckType;
import com.example.Tower.TowerPosition;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Game {
    private static final int POINT_MULTIPLIER_GAME = 1;
    private static final int DARK_MODE_MULTIPLIER = 2;
    private static final int LIGHT_MODE_MULTIPLIER = 1;
    private static final int startingCardAmount = 7;
    private static final int sizeForBigHandPenalty = 100;
    private Deck deck;
    private DeckType deckType;
    private int numOfPlayers;
    private int numOfBuiltTowers;

    private TowerPosition selectedTowerPosition;

    private DisputeDeck disputeDeck;
    private Stack stack;

    private BooleanProperty isDarkMode;
    private BooleanProperty userViewingDarkMode;

    private boolean waitingForUserToTakeTurn;
    private boolean gameDirectionClockwise;

    private Tower castle; //tower0
    private Tower northTower; //tower1
    private Tower southTower; //tower2
    private Tower eastTower; //tower3
    private Tower westTower; //tower4

    private Player player1; // player 1 is the user
    private Player player2;
    private Player player3;
    private Player player4;

    private ArrayList<Player> playerList;

    private Random rand;

    private Player curPlayer; // points to the current player

    private int roundNumber; // the current round number
    private int numOfRounds; // the total number of rounds to be played

    private long lastRoundVictoryBonus = 0;
    private Player lastRoundWinner = null;

    private GameImageLoader imageLoader;
    private GamePane gamePane;

    public void setGameNull() {
        this.gamePane.setGameNull();
        this.rand = null;
        this.curPlayer = null;
        this.imageLoader.clearImages();
        this.imageLoader = null;
        this.deck = null;
        this.deckType = null;
        this.castle = null;
        this.northTower = null;
        this.southTower = null;
        this.eastTower = null;
        this.westTower = null;
        this.player1 = null;
        this.player2 = null;
        this.player3 = null;
        this.player4 = null;
        this.gamePane = null;
        this.playerList = null;
        this.isDarkMode = null;
        this.userViewingDarkMode = null;
        this.stack = null;
        this.disputeDeck = null;
        this.selectedTowerPosition = null;
    }

    public GameImageLoader getImageLoader() {
        return imageLoader;
    }

    public GamePane getGamePane() {
        return this.gamePane;
    }

    public DeckType getDeckType() {
        return this.deckType;
    }

    public ArrayList<Text> getCurrentRankings() {
        ArrayList<Player> tempPlayerArray = new ArrayList<Player>(5);
        for (Player player: playerList) {
            tempPlayerArray.add(player);
        }
        tempPlayerArray.sort(new PlayerScoreComparator());

        ArrayList<Text> returnTextArray = new ArrayList<Text>(5);

        for (Player player: tempPlayerArray) {
            Text txtPlayerScore = new Text("\t" + player.getPlayerName() +": " + player.getOverallScore() + " points");
            txtPlayerScore.setFill(Color.WHITESMOKE);
            txtPlayerScore.setFont(new Font("Perpetua Bold Italic", 25));
            txtPlayerScore.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
            returnTextArray.add(txtPlayerScore);
        }

        return returnTextArray;
    }

    public long getLastRoundVictoryBonus() {
        return lastRoundVictoryBonus;
    }
    public Player getLastRoundWinner() {
        return lastRoundWinner;
    }

    public int getNumOfPlayers() {
        return this.numOfPlayers;
    }

    public Player getUser() {
        return player1;
    }

    public Random getRand() {
        return this.rand;
    }

    public Stack getStack() {
        return this.stack;
    }

    // Randomly redistributes the player's hands. A player's hand may end up being
    // the same after the redistribution.
    private void redistributeHands() {
        ArrayList<ObservableList<Card>> playerHandList = new ArrayList<ObservableList<Card>>(5);
        // Collects the player's hands
        for (Player player : getPlayers()) {
            playerHandList.add(player.getHand());
        }

        // Shuffles the list of hands
        Collections.shuffle(playerHandList, rand);

        // Distributes the players hands
        for (Player player : getPlayers()) {
            player.setHand(playerHandList.remove(0));
        }
    }

    // Determines if the card is playable at any of the currently active towers.
    private boolean playableAtAnyTower(Card card) {
        for (Tower tower : getTowers()) {
            if (card.isPlayable(tower.getDisplayedCard(), isDarkMode.get())) {
                return true;
            }
        }
        return false;
    }

    public Card drawToMatchAnyTower(Player player) {
        //Draws an initial card
        Card card = deck.drawCard();
        player.drawCard(card);

        //Until a playable card is drawn, cards keep being drawn.
        //Or if the player's hand becomes too large, the drawing is terminated.
        while (!playableAtAnyTower(card) && (player.getHand().size() < sizeForBigHandPenalty)) {
            card = deck.drawCard();
            player.drawCard(card);
        }

        //Once the drawing is terminated, it is checked if the drawing was terminated because the hand was too large.
        if (player.getHand().size() >= sizeForBigHandPenalty) {
            checkForBigHandPenalty();
            return null; //null is returned because the player accepts the bigHandPenalty and cannot play.
        }
        //Otherwise the drawing was terminated because the drawn card is playable, thus the drawn card is returned.
        else {
            return card;
        }
        
    }

    private Card drawToMatchSpecificTower(Player targetPlayer, Tower tower) {
        //Draws an initial card
        Card card = deck.drawCard();
        targetPlayer.drawCard(card);

        //Until a playable card is drawn, cards keep being drawn.
        //Or if the player's hand becomes too large, the drawing is terminated.
        while (!(card.isPlayable(tower.getDisplayedCard(), isDarkMode.get())) && (targetPlayer.getHand().size() < sizeForBigHandPenalty)) {
            card = deck.drawCard();
            targetPlayer.drawCard(card);
        }

        //Once the drawing is terminated, it is checked if the drawing was terminated because the hand was too large.
        if (targetPlayer.getHand().size() >= sizeForBigHandPenalty) {
            checkForBigHandPenalty();
            return null; //null is returned because the player accepts the bigHandPenalty and cannot play.
        }
        //Otherwise the drawing was terminated because the drawn card is playable, thus the playable drawn card is returned.
        else {
            return card;
        }
        
    }

    private void towerDestroy(Player currentPlayer, Tower currentTower) {
        // TODO implement me

        // if the tower is the castle
        if (currentTower.getIsCastle()) {

            // if the castle is owned
            if (currentTower.getIsOwned()) {

                // if the owner of the castle is the current player
                if (currentTower.getOwner().getPlayerNum() == currentPlayer.getPlayerNum()) {

                    // the player chooses a tower which they do not own to destroy
                    Tower chosenTower = selectTowerForDispute(currentPlayer);

                    if (chosenTower != null) {
                        chosenTower.destroyTower();
                    } else {
                        currentPlayer.addPoints(100);
                        // If there are no towers available for the player to choose from
                        // (because they own them all), they will receive 100 points instead.
                    }
                }
                // else the owner of the castle is different from the current player,
                // so a dispute is started for ownership
                else {
                    ArrayList<Player> disputingPlayers = new ArrayList<Player>();
                    disputingPlayers.add(currentPlayer);
                    disputingPlayers.add(currentTower.getOwner());
                    Player disputeWinner = conductDispute(disputingPlayers);
                    currentTower.setOwner(disputeWinner);
                }

            }
            // else the castle is unowned, so a dispute begins among all the players for
            // castle ownership
            else {
                Player disputeWinner = conductDispute(playerList);
                currentTower.setOwner(disputeWinner);
            }

        }
        // else the tower is not the castle
        else {
            currentTower.destroyTower();
        }

    }

    private void towerBuild(Player currentPlayer, Tower currentTower) {
        // If the tower is not owned (will implicitly be the castle)
        if (!(currentTower.getIsOwned())) {
            currentTower.setOwner(currentPlayer); // set the tower owner to the current player
        }
        // If the tower is the castle and it is owned
        else if (currentTower.getIsCastle()) {
            // TODO TEST ME
            if (numOfBuiltTowers < numOfPlayers) {
                try {
                    getNextEmptyTower().startTower(currentPlayer);
                } catch (Exception ex) {
                    Tower chosenTower = selectTowerForDispute(currentPlayer);

                    if (chosenTower != null) {
                        ArrayList<Player> disputingPlayers = new ArrayList<Player>();
                        disputingPlayers.add(currentPlayer);
                        disputingPlayers.add(chosenTower.getOwner());
                        Player disputeWinner = conductDispute(disputingPlayers);
                        chosenTower.setOwner(disputeWinner);
                    } else {
                        currentPlayer.addPoints(100);
                        // If there are no towers available for the player to choose from
                        // (because they own them all), they will receive 100 points instead.
                    }

                }
            } else {
                Tower chosenTower = selectTowerForDispute(currentPlayer);

                if (chosenTower != null) {
                    ArrayList<Player> disputingPlayers = new ArrayList<Player>();
                    disputingPlayers.add(currentPlayer);
                    disputingPlayers.add(chosenTower.getOwner());
                    Player disputeWinner = conductDispute(disputingPlayers);
                    chosenTower.setOwner(disputeWinner);
                } else {
                    currentPlayer.addPoints(100);
                    // If there are no towers available for the player to choose from
                    // (because they own them all), they will receive 100 points instead.
                }
            }

        }
        // Else the tower is not the castle (and must be owned implicitly)
        else {
            // TODO start dispute between tower owner and player if player is not the tower
            // (DONE, remove this comment when no longer needed)
            // owner
            // else let the player build a tower, or if towers are full, get 100 points

            // If the player is not the owner of the tower, a dispute for tower ownership is
            // started.
            if (currentPlayer.getPlayerNum() != currentTower.getOwner().getPlayerNum()) {
                ArrayList<Player> disputingPlayers = new ArrayList<Player>();
                disputingPlayers.add(currentPlayer);
                disputingPlayers.add(currentTower.getOwner());
                Player disputeWinner = conductDispute(disputingPlayers);
                currentTower.setOwner(disputeWinner);
            }
            // Else the player is the owner of the tower
            // A tower will be built if it is able to be, otherwise a dispute can be
            // started,
            // or points are obtained if all the towers are full.
            else {
                if (numOfBuiltTowers < numOfPlayers) {
                    try {
                        getNextEmptyTower().startTower(currentPlayer);
                    } catch (Exception ex) {
                        Tower chosenTower = selectTowerForDispute(currentPlayer);

                        if (chosenTower != null) {
                            ArrayList<Player> disputingPlayers = new ArrayList<Player>();
                            disputingPlayers.add(currentPlayer);
                            disputingPlayers.add(chosenTower.getOwner());
                            Player disputeWinner = conductDispute(disputingPlayers);
                            chosenTower.setOwner(disputeWinner);
                        } else {
                            currentPlayer.addPoints(100);
                            // If there are no towers available for the player to choose from
                            // (because they own them all), they will receive 100 points instead.
                        }

                    }
                } else {
                    Tower chosenTower = selectTowerForDispute(currentPlayer);

                    if (chosenTower != null) {
                        ArrayList<Player> disputingPlayers = new ArrayList<Player>();
                        disputingPlayers.add(currentPlayer);
                        disputingPlayers.add(chosenTower.getOwner());
                        Player disputeWinner = conductDispute(disputingPlayers);
                        chosenTower.setOwner(disputeWinner);
                    } else {
                        currentPlayer.addPoints(100);
                        // If there are no towers available for the player to choose from
                        // (because they own them all), they will receive 100 points instead.
                    }
                }
            }
        }
    }

    private Player conductDispute(ArrayList<Player> players) {

        ArrayList<Card> drawnCards = disputeDeck.drawDisputeCards(players.size());
        // TODO Show animation for the dispute

        CardLightValueComparator comparator = new CardLightValueComparator();

        Card highestCard = drawnCards.get(0);
        int winningIndex = 0;
        int i = 0;
        for (Card card : drawnCards) {
            if (comparator.compare(card, highestCard) > 0) {
                highestCard = card;
                winningIndex = i;
            }
            System.out.println("Player " + players.get(i).getPlayerNum() + ": " + card.toString()); // TODO remove this
                                                                                                    // after debugging
            i++;
        }
        return players.get(winningIndex);
    }

    private Card.DarkColor selectWildDarkColor(Player player) {
        int aqua = 0, lime = 0, magenta = 0, currant = 0;

        // count the total number of each color card in the bot's hand
        for (Card card : player.getHand()) {
            switch (card.getDarkColor()) {
                case AQUA:
                    aqua++;
                    break;
                case LIME:
                    lime++;
                    break;
                case MAGENTA:
                    magenta++;
                    break;
                case CURRANT:
                    currant++;
                    break;
                default:
                    break;
            }
        }

        // determine the maximum value for the amount of a color of card (in case the
        // two most plentiful colors are present in equal numbers)
        int max = 0;
        if (aqua > max) {
            max = aqua;
        }
        if (lime > max) {
            max = lime;
        }
        if (magenta > max) {
            max = magenta;
        }
        if (currant > max) {
            max = currant;
        }

        // Creates an array of choices of the most plentiful colors
        ArrayList<Card.DarkColor> colorChoices = new ArrayList<Card.DarkColor>();
        if (aqua == max) {
            colorChoices.add(Card.DarkColor.WILD_AQUA);
        }
        if (lime == max) {
            colorChoices.add(Card.DarkColor.WILD_LIME);
        }
        if (magenta == max) {
            colorChoices.add(Card.DarkColor.WILD_MAGENTA);
        }
        if (currant == max) {
            colorChoices.add(Card.DarkColor.WILD_CURRANT);
        }

        // If there is a color to choose from, choose randomly from the preferable color
        // selection
        if (colorChoices.size() > 0) {
            int choice = rand.nextInt(colorChoices.size());
            return colorChoices.get(choice);
        }
        // else something goes wrong, so choose a completely random color
        else {
            int choice = rand.nextInt(6);

            if (choice == 0) {
                return Card.DarkColor.WILD_AQUA;
            } else if (choice == 1) {
                return Card.DarkColor.WILD_LIME;
            } else if (choice == 2) {
                return Card.DarkColor.WILD_MAGENTA;
            } else {
                return Card.DarkColor.WILD_CURRANT;
            }
        }
    }

    private Card.LightColor selectWildLightColor(Player player) {
        int red = 0, blue = 0, green = 0, yellow = 0, purple = 0, orange = 0;

        // count the total number of each color card in the bot's hand
        for (Card card : player.getHand()) {
            switch (card.getLightColor()) {
                case BLUE:
                    blue++;
                    break;
                case GREEN:
                    green++;
                    break;
                case ORANGE:
                    orange++;
                    break;
                case PURPLE:
                    purple++;
                    break;
                case RED:
                    red++;
                    break;
                case YELLOW:
                    yellow++;
                    break;
                default:
                    break;
            }
        }

        // determine the maximum value for the amount of a color of card (in case the
        // two most plentiful colors are present in equal numbers)
        int max = 0;
        if (red > max) {
            max = red;
        }
        if (blue > max) {
            max = blue;
        }
        if (green > max) {
            max = green;
        }
        if (yellow > max) {
            max = yellow;
        }
        if (purple > max) {
            max = purple;
        }
        if (orange > max) {
            max = orange;
        }

        // Creates an array of choices of the most plentiful colors
        ArrayList<Card.LightColor> colorChoices = new ArrayList<Card.LightColor>();
        if (red == max) {
            colorChoices.add(Card.LightColor.WILD_RED);
        }
        if (blue == max) {
            colorChoices.add(Card.LightColor.WILD_BLUE);
        }
        if (green == max) {
            colorChoices.add(Card.LightColor.WILD_GREEN);
        }
        if (yellow == max) {
            colorChoices.add(Card.LightColor.WILD_YELLOW);
        }
        if (purple == max) {
            colorChoices.add(Card.LightColor.WILD_PURPLE);
        }
        if (orange == max) {
            colorChoices.add(Card.LightColor.WILD_ORANGE);
        }

        // If there is a color to choose from, choose randomly from the preferable color
        // selection
        if (colorChoices.size() > 0) {
            int choice = rand.nextInt(colorChoices.size());
            return colorChoices.get(choice);
        }
        // else something goes wrong, so choose a completely random color
        else {
            int choice = rand.nextInt(6);

            if (choice == 0) {
                return Card.LightColor.WILD_RED;
            } else if (choice == 1) {
                return Card.LightColor.WILD_BLUE;
            } else if (choice == 2) {
                return Card.LightColor.WILD_GREEN;
            } else if (choice == 3) {
                return Card.LightColor.WILD_YELLOW;
            } else if (choice == 4) {
                return Card.LightColor.WILD_PURPLE;
            } else {
                return Card.LightColor.WILD_ORANGE;
            }
        }
    }

    private Tower selectTowerForDispute(Player player) {
        ArrayList<Tower> potentialTowerChoices = new ArrayList<Tower>();
        for (Tower tower : getTowers()) {
            if (tower.getOwner() != null) {
                if ((tower.getOwner().getPlayerNum() != player.getPlayerNum())
                        && (tower.getPosition() != Tower.TowerPosition.CENTER)) {
                    potentialTowerChoices.add(tower);
                }
            }
        }

        // If the player is a bot
        if (player.getIsBot()) {
            if (potentialTowerChoices.size() <= 0) {
                return null;
            } else {
                int towerChoice = rand.nextInt(potentialTowerChoices.size());
                return potentialTowerChoices.get(towerChoice);
            }
        }
        // Else the player is the user
        else {
            if (potentialTowerChoices.size() <= 0) {
                return null;
            } else {
                TowerChooserStage towerChooserStage = new TowerChooserStage(potentialTowerChoices, this);

                towerChooserStage.showAndWait();

                Tower chosenTower = towerChooserStage.getChoice();

                if (chosenTower != null) {
                    return chosenTower; // If a tower is chosen, then the chosen one is returned
                }
                // Else a random tower is returned
                else {
                    int towerChoice = rand.nextInt(potentialTowerChoices.size());
                    return potentialTowerChoices.get(towerChoice);
                }

            }
        }
    }

    private Tower getNextEmptyTower() throws Exception {
        Tower emptyTower = null;
        for (Tower tower : getTowers()) {
            if (tower.getIsEmpty()) {
                emptyTower = tower;
                break;
            }
        }
        if (emptyTower != null) {
            return emptyTower;
        } else {
            throw new Exception("There are no empty towers");
        }
    }

    public Tower getSelectedTower() {
        Tower selectedTower;
        switch (selectedTowerPosition) {
            case CENTER:
                selectedTower = castle;
                break;
            case EAST:
                selectedTower = eastTower;
                break;
            case NORTH:
                selectedTower = northTower;
                break;
            case SOUTH:
                selectedTower = southTower;
                break;
            case WEST:
                selectedTower = westTower;
                break;
            default:
                selectedTower = castle;
                break;

        }
        return selectedTower;
    }

    public TowerPosition getSelectedTowerPosition() {
        return selectedTowerPosition;
    }

    public void setSelectedTowerPosition(TowerPosition position) {
        selectedTowerPosition = position;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getNumberOfRounds() {
        return numOfRounds;
    }

    public Card getSelectedCard() {
        return gamePane.getSelectedCard();
    }

    public void refreshGamePane() {
        gamePane.refreshGameTable();
    }

    public Tower getCastle() {
        return this.castle;
    }

    public ArrayList<Tower> getTowers() {
        ArrayList<Tower> towers = new ArrayList<Tower>(5);
        towers.add(this.castle);
        towers.add(this.northTower);
        towers.add(this.southTower);
        towers.add(this.eastTower);
        towers.add(this.westTower);
        return towers;
    }

    public void setViewingDarkMode(boolean viewing) {
        userViewingDarkMode.setValue(viewing);
    }

    public boolean getViewingDarkMode() {
        return userViewingDarkMode.get();
    }

    public BooleanProperty getViewingDarkModeProperty() {
        return userViewingDarkMode;
    }

    public void setCurrentPlayer(Player nextPlayer) {
        this.curPlayer = nextPlayer;
    }

    public boolean isClockwise() {
        return this.gameDirectionClockwise;
    }

    public ArrayList<Player> getPlayers() {
        return this.playerList;
    }

    public boolean getIsDarkMode() {
        return this.isDarkMode.get();
    }

    public boolean getWaitingForUserToTakeTurn() {
        return this.waitingForUserToTakeTurn;
    }

    public void setWaitingForUserToTakeTurn(boolean waitingStatus) {
        this.waitingForUserToTakeTurn = waitingStatus;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public Player getPlayer(int playerNumber) {
        // TODO finish implementing this method // Done?
        if (playerNumber == 1) {
            return player1;
        }
        else if (playerNumber == 2) {
            return player2;
        }
        else if (playerNumber == 3) {
            return player3;
        }
        else if (playerNumber == 4) {
            return player4;
        }
        else {
            return null;
        }
    }

    public Player getCurrentPlayer() {
        return this.curPlayer;
    }

    public boolean playCard(Player player, Tower tower, Card card) {
        if (tower.getIsEmpty()) {
            return false;
        }
        if (card.isPlayable(tower.getDisplayedCard(), isDarkMode.get())) {
            tower.addCard(card);
            player.discardCard(card);
            performCardAction(player, tower);
            return true;
        } else {
            return false;
        }
    }

    private void swapHands(Player player) {
        Player target = player.selectTargetPlayer();
        ObservableList<Card> temp = player.getHand();
        player.setHand(target.getHand());
        target.setHand(temp);
    }

    private void rotateHands(Player player) {
        Player currentPlayer = player;
        for (int i = (getPlayers().size() - 1); i > 0; i--) {
            Player target = currentPlayer.getPrevPlayer();
            ObservableList<Card> temp = currentPlayer.getHand();
            currentPlayer.setHand(target.getHand());
            target.setHand(temp);
            currentPlayer = target;
        }
    }

    private void discardAllLightColor(Player player, Card.LightColor color) {
        ArrayList<Card> cardsToBeDiscarded = new ArrayList<Card>();
        for (Card card : player.getHand()) {
            if (card.getLightColor() == color) {
                cardsToBeDiscarded.add(card);
            }
        }
        for (Card card : cardsToBeDiscarded) {
            deck.burnCard(card);
            player.discardCard(card);
        }
    }

    private void discardAllDarkColor(Player player, Card.DarkColor color) {
        ArrayList<Card> cardsToBeDiscarded = new ArrayList<Card>();
        for (Card card : player.getHand()) {
            if (card.getDarkColor() == color) {
                cardsToBeDiscarded.add(card);
            }
        }
        for (Card card : cardsToBeDiscarded) {
            deck.burnCard(card);
            player.discardCard(card);
        }
    }

    private void performJackpot(Player player) {
        // TODO display jackpot animation

        int randomNumber = rand.nextInt(16);

        if (randomNumber == 0) {
            // If the player has two or more cards in their hand, they will discard until
            // they have 2 left. Those 2 cards will be random chosen.
            if (player.getHand().size() >= 2) {
                ArrayList<Card> cardsToBeDiscarded = new ArrayList<Card>();
                // Adds all the cards in the player's hand to a list of cards to be discarded.
                for (Card card : player.getHand()) {
                    cardsToBeDiscarded.add(card);
                }

                // Removes two random cards from the list of cards to be discarded, so the
                // player will have 2 cards left.
                cardsToBeDiscarded.remove(rand.nextInt(cardsToBeDiscarded.size()));
                cardsToBeDiscarded.remove(rand.nextInt(cardsToBeDiscarded.size()));

                // Removes all the other cards from the player's hand.
                for (Card card : cardsToBeDiscarded) {
                    deck.burnCard(card);
                    player.discardCard(card);
                }
            }
            // Else the player does not discard any cards and is instead compensated with
            // points.
            else {
                player.addPoints(500);
            }
        } else if ((randomNumber == 2) || (randomNumber == 6) || (randomNumber == 7) || (randomNumber == 11)) {
            player.addPoints(45);
        } else if ((randomNumber == 4)) {
            player.addPoints(150);
        } else if ((randomNumber == 9)) {
            player.addPoints(300);
        } else if ((randomNumber == 13)) {
            player.addPoints(500);
        } else {
            player.addPoints(15);
        }
    }

    private void reverse() {
        if (gameDirectionClockwise) {
            gameDirectionClockwise = false;
        } else {
            gameDirectionClockwise = true;
        }
    }

    private void skipAll(Player currentPlayer) {
        // Iterate through the list of players in the game.
        // If the player is not the player who played this card, they are skipped.
        for (Player player : getPlayers()) {
            if (player.getPlayerNum() != currentPlayer.getPlayerNum()) {
                player.setIsSkipped(true);
            }
        }
    }

    private void skipNext(Player player) {
        player.getNextPlayer().setIsSkipped(true);
    }

    private void targetedDraw(int numCards, Player currentPlayer) {
        Player target = currentPlayer.selectTargetPlayer(); // TODO finish me
        // TODO add draw card animation

        System.out.println("Before: Target has " + target.getHandSize() + " cards.");
        // For each iteration, draw a card
        for (int i = 0; i < numCards; i++) {
            target.drawCard(deck.drawCard());
        }
        System.out.println("After: Target has " + target.getHandSize() + " cards.\n");
    }

    protected void draw(int numCards, Player player) {
        // TODO add draw card animation
        for (int i = 0; i < numCards; i++) {
            player.drawCard(deck.drawCard());
        }
    }

    protected void press(int numPresses, Player player) {
        // For each press
        for (int i = 0; i < numPresses; i++) {
            // TODO display press animation
            int randomNumber = rand.nextInt(3);

            // One third of the time (when the random number is 2)
            if (randomNumber == 2) {
                // Get a random number of cards to draw (1 to 6)
                int numCards = rand.nextInt(6) + 1;

                // Draw that amount of cards
                for (int j = 0; j < numCards; j++) {
                    player.drawCard(deck.drawCard());
                }
            }
        }
    }

    private void everyonePress(Player currentPlayer) {
        // Iterate through all the players in the game
        for (Player player : getPlayers()) {
            // If the player is different from the player who played this card, then they
            // will press the button once
            if (player.getPlayerNum() != currentPlayer.getPlayerNum()) {
                // TODO display press animation

                // Get a random number of cards to draw (1 to 6)
                int numCards = rand.nextInt(6) + 1;

                // Draw that amount of cards
                for (int j = 0; j < numCards; j++) {
                    player.drawCard(deck.drawCard());
                }
            }
        }
    }

    private void pressTillLose(Player targetPlayer) {
        // Create a boolean and a counter to keep track of button presses and success in
        // draw cards
        boolean cardsDrawn = false;
        int i = 0;

        while ((!cardsDrawn) && (i < 3)) {
            // TODO display press animation
            int randomNumber = rand.nextInt(3);

            // One third of the time (when the random number is 2)
            if (randomNumber == 2) {
                // Get a random number of cards to draw (1 to 6)
                int numCards = rand.nextInt(6) + 1;

                // Draw that amount of cards
                for (int j = 0; j < numCards; j++) {
                    targetPlayer.drawCard(deck.drawCard());
                }

                // The cards were drawn, so the boolean cardDrawn becomes true
                cardsDrawn = true;
            }
            i++; // The counter is incremented
        }
        // If after 3 presses, no cards are drawn, then cards will be drawn no matter
        // what the 4th time.
        if (!cardsDrawn) {
            // TODO display press animation

            // Get a random number of cards to draw (1 to 6)
            int numCards = rand.nextInt(6) + 1;

            // Draw that amount of cards
            for (int j = 0; j < numCards; j++) {
                targetPlayer.drawCard(deck.drawCard());
            }
        }
    }

    protected void spin(int numSpins, Player player) {
        // For each spin
        for (int i = 0; i < numSpins; i++) {
            // Get a random number from 0 to 6
            int randomNumber = rand.nextInt(6);

            // TODO add spin animation

            if (randomNumber == 0) {
                player.removePoints(15);
            } else if (randomNumber == 1) {
                player.removePoints(30);
            } else if (randomNumber == 2) {
                player.removePoints(45);
            } else if (randomNumber == 3) {
                player.removePoints(60);
            } else if (randomNumber == 4) {
                player.removePoints(75);
            } else {
                player.removePoints(100);
            }
        }
    }

    private void doThief(Player player) {
        int pointsToTake = 0;
        int p = rand.nextInt(6);

        if (p == 0) {
            pointsToTake = 15;
        } else if (p == 1) {
            pointsToTake = 30;
        } else if (p == 2) {
            pointsToTake = 45;
        } else if (p == 3) {
            pointsToTake = 60;
        } else if (p == 4) {
            pointsToTake = 75;
        } else {
            pointsToTake = 100;
        }
        player.addPoints(player.getNextPlayer().takePoints(pointsToTake));
    }

    protected void performWild(Player player, Tower tower) {
        if (player.getIsBot()) {
            if (isDarkMode.get()) {
                tower.getDisplayedCard().setActiveDarkColor(selectWildDarkColor(player));
            } else {
                tower.getDisplayedCard().setActiveLightColor(selectWildLightColor(player));
            }
        } else {
            if (isDarkMode.get()) {
                Card.DarkColor color;
                DarkColorChooserStage chooseColorStage = new DarkColorChooserStage(rand);
                chooseColorStage.showAndWait();

                color = chooseColorStage.getChoice();
                if (color == null) {
                    color = selectWildDarkColor(player);
                }

                tower.getDisplayedCard().setActiveDarkColor(color);
            } else {
                Card.LightColor color;
                LightColorChooserStage chooseColorStage = new LightColorChooserStage(rand);
                chooseColorStage.showAndWait();

                color = chooseColorStage.getChoice();
                if (color == null) {
                    color = selectWildLightColor(player);
                }

                tower.getDisplayedCard().setActiveLightColor(color);
            }
        }
    }

    public boolean drawCard(Player player) {
        // TODO finish implementing this method // Done?
        return player.drawCard(deck.drawCard());
    }

    private void performCardAction(Player currentPlayer, Tower currentTower) {

        if (isDarkMode.get()) {
            if ( (currentTower.getDisplayedCard().getDarkColor() == Card.DarkColor.WILD) || 
            (currentTower.getDisplayedCard().getDarkColor() == Card.DarkColor.SEMIWILD) ){
                performWild(currentPlayer, currentTower);
            }
            switch (currentTower.getDisplayedCard().getDarkValue()) {
                case N_NINE:
                    currentPlayer.getNextPlayer().removePoints(45 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    break;
                case N_EIGHT:
                    currentPlayer.getNextPlayer().removePoints(40 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    break;
                case N_SEVEN:
                    currentPlayer.getNextPlayer().removePoints(35 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    break;
                case N_SIX:
                    currentPlayer.getNextPlayer().removePoints(30 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    break;
                case N_FIVE:
                    currentPlayer.getNextPlayer().removePoints(25 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    break;
                case N_FOUR:
                    currentPlayer.getNextPlayer().removePoints(20 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    break;
                case N_THREE:
                    currentPlayer.getNextPlayer().removePoints(15 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    break;
                case N_TWO:
                    currentPlayer.getNextPlayer().removePoints(10 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    break;
                case N_ONE:
                    currentPlayer.getNextPlayer().removePoints(5 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    break;
                case ZERO:
                    currentPlayer.addPoints(75 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    break;
                case ONE:
                    currentPlayer.addPoints(2 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(3 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER));
                    break;
                case TWO:
                    currentPlayer.addPoints(4 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(6 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER));
                    break;
                case THREE:
                    currentPlayer.addPoints(6 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(9 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER));
                    break;
                case FOUR:
                    currentPlayer.addPoints(8 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(12 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER));
                    break;
                case FIVE:
                    currentPlayer.addPoints(10 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(15 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER));
                    break;
                case SIX:
                    currentPlayer.addPoints(12 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(18 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER));
                    break;
                case SEVEN:
                    currentPlayer.addPoints(14 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(21 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER));
                    break;
                case EIGHT:
                    currentPlayer.addPoints(16 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(24 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER));
                    break;
                case NINE:
                    currentPlayer.addPoints(18 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(27 * POINT_MULTIPLIER_GAME * DARK_MODE_MULTIPLIER));
                    break;
                case FLIP:
                    isDarkMode.setValue(false);
                    break;
                case TOWER_BUILD:
                    towerBuild(currentPlayer, currentTower);
                    break;
                case TOWER_DESTROY:
                    towerDestroy(currentPlayer, currentTower);
                    break;
                case REVERSE:
                    reverse();
                    break;
                case WILD_REGULAR:
                    break;
                case THIEF:
                    doThief(currentPlayer);
                    break;
                case ROTATE:
                    rotateHands(currentPlayer);
                    break;
                case SWAP:
                    swapHands(currentPlayer);
                    break;
                case DISCARD_ALL:
                    discardAllDarkColor(currentPlayer, currentTower.getDisplayedCard().getDarkColor());
                    break;
                case SKIP:
                    skipNext(currentPlayer);
                    break;
                case SKIP_ALL:
                    skipAll(currentPlayer);
                    break;
                case TARGET_DRAW_1:
                    targetedDraw(1, currentPlayer);
                    break;
                case TARGET_DRAW_2:
                    targetedDraw(2, currentPlayer);
                    break;
                case DRAW_2:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case DRAW_5:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case PRESS_1:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case PRESS_3:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case SPIN_1:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case SPIN_2:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case WILD_DRAW_4:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case WILD_PRESS_2:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case WILD_SHIELD: //Handled in stacking, otherwise nothing extra is done if the stack is not active.
                    break;
                case WILD_TIMES_2: //Handled in stacking, otherwise nothing extra is done if the stack is not active.
                    break;
                case WILD_SPIN_2:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case WILD_SPIN_3:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case WILD_SKIP:
                    skipNext(currentPlayer);
                    break;
                case WILD_REVERSE:
                    reverse();
                    break;
                case WILD_SWAP:
                    swapHands(currentPlayer);
                    break;
                case WILD_DRAW_TO_MATCH:
                    drawToMatchSpecificTower(currentPlayer.getNextPlayer(), currentTower);
                    currentPlayer.getNextPlayer().setIsSkipped(true);
                    break;
                case WILD_REDISTRIBUTE:
                    redistributeHands();
                    break;
                case WILD_EVERYONE_PRESS:
                    everyonePress(currentPlayer);
                    break;
                case WILD_PRESS_LOSE:
                    pressTillLose(currentPlayer.getNextPlayer());
                    break;
                case JACKPOT:
                    performJackpot(currentPlayer);
                    break;
                case WILD_JACKPOT:
                    performJackpot(currentPlayer);
                    break;
                default:
                    break;
            }
        } else {

            if (currentTower.getDisplayedCard().getLightColor() == Card.LightColor.WILD) {
                performWild(currentPlayer, currentTower);
            }
            switch (currentTower.getDisplayedCard().getLightValue()) {
                case N_NINE:
                    currentPlayer.getNextPlayer().removePoints(45 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    break;
                case N_EIGHT:
                    currentPlayer.getNextPlayer().removePoints(40 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    break;
                case N_SEVEN:
                    currentPlayer.getNextPlayer().removePoints(35 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    break;
                case N_SIX:
                    currentPlayer.getNextPlayer().removePoints(30 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    break;
                case N_FIVE:
                    currentPlayer.getNextPlayer().removePoints(25 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    break;
                case N_FOUR:
                    currentPlayer.getNextPlayer().removePoints(20 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    break;
                case N_THREE:
                    currentPlayer.getNextPlayer().removePoints(15 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    break;
                case N_TWO:
                    currentPlayer.getNextPlayer().removePoints(10 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    break;
                case N_ONE:
                    currentPlayer.getNextPlayer().removePoints(5 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    break;
                case ZERO:
                    currentPlayer.addPoints(50 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    break;
                case ONE:
                    currentPlayer.addPoints(4 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(1 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER));
                    break;
                case TWO:
                    currentPlayer.addPoints(8 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(2 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER));
                    break;
                case THREE:
                    currentPlayer.addPoints(12 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(3 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER));
                    break;
                case FOUR:
                    currentPlayer.addPoints(16 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(4 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER));
                    break;
                case FIVE:
                    currentPlayer.addPoints(20 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(5 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER));
                    break;
                case SIX:
                    currentPlayer.addPoints(24 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(6 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER));
                    break;
                case SEVEN:
                    currentPlayer.addPoints(28 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(7 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER));
                    break;
                case EIGHT:
                    currentPlayer.addPoints(32 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(8 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER));
                    break;
                case NINE:
                    currentPlayer.addPoints(36 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER);
                    currentPlayer.addPoints(currentTower.payTaxPoints(9 * POINT_MULTIPLIER_GAME * LIGHT_MODE_MULTIPLIER));
                    break;
                case FLIP:
                    isDarkMode.setValue(true);
                    break;
                case TOWER_BUILD:
                    towerBuild(currentPlayer, currentTower);
                    break;
                case TOWER_DESTROY:
                    towerDestroy(currentPlayer, currentTower);
                    break;
                case REVERSE:
                    reverse();
                    break;
                case WILD_REGULAR:
                    break;
                case THIEF:
                    doThief(currentPlayer);
                    break;
                case ROTATE:
                    rotateHands(currentPlayer);
                    break;
                case SWAP:
                    swapHands(currentPlayer);
                    break;
                case DISCARD_ALL:
                    discardAllLightColor(currentPlayer, currentTower.getDisplayedCard().getLightColor());
                    break;
                case SKIP:
                    skipNext(currentPlayer);
                    break;
                case SKIP_ALL:
                    skipAll(currentPlayer);
                    break;
                case TARGET_DRAW_1:
                    targetedDraw(1, currentPlayer);
                    break;
                case TARGET_DRAW_2:
                    targetedDraw(2, currentPlayer);
                    break;
                case DRAW_2:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case DRAW_5:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case PRESS_1:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case PRESS_3:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case SPIN_1:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case SPIN_2:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case WILD_DRAW_4:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case WILD_PRESS_2:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case WILD_SHIELD: //Handled in stacking, otherwise nothing extra is done if the stack is not active.
                    break;
                case WILD_TIMES_2: //Handled in stacking, otherwise nothing extra is done if the stack is not active.
                    break;
                case WILD_SPIN_2:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case WILD_SPIN_3:
                    stack.startStack(currentTower.getDisplayedCard(), currentTower);
                    break;
                case WILD_SKIP:
                    skipNext(currentPlayer);
                    break;
                case WILD_REVERSE:
                    reverse();
                    break;
                case WILD_SWAP:
                    swapHands(currentPlayer);
                    break;
                case WILD_DRAW_TO_MATCH:
                    drawToMatchSpecificTower(currentPlayer.getNextPlayer(), currentTower);
                    currentPlayer.getNextPlayer().setIsSkipped(true);
                    break;
                case WILD_REDISTRIBUTE:
                    redistributeHands();
                    break;
                case WILD_EVERYONE_PRESS:
                    everyonePress(currentPlayer);
                    break;
                case WILD_PRESS_LOSE:
                    pressTillLose(currentPlayer.getNextPlayer());
                    break;
                case JACKPOT:
                    performJackpot(currentPlayer);
                    break;
                case WILD_JACKPOT:
                    performJackpot(currentPlayer);
                    break;
                default:
                    break;
            }
        }

    }

    public void startRound() {
        isDarkMode.set(false);
        userViewingDarkMode.set(false);
        deck = new Deck(this, rand);
        stack.clearStack();

        for (Tower tower : getTowers()) {
            tower.clear();
        }
        numOfBuiltTowers = 0;

        castle.addCard(deck.drawCard());
        if (roundNumber > 1) {
            gamePane.getGameTable().selectTower(TowerPosition.CENTER);
        }
        


        for (Player player : playerList) {
            for (int i = 0; i < startingCardAmount; i++) {
                player.drawCard(deck.drawCard());
            }
        }

        int startingPlayerIndex = rand.nextInt(numOfPlayers);

        curPlayer = playerList.get(startingPlayerIndex);

        gameDirectionClockwise = true;
    }

    public void startFirstPlayerTurn() {
        if (roundNumber == 1) {
            GameStartStage gameStartStage = new GameStartStage(this);
            gameStartStage.showAndWait();
        }
        else {
            gamePane.refreshGameTable();
            RoundStartStage roundStartStage = new RoundStartStage(this);
            roundStartStage.showAndWait();
            //TODO show round start stage
        }
        
        curPlayer.startTurn();
    }

    public void endRound(Player winner) {
        lastRoundVictoryBonus = 0; //Resets the victory bonus from last round to be counter up again this round
        lastRoundWinner = winner; //Sets the last round's winner to the winner of the round

        for (Player player : playerList) {
            long pointsEarned = player.getRoundEndPoints();
            lastRoundVictoryBonus += pointsEarned;
            winner.addPoints(pointsEarned);
            player.getHand().clear();
            player.setIsSkipped(false);
            player.savePointsFromRound();
        }
        winner.savePointsFromRound();
        // TODO move the round's scores to a separate place (Did, needs testing)

        if (roundNumber < numOfRounds) {
            roundNumber++;
            this.startRound();
            this.startFirstPlayerTurn();
        } else {
            System.out.println("Game end"); // TODO end the game

            for (Tower tower : getTowers()) {
                tower.clear();
            }
            gamePane.refreshGameTable();

            GameEndStage gameEndStage = new GameEndStage(this, gamePane.getStage());
            gameEndStage.show();
        }
        //gamePane.refreshGameTable();
    }

    public boolean checkForRoundEnd() {
        boolean roundEnded = false;
        for (Player player : playerList) {
            if (player.getHand().size() <= 0) {
                endRound(player);
                roundEnded = true;
                break;
            }
        }
        return roundEnded;
    }

    public void checkForBigHandPenalty() {
        for (Player player : playerList) {
            if (player.getHand().size() >= sizeForBigHandPenalty) {
                player.performBigHandPenalty();
            }
        }
    }

    public Game(int numOfRounds, int numOfPlayers, DeckType deckType, String userName, GamePane gamePane) {
        this.deckType = deckType;
        this.imageLoader = new GameImageLoader();
        if ((numOfRounds == 1) || (numOfRounds == 3) || (numOfRounds == 5)) {
            this.numOfRounds = numOfRounds;
        } else {
            this.numOfRounds = 1;
        }
        this.roundNumber = 1;
        this.gamePane = gamePane;

        isDarkMode = new SimpleBooleanProperty(false);
        userViewingDarkMode = new SimpleBooleanProperty(false);

        rand = new Random();

        disputeDeck = new DisputeDeck(rand, this);
        stack = new Stack(this);

        castle = new Tower(true, Tower.TowerPosition.CENTER, this);
        northTower = new Tower(false, Tower.TowerPosition.NORTH, this);
        southTower = new Tower(false, Tower.TowerPosition.SOUTH, this);
        eastTower = new Tower(false, Tower.TowerPosition.EAST, this);
        westTower = new Tower(false, Tower.TowerPosition.WEST, this);

        if ((numOfPlayers >= 2) && (numOfPlayers <= 4)) {
            this.numOfPlayers = numOfPlayers;
        } else {
            this.numOfPlayers = 4;
        }

        playerList = new ArrayList<Player>();
        if (numOfPlayers == 3) {
            player1 = new Player(false, 1, userName, this);
            player2 = new Player(true, 2, new NameGenerator().getRandomName(2), this);
            player3 = new Player(true, 3, new NameGenerator().getRandomName(3), this);

            player1.setLeftPlayer(player3);
            player1.setRightPlayer(player2);

            player2.setLeftPlayer(player1);
            player2.setRightPlayer(player3);

            player3.setLeftPlayer(player2);
            player3.setRightPlayer(player1);

            playerList.add(player1);
            playerList.add(player2);
            playerList.add(player3);

        } else if (numOfPlayers == 4) {
            player1 = new Player(false, 1, userName, this);
            player2 = new Player(true, 2, new NameGenerator().getRandomName(2), this);
            player3 = new Player(true, 3, new NameGenerator().getRandomName(3), this);
            player4 = new Player(true, 4, new NameGenerator().getRandomName(4), this);

            player1.setLeftPlayer(player4);
            player1.setRightPlayer(player2);

            player2.setLeftPlayer(player1);
            player2.setRightPlayer(player3);

            player3.setLeftPlayer(player2);
            player3.setRightPlayer(player4);

            player4.setLeftPlayer(player3);
            player4.setRightPlayer(player1);

            playerList.add(player1);
            playerList.add(player2);
            playerList.add(player3);
            playerList.add(player4);

        }
        // else 2 players
        else {
            player1 = new Player(false, 1, userName, this);
            player2 = new Player(true, 2, new NameGenerator().getRandomName(2), this);

            player1.setLeftPlayer(player2);
            player1.setRightPlayer(player2);

            player2.setLeftPlayer(player1);
            player2.setRightPlayer(player1);

            playerList.add(player1);
            playerList.add(player2);
        }

        // curPlayer = player1; // TODO Randomly select first player and have card
        // effect apply
        this.startRound();

    }

}

// Old constructor code
/*
 * private Game() throws Exception {
 * isDarkMode = new SimpleBooleanProperty(false);
 * userViewingDarkMode = new SimpleBooleanProperty(false);
 * rand = new Random();
 * deck = new Deck(this, rand);
 * castle = new Tower(true, this);
 * castle.addCard(deck.drawCard());
 * tower1 = new Tower(false, this);
 * tower2 = new Tower(false, this);
 * tower3 = new Tower(false, this);
 * tower4 = new Tower(false, this);
 * 
 * player1 = new Player(false, 1, this);
 * player2 = new Player(true, 2, this);
 * player3 = new Player(true, 3, this);
 * player4 = new Player(true, 4, this);
 * 
 * playerList = new ArrayList<Player>();
 * playerList.add(player1);
 * playerList.add(player2);
 * playerList.add(player3);
 * playerList.add(player4);
 * 
 * for (int i = 0; i < 10; i++) {
 * player1.drawCard(deck.drawCard());
 * }
 * 
 * gameDirectionClockwise = true;
 * curPlayer = player1; // TODO Randomly select first player and have card
 * effect apply
 * 
 * }
 */