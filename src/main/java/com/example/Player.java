package com.example;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.util.Pair;

public class Player {
    private static final int bigHandPenaltyPoints = 1000;
    private boolean isBot; // Whether the player is a bot or the user
    private int playerNum; // A number to identify the player in the game
    private boolean isSkipped;
    private int score; // The score of the player for the current round
    private int overallScore; //The score of the player for the current game
    private Game game;
    private boolean isPlayerTurn; // TODO Remove this???

    private String playerName;

    private Player leftPlayer; // The player to the left of this player
    private Player rightPlayer; // The player to the right of this player

    private ObservableList<Card> playerHand; // A list of the cards in the player's hand

    public void setHand(ObservableList<Card> hand) {
        this.playerHand = hand;
    }

    public void setIsSkipped(boolean skipped) {
        this.isSkipped = skipped;
    }

    // Applies the penalty for getting too large of a hand of cards, and decreases
    // the number of cards held
    public void performBigHandPenalty() {
        // TODO add animation for penalty

        // Removes 1000 points from the player as a penalty
        this.removePoints(bigHandPenaltyPoints);

        ArrayList<Card> cardsToBeDiscarded = new ArrayList<Card>();
        // Adds all the cards in the player's hand to a list of cards to be discarded.
        for (Card card : this.getHand()) {
            cardsToBeDiscarded.add(card);
        }

        // Removes two random cards from the list of cards to be discarded, so the
        // player will have 2 cards left.
        cardsToBeDiscarded.remove(game.getRand().nextInt(cardsToBeDiscarded.size()));
        cardsToBeDiscarded.remove(game.getRand().nextInt(cardsToBeDiscarded.size()));

        // Removes all the other cards from the player's hand.
        for (Card card : cardsToBeDiscarded) {
            game.getDeck().burnCard(card);
            this.discardCard(card);
        }
        this.isSkipped = true;
    }

