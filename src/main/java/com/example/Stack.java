package com.example;

public class Stack {
    private Game game;

    private Card.StackType stackType;
    private boolean stackingActive;
    private Tower stackingTower;

    private int stackMagnitude;

    public Card.StackType getStackType() {
        return this.stackType;
    }

    public int getStackMagnitude() {
        return this.stackMagnitude;
    }

    public boolean getStackingActive() {
        return this.stackingActive;
    }

    public Tower getTower() {
        return this.stackingTower;
    }

    public boolean isStackable(Card card) {
        if ((this.stackingActive == false) || (this.stackType == null)) {
            return false;
        } else if ((this.stackType == Card.StackType.NONE) || (this.stackType == Card.StackType.ALL)) {
            this.clearStack();
            return false;
        } else {
            Card.StackType cardStackType;
            if (game.getIsDarkMode()) {
                cardStackType = card.getDarkStackType();
            } else {
                cardStackType = card.getLightStackType();
            }

            //If the card's stacking type is the same as the stack's stacking type, the card is stackable and true is returned
            if (cardStackType == Card.StackType.ALL) {
                return true;
            } else if (cardStackType == this.stackType) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void clearStack() {
        stackMagnitude = 0;
        stackType = null;
        stackingActive = false;
        stackingTower = null;
    }

    // The specified player accepts the stack of cards.
    public void acceptStack(Player player) {
        if (stackType == Card.StackType.DRAW) {
            game.draw(stackMagnitude, player);
        } else if (stackType == Card.StackType.PRESS) {
            game.press(stackMagnitude, player);
        } else if (stackType == Card.StackType.SPIN) {
            game.spin(stackMagnitude, player);
        }
        clearStack();
    }

    // Starts the stack given the played card.
    public void startStack(Card card, Tower tower) {
        this.stackingActive = true;
        this.stackingTower = tower;

        Card.Value value;
        if (game.getIsDarkMode()) {
            value = card.getDarkValue();
        } else {
            value = card.getLightValue();
        }

        switch (value) {
            case DRAW_2:
                this.stackType = Card.StackType.DRAW;
                this.stackMagnitude = 2;
                break;
            case DRAW_5:
                this.stackType = Card.StackType.DRAW;
                this.stackMagnitude = 5;
                break;
            case PRESS_1:
                this.stackType = Card.StackType.PRESS;
                this.stackMagnitude = 1;
                break;
            case PRESS_3:
                this.stackType = Card.StackType.PRESS;
                this.stackMagnitude = 3;
                break;
            case SPIN_1:
                this.stackType = Card.StackType.SPIN;
                this.stackMagnitude = 1;
                break;
            case SPIN_2:
                this.stackType = Card.StackType.SPIN;
                this.stackMagnitude = 2;
                break;
            case WILD_DRAW_4:
                this.stackType = Card.StackType.DRAW;
                this.stackMagnitude = 4;
                break;
            case WILD_PRESS_2:
                this.stackType = Card.StackType.PRESS;
                this.stackMagnitude = 2;
                break;
            case WILD_SPIN_2:
                this.stackType = Card.StackType.SPIN;
                this.stackMagnitude = 2;
                break;
            case WILD_SPIN_3:
                this.stackType = Card.StackType.SPIN;
                this.stackMagnitude = 3;
                break;
            default:
                this.stackType = null;
                this.stackingActive = false;
                this.stackMagnitude = 0;
                break;
        }
    }

    // Adds the card to the stack
    public void addToStack(Card card, Player player, Tower tower) {
        // If the stack is currently active, so increase the stack magnitude
        if (this.stackingActive) {
            if (isStackable(card)) {
                Card.Value value;
                if (game.getIsDarkMode()) {
                    value = card.getDarkValue();
                } else {
                    value = card.getLightValue();
                }
    
                switch (value) {
                    case DRAW_2:
                        this.stackMagnitude += 2;
                        break;
                    case DRAW_5:
                        this.stackMagnitude += 5;
                        break;
                    case PRESS_1:
                        this.stackMagnitude += 1;
                        break;
                    case PRESS_3:
                        this.stackMagnitude += 3;
                        break;
                    case SPIN_1:
                        this.stackMagnitude += 1;
                        break;
                    case SPIN_2:
                        this.stackMagnitude += 2;
                        break;
                    case WILD_DRAW_4:
                        this.stackMagnitude += 4;
                        break;
                    case WILD_PRESS_2:
                        this.stackMagnitude += 2;
                        break;
                    case WILD_SPIN_2:
                        this.stackMagnitude += 2;
                        break;
                    case WILD_SPIN_3:
                        this.stackMagnitude += 3;
                        break;
                    case WILD_TIMES_2:
                        this.stackMagnitude *= 2;
                        break;
                    // A shield card is played, so the stack magnitude is set to zero and the stack
                    // ends (by having the player accept it)
                    case WILD_SHIELD:
                        this.stackMagnitude = 0;
                        acceptStack(player);
                        break;
                    default:
                        break;
                }
            }
        }
        // Else the stack is not active, so a new stack is started
        else {
            startStack(card, tower);
        }
    }

    public Stack(Game game) {
        this.game = game;
        this.stackMagnitude = 0;
        this.stackingActive = false;
    }
}
