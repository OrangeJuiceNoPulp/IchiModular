package com.example;

import javafx.scene.control.TextArea;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class Tower {
    public enum TowerPosition {
        CENTER, NORTH, SOUTH, EAST, WEST
    }

    private Game game;

    private boolean isSelected;
    private TowerPosition position;

    private boolean isCastle;
    private boolean isEmpty;
    private boolean isOwned;
    private Player owner;

    private Card topCard;
    private Card bottomCard;

    private int counter;

    private TextArea towerDisplay;

    public TowerPosition getPosition() {
        return this.position;
    }

    public Player getOwner() {
        return this.owner;
    }

    public Game getGame() {
        return this.game;
    }

    public void setOwner(Player player) {
        this.owner = player;
        if (player != null) {
            this.isOwned = true;
        } else {
            this.isOwned = false;
        }
    }

    public TextArea getTowerDisplay() {
        return this.towerDisplay;
    }

    private void setSelection(boolean selection) {
        this.isSelected = selection;
    }

    public void select() {
        for (Tower tower : game.getTowers()) {
            tower.setSelection(false);
        }
        this.isSelected = true;
    }

    public boolean getIsSelected() {
        return this.isSelected;
    }

    public void refreshDisplay() {
        String output = new String();
        if (!isEmpty) {
            output = getDisplayedCard().toString() + "\n\n\nTop Card: " + getTopCard().toString() + "\nBottom Card: "
                    + getBottomCard().toString() +
                    "\n\nOwner: " + owner + "\nIs Owned: " + isOwned;
        }
        output = output + "\n\nSelected: " + this.isSelected;
        towerDisplay.setText(output);
    }

    public void clear() {
        this.isEmpty = true;
        this.counter = 0;
        this.topCard = null;
        this.bottomCard = null;
        this.isOwned = false;
        this.owner = null;
    }

    public boolean getIsEmpty() {
        return this.isEmpty;
    }

    public boolean getIsOwned() {
        return this.isOwned;
    }

    public boolean getIsCastle() {
        return this.isCastle;
    }

    public Card getDisplayedCard() {
        if (game.getIsDarkMode()) {
            return this.bottomCard;
        } else {
            return this.topCard;
        }
    }

    public Card getTopCard() {
        return this.topCard;
    }

    public Card getBottomCard() {
        return this.bottomCard;
    }

    public long payTaxPoints(long points) {
        if (isOwned) {
            owner.addPoints(points);
            return 0;
        } else {
            return points;
        }
    }

    public boolean addCard(Card card) {
        if (counter == 0 && isCastle) {
            topCard = card;
            bottomCard = card;
            counter = 1;
            this.isEmpty = false;
            return true;
        } else if (counter == 1) {
            if (game.getIsDarkMode()) {
                this.bottomCard = card;
                counter = 2;
                return true;
            } else {
                this.topCard = card;
                counter = 2;
                return true;
            }
        } else if (counter == 2) {
            if (game.getIsDarkMode()) {
                game.getDeck().burnCard(bottomCard);
                this.bottomCard = card;
                return true;
            } else {
                game.getDeck().burnCard(topCard);
                this.topCard = card;
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean startTower(Player owner) {
        if (isEmpty) {
            try {
                if (counter == 0) {
                    Card drawnCard = game.getDeck().drawCard();
                    topCard = drawnCard;
                    bottomCard = drawnCard;
                    counter = 1;
                    this.owner = owner;
                    this.isOwned = true;
                    this.isEmpty = false;
                    return true;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean destroyTower() {
        if (isCastle) {
            return false;
        }

        if (counter == 1) {
            game.getDeck().burnCard(topCard);
            this.topCard = null;
            this.bottomCard = null;
        } else if (counter == 2) {
            game.getDeck().burnCard(topCard);
            game.getDeck().burnCard(bottomCard);
            this.topCard = null;
            this.bottomCard = null;
        } else {
            return false;
        }

        this.isEmpty = true;
        this.owner = null;
        this.isOwned = false;
        this.counter = 0;

        return true;
    }

    public Tower(boolean isCastle, TowerPosition position, Game game) {
        this.isOwned = false;
        this.isCastle = isCastle;
        this.isEmpty = true;
        this.counter = 0;
        this.game = game;
        this.position = position;

        towerDisplay = new TextArea();
        towerDisplay.setEditable(false);
        towerDisplay.setOnDragDropped(e -> {
            // System.out.println("Discard DragDone"); // TODO remove this after debugging
            Dragboard db = e.getDragboard();
            boolean dropSuccess = false;
            boolean turnSuccess = false;

            if (db.hasContent(CardCellFactory.cardFormat)) {
                if (db.getContent(CardCellFactory.cardFormat) instanceof CardBase) {

                    CardBase draggedCard = (CardBase) db.getContent(CardCellFactory.cardFormat);
                    Card selectedCard = game.getSelectedCard();

                    // System.out.println(draggedCard.isTheSameCard(selectedCard)); // TODO remove
                    // this after debugging

                    if (draggedCard.isTheSameCard(selectedCard)) {
                        turnSuccess = game.playCard(game.getCurrentPlayer(), this, selectedCard);
                    }
                }

                if (turnSuccess) {
                    game.getCurrentPlayer().endTurn();
                    game.refreshGamePane();
                }
                dropSuccess = true;
            }
            e.setDropCompleted(dropSuccess);
            e.consume();
        });
        towerDisplay.setOnDragOver(e -> {
            if (e.getGestureSource() != towerDisplay && e.getDragboard().hasContent(CardCellFactory.cardFormat)) {
                e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
        });
        towerDisplay.setOnMouseClicked(e -> {
            this.select();
            game.refreshGamePane();
        });
    }
}