    // Runs at the start of each player's turn.
    // Skips the player if they are skipped.
    // Start's the bot's turn.
    public void startTurn() {
        System.out.println(this.toString() + "'s turn has started.");
        if (this.isSkipped) {
            // TODO add skip animation
            System.out.println(this.toString() + " was skipped.\n");
            this.endTurn(); // isSkipped is set to false when the turn ends.
            return;
        }
        this.isPlayerTurn = true;

        // If there currently is a stack going on
        if (game.getStack().getStackingActive()) {

            if (this.isBot) {

                ArrayList<Card> cardChoicesList = new ArrayList<Card>(105);

                // Creates a list of stackable cards in the player's hand
                for (Card card : playerHand) {
                    if (game.getStack().isStackable(card)) {
                        cardChoicesList.add(card);
                    }
                }

                // If the list of card choices for stacking is not empty
                if (cardChoicesList.size() > 0) {
                    cardChoicesList.sort(new Comparator<Card>() {
                        public int compare(Card o1, Card o2) {
                            if (Card.getPriorityValue(game.getStack().getTower(), o1, Player.this) < Card
                                    .getPriorityValue(game.getStack().getTower(), o2, Player.this)) {
                                return 1;
                            } else if (Card.getPriorityValue(game.getStack().getTower(), o1, Player.this) > Card
                                    .getPriorityValue(game.getStack().getTower(), o2, Player.this)) {
                                return -1;
                            } else {
                                return 0;
                            }
                        };
                    });
                    // The list is sorted by priority. Higher priority at the beginning of the list.

                    int numChoices = (int) Math.round(Math.floor(cardChoicesList.size() / 3.0)); // Determines a third
                                                                                                 // of the
                    // list's size

                    // If a third of the list size ends up being less than 1, then numChoices is set
                    // to 1
                    if (numChoices < 1) {
                        numChoices = 1;
                    }

                    int choiceIndex = game.getRand().nextInt(numChoices); // Randomly chooses an index from the first
                                                                          // third of
                                                                          // the list of possible choices
                    Card cardToStack = cardChoicesList.get(choiceIndex);

                    //If the card is stackable, it is added to the stack
                    if (game.getStack().isStackable(cardToStack)) {
                        game.getStack().addToStack(cardToStack, this, game.getStack().getTower());
                        game.getStack().getTower().addCard(cardToStack);
                        this.discardCard(cardToStack);
                        if (game.getIsDarkMode()) {
                            if (cardToStack.getDarkColor() == Card.DarkColor.WILD) {
                                game.performWild(this, game.getStack().getTower());
                            }
                        } else {
                            if (cardToStack.getLightColor() == Card.LightColor.WILD) {
                                game.performWild(this, game.getStack().getTower());
                            }
                        }
                    } 
                    //Else the card is not stackable, so the bot accepts the stack
                    else {
                        game.getStack().acceptStack(this);
                    }

                }
                // Else the bot has no cards which are stackable
                else {
                    //If the bot has enough points to top deck
                    if (this.getScore() >= StackContributeStage.topDeckPenalty) {
                        this.removePoints(StackContributeStage.topDeckPenalty);
                        Card topDeckCard = game.getDeck().drawCard();
                        this.drawCard(topDeckCard);

                        //If the top deck card is stackable, it is added to the stack
                        if (game.getStack().isStackable(topDeckCard)) {
                            game.getStack().addToStack(topDeckCard, this, game.getStack().getTower());
                            game.getStack().getTower().addCard(topDeckCard);
                            this.discardCard(topDeckCard);
                            if (game.getIsDarkMode()) {
                                if (topDeckCard.getDarkColor() == Card.DarkColor.WILD) {
                                    game.performWild(this, game.getStack().getTower());
                                }
                            } else {
                                if (topDeckCard.getLightColor() == Card.LightColor.WILD) {
                                    game.performWild(this, game.getStack().getTower());
                                }
                            }
                        } 
                        //Else the top deck card is not stackable, so the bot accepts the stack
                        else {
                            game.getStack().acceptStack(this);
                        }
                    } 
                    //Else the bot does not have enough points to top deck, and thus accepts the stack
                    else {
                        game.getStack().acceptStack(this);
                    }
                }
                this.endTurn();
                return;
            } else {

                StackContributeStage stackContributeStage = new StackContributeStage(this, game);
                stackContributeStage.showAndWait();

                Card cardToStack = stackContributeStage.getStackedCard();

                if (cardToStack != null) {
                    if (game.getStack().isStackable(cardToStack)) {
                        game.getStack().addToStack(cardToStack, this, game.getStack().getTower());
                        game.getStack().getTower().addCard(cardToStack);
                        this.discardCard(cardToStack);
                        if (game.getIsDarkMode()) {
                            if (cardToStack.getDarkColor() == Card.DarkColor.WILD) {
                                game.performWild(this, game.getStack().getTower());
                            }
                        } else {
                            if (cardToStack.getLightColor() == Card.LightColor.WILD) {
                                game.performWild(this, game.getStack().getTower());
                            }
                        }
                    } else {
                        game.getStack().acceptStack(this);
                    }
                } else {
                    game.getStack().acceptStack(this);
                }
                this.endTurn();
                return;
            }
        }

        // If the player is a bot, their turn is automatically taken
        if (this.isBot) {
            this.doBotTurn();
            this.endTurn();
            return;
        }
        // Else the player is the user, so the method returns and the game will continue
        // upon the player's next input
        else {
            //game.refreshGamePane();
            game.setWaitingForUserToTakeTurn(true); // TODO set to false whenever the play button is pressed or card is
                                                    // dragged
            return;
        }
    }

    private void doBotTurn() {

        Pair<Card, Tower> botMove = getBotMove(); // Determines the card that the bot will play and which tower it will
                                                  // play it at

        if (botMove != null) {
            game.playCard(this, botMove.getValue(), botMove.getKey());
        }

    }

    private Pair<Card, Tower> getBotMove() {
        // Creates a list of possible moves for the bot to make
        ArrayList<Pair<Card, Tower>> possibleMoves = new ArrayList<Pair<Card, Tower>>();

        // Fills the list with possible moves
        for (Card card : playerHand) {
            for (Tower tower : game.getTowers()) {
                // If the tower is not empty
                if (!tower.getIsEmpty()) {
                    if (card.isPlayable(tower.getDisplayedCard(), game.getIsDarkMode())) {
                        possibleMoves.add(new Pair<Card, Tower>(card, tower));
                    }
                }
            }
        }

        // If there is a possible move to make
        if (possibleMoves.size() > 0) {
            possibleMoves.sort(new Comparator<Pair<Card, Tower>>() {
                public int compare(javafx.util.Pair<Card, Tower> o1, javafx.util.Pair<Card, Tower> o2) {
                    if (Card.getPriorityValue(o1.getValue(), o1.getKey(), Player.this) < Card
                            .getPriorityValue(o2.getValue(), o2.getKey(), Player.this)) {
                        return 1;
                    } else if (Card.getPriorityValue(o1.getValue(), o1.getKey(), Player.this) > Card
                            .getPriorityValue(o2.getValue(), o2.getKey(), Player.this)) {
                        return -1;
                    } else {
                        return 0;
                    }
                };
            });
            // The list of possible moves is sorted such that moves with greater priority
            // are at the bottom of the list,
            // moves with greater priority are at the top of the list.

            int numChoices = (int) Math.round(Math.floor(possibleMoves.size() / 3.0)); // Determines a third of the
                                                                                       // list's size

            // If a third of the list size ends up being less than 1, then numChoices is set
            // to 1
            if (numChoices < 1) {
                numChoices = 1;
            }

            int choiceIndex = game.getRand().nextInt(numChoices); // Randomly chooses an index from the first third of
                                                                  // the list of possible choices

            return possibleMoves.get(choiceIndex);

        }

        // Else, there are no possible moves, so a card is drawn
        else {
            game.drawCard(this);
            return null; // null is returned to tell the doBotTurn method to end this player's turn
        }
    }

    // Selects a target player automatically if bot, or opens a window for the user
    // to select their target
    public Player selectTargetPlayer() {
        if (this.isBot) {
            ArrayList<Player> choices = new ArrayList<Player>(5);
            int minHandSize = 1000; // An absurdly large value which is more cards than any player could hold
            for (Player player : game.getPlayers()) {
                if (player.playerNum != this.playerNum) {
                    if (minHandSize == player.getHand().size()) {
                        choices.add(player);
                    } else if (minHandSize > player.getHand().size()) {
                        choices.clear();
                        minHandSize = player.getHand().size();
                        choices.add(player);
                    }
                }
            }
            int choice = game.getRand().nextInt(choices.size());
            return choices.get(choice);
        } else {
            // If there are only two players, then the other player is automatically chosen
            if (game.getPlayers().size() == 2) {
                return this.getNextPlayer();
            } else {
                PlayerChooserStage chooserStage = new PlayerChooserStage(this, game);
                chooserStage.showAndWait();
                Player selection = chooserStage.getChoice();
                if (selection != null) {
                    return selection;
                } else {
                    ArrayList<Player> choices = new ArrayList<Player>(5);
                    int minHandSize = 1000; // An absurdly large value which is more cards than any player could hold
                    for (Player player : game.getPlayers()) {
                        if (player.playerNum != this.playerNum) {
                            if (minHandSize == player.getHand().size()) {
                                choices.add(player);
                            } else if (minHandSize > player.getHand().size()) {
                                choices.clear();
                                minHandSize = player.getHand().size();
                                choices.add(player);
                            }
                        }
                    }
                    int choice = game.getRand().nextInt(choices.size());
                    return choices.get(choice);
                }
            }
        }
    }

    public int getRoundEndPoints() {
        int points = 0;
        for (Card card : playerHand) {
            points += card.getPointValue(game.getIsDarkMode());
        }
        return points;
    }

    public int getScore() {
        return this.score;
    }
    public int getOverallScore() {
        return this.overallScore;
    }

    public void savePointsFromRound() {
        this.overallScore += this.score;
        this.score = 0;
    }

    public boolean getIsBot() {
        return this.isBot;
    }

    

    public String getPlayerName() {
        String correctedPlayerName = new String(playerName);

        int textLength = Math.min(12, correctedPlayerName.length());
        correctedPlayerName = correctedPlayerName.substring(0, textLength);

        if (playerName.length() > 12) {
            correctedPlayerName = correctedPlayerName + "...";
        } 

        if (isBot) {
            correctedPlayerName = correctedPlayerName + " (Bot)";
        }
        else {
            correctedPlayerName = correctedPlayerName + " (You)";
        }
        return correctedPlayerName;
    }

    public String getTrueName() {
        String correctedPlayerName = new String(playerName);

        if (isBot) {
            correctedPlayerName = correctedPlayerName + " (Bot)";
        }

        return correctedPlayerName;
    }

    // Returns a pointer to the player whose turn is next.
    public Player getNextPlayer() {
        if (game.isClockwise()) {
            return leftPlayer;
        } else {
            return rightPlayer;
        }
    }

    // Returns a pointer to the player whose turn would have been previous assuming
    // the current game rotation. (For the rotate hands method)
    public Player getPrevPlayer() {
        if (game.isClockwise()) {
            return rightPlayer;
        } else {
            return leftPlayer;
        }
    }

    //Code from https://stackoverflow.com/questions/26454149/make-javafx-wait-and-continue-with-code
    //Answer by DaveB October 19, 2014
    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
      }

    public void endTurn() {
        System.out.println(this.toString() + "'s turn has ended.\n"); //TODO remove debug text
        this.isSkipped = false;
        this.isPlayerTurn = false;

        
        game.refreshGamePane();

        delay(1500, () -> {
            game.checkForBigHandPenalty();
            if (!(game.checkForRoundEnd())) {
                game.setCurrentPlayer(this.getNextPlayer());
                game.getCurrentPlayer().startTurn();
            }
        });

        
    }

    public int getHandSize() {
        return this.playerHand.size();
    }

    public void setLeftPlayer(Player leftPlayer) {
        this.leftPlayer = leftPlayer;
    }

    public void setRightPlayer(Player rightPlayer) {
        this.rightPlayer = rightPlayer;
    }

    public Player getLeftPlayer() {
        return this.leftPlayer;
    }

    public Player getRightPlayer() {
        return this.rightPlayer;
    }

    public int getPlayerNum() {
        return this.playerNum;
    }

    public boolean drawCard(Card card) {
        //card.setHolder(this);
        return playerHand.add(card);
    }

    public boolean discardCard(Card card) {
        //card.setHolder(null);
        return playerHand.remove(card);
    }

    public ObservableList<Card> getHand() {
        return this.playerHand;
    }

    public void addPoints(int points) {
        score += points;
    }

    public void removePoints(int points) {
        score -= points;
    }

    public String toString() {
        return "Player " + playerNum;
    }

    // Tries to remove the given number of points until the player's points become
    // zero. Returns the number of points successfully taken.
    public int takePoints(int points) {
        int initialScore = score;
        score -= points;
        if (score < 0) {
            score = 0;
        }
        return (initialScore - score);
    }

    public Player(boolean isBot, int playerNum, String playerName, Game game) {
        this.game = game;
        this.isBot = isBot;
        this.playerNum = playerNum;
        this.isSkipped = false;
        this.score = 0;
        this.isPlayerTurn = false;

        this.playerName = playerName;

        // if (isBot) {
        //     this.playerName = playerName + " (Bot)";
        // }
        // else {
        //     this.playerName = playerName + " (You)";
        // }
        
        playerHand = FXCollections.observableArrayList(new ArrayList<Card>(105));
    }

}
