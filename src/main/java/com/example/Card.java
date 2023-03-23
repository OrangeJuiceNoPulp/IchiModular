package com.example;

public class Card {
    private static final int POINT_MULTIPLIER = 5;

    private LightColor lightColor;
    private DarkColor darkColor;
    private LightColor activeLightColor;
    private DarkColor activeDarkColor;

    private Value lightValue;
    private Value darkValue;

    private StackType lightStackType;
    private StackType darkStackType;

    private Game game;
    private Player holder;

    public static Card makeRandomCard(Game game) {
        int lightSideNum = game.getRand().nextInt(6);
        int darkSideNum = game.getRand().nextInt(4);
        int val1 = game.getRand().nextInt(19);
        int val2 = game.getRand().nextInt(19);
        LightColor lightCol;
        DarkColor darkCol;
        Value lightVal;
        Value darkVal;

        if (lightSideNum == 0) {
            lightCol = LightColor.RED;
        } else if (lightSideNum == 1) {
            lightCol = LightColor.BLUE;
        } else if (lightSideNum == 2) {
            lightCol = LightColor.GREEN;
        } else if (lightSideNum == 3) {
            lightCol = LightColor.YELLOW;
        } else if (lightSideNum == 4) {
            lightCol = LightColor.PURPLE;
        } else {
            lightCol = LightColor.ORANGE;
        }

        if (darkSideNum == 0) {
            darkCol = DarkColor.AQUA;
        } else if (darkSideNum == 1) {
            darkCol = DarkColor.LIME;
        } else if (darkSideNum == 2) {
            darkCol = DarkColor.MAGENTA;
        } else {
            darkCol = DarkColor.CURRANT;
        }

        if (val1 == 0) {
            lightVal = Value.N_NINE;
        } else if (val1 == 1) {
            lightVal = Value.N_EIGHT;
        } else if (val1 == 2) {
            lightVal = Value.N_SEVEN;
        } else if (val1 == 3) {
            lightVal = Value.N_SIX;
        } else if (val1 == 4) {
            lightVal = Value.N_FIVE;
        } else if (val1 == 5) {
            lightVal = Value.N_FOUR;
        } else if (val1 == 6) {
            lightVal = Value.N_THREE;
        } else if (val1 == 7) {
            lightVal = Value.N_TWO;
        } else if (val1 == 8) {
            lightVal = Value.N_ONE;
        } else if (val1 == 9) {
            lightVal = Value.ZERO;
        } else if (val1 == 10) {
            lightVal = Value.ONE;
        } else if (val1 == 11) {
            lightVal = Value.TWO;
        } else if (val1 == 12) {
            lightVal = Value.THREE;
        } else if (val1 == 13) {
            lightVal = Value.FOUR;
        } else if (val1 == 14) {
            lightVal = Value.FIVE;
        } else if (val1 == 15) {
            lightVal = Value.SIX;
        } else if (val1 == 16) {
            lightVal = Value.SEVEN;
        } else if (val1 == 17) {
            lightVal = Value.EIGHT;
        } else {
            lightVal = Value.NINE;
        } 

        if (val2 == 0) {
            darkVal = Value.N_NINE;
        } else if (val2 == 1) {
            darkVal = Value.N_EIGHT;
        } else if (val2 == 2) {
            darkVal = Value.N_SEVEN;
        } else if (val2 == 3) {
            darkVal = Value.N_SIX;
        } else if (val2 == 4) {
            darkVal = Value.N_FIVE;
        } else if (val2 == 5) {
            darkVal = Value.N_FOUR;
        } else if (val2 == 6) {
            darkVal = Value.N_THREE;
        } else if (val2 == 7) {
            darkVal = Value.N_TWO;
        } else if (val2 == 8) {
            darkVal = Value.N_ONE;
        } else if (val2 == 9) {
            darkVal = Value.ZERO;
        } else if (val2 == 10) {
            darkVal = Value.ONE;
        } else if (val2 == 11) {
            darkVal = Value.TWO;
        } else if (val2 == 12) {
            darkVal = Value.THREE;
        } else if (val2 == 13) {
            darkVal = Value.FOUR;
        } else if (val2 == 14) {
            darkVal = Value.FIVE;
        } else if (val2 == 15) {
            darkVal = Value.SIX;
        } else if (val2 == 16) {
            darkVal = Value.SEVEN;
        } else if (val2 == 17) {
            darkVal = Value.EIGHT;
        } else {
            darkVal = Value.NINE;
        } 

        return new Card(lightCol, lightVal, darkCol, darkVal, game);
    }

    public void setActiveLightColor(LightColor color) {
        this.activeLightColor = color;
    }
    public void setActiveDarkColor(DarkColor color) {
        this.activeDarkColor = color;
    }

    public StackType getLightStackType() {
        return this.lightStackType;
    }
    public StackType getDarkStackType(){
        return this.darkStackType;
    }

    public void resetWild() {
        switch (this.activeLightColor) {
            case WILD_BLUE:
                activeLightColor = LightColor.WILD;
                break;
            case WILD_GREEN:
                activeLightColor = LightColor.WILD;
                break;
            case WILD_ORANGE:
                activeLightColor = LightColor.WILD;
                break;
            case WILD_PURPLE:
                activeLightColor = LightColor.WILD;
                break;
            case WILD_RED:
                activeLightColor = LightColor.WILD;
                break;
            case WILD_YELLOW:
                activeLightColor = LightColor.WILD;
                break;
            default:
                break;
        }

        switch (this.activeDarkColor) {

            case WILD_AQUA:
                if (this.darkColor == DarkColor.WILD) {
                    activeDarkColor = DarkColor.WILD;
                } else if (this.darkColor == DarkColor.SEMIWILD) {
                    activeDarkColor = DarkColor.SEMIWILD;
                }
                break;
            case WILD_CURRANT:
                if (this.darkColor == DarkColor.WILD) {
                    activeDarkColor = DarkColor.WILD;
                } else if (this.darkColor == DarkColor.SEMIWILD) {
                    activeDarkColor = DarkColor.SEMIWILD;
                }
                break;
            case WILD_LIME:
                if (this.darkColor == DarkColor.WILD) {
                    activeDarkColor = DarkColor.WILD;
                } else if (this.darkColor == DarkColor.SEMIWILD) {
                    activeDarkColor = DarkColor.SEMIWILD;
                }
                break;
            case WILD_MAGENTA:
                if (this.darkColor == DarkColor.WILD) {
                    activeDarkColor = DarkColor.WILD;
                } else if (this.darkColor == DarkColor.SEMIWILD) {
                    activeDarkColor = DarkColor.SEMIWILD;
                }
                break;
            default:
                break;

        }

    }

    public CardDisplay getCardDisplay(boolean darkMode) {
        CardDisplay display;
        if (darkMode) {
            display = new CardDisplay(activeDarkColor, darkValue);
        } else {
            display = new CardDisplay(activeLightColor, lightValue);
        }
        return display;
    }

    public CardDisplayOld getOldCardDisplay(boolean darkMode) {
        CardDisplayOld display;
        if (darkMode) {
            display = new CardDisplayOld(activeDarkColor, darkValue);
        } else {
            display = new CardDisplayOld(activeLightColor, lightValue);
        }
        return display;
    }

    public Game getGame() {
        return this.game;
    }

    private void setStackType() {
        switch (this.lightValue) {
            case DRAW_2:
                this.lightStackType = StackType.DRAW;
                break;
            case DRAW_5:
                this.lightStackType = StackType.DRAW;
                break;
            case PRESS_1:
                this.lightStackType = StackType.PRESS;
                break;
            case PRESS_3:
                this.lightStackType = StackType.PRESS;
                break;
            case SPIN_1:
                this.lightStackType = StackType.SPIN;
                break;
            case SPIN_2:
                this.lightStackType = StackType.SPIN;
                break;
            case WILD_DRAW_4:
                this.lightStackType = StackType.DRAW;
                break;
            case WILD_PRESS_2:
                this.lightStackType = StackType.PRESS;
                break;
            case WILD_SHIELD:
                this.lightStackType = StackType.ALL;
                break;
            case WILD_TIMES_2:
                this.lightStackType = StackType.ALL;
                break;
            case WILD_SPIN_2:
                this.lightStackType = StackType.SPIN;
                break;
            case WILD_SPIN_3:
                this.lightStackType = StackType.SPIN;
                break;
            default:
                this.lightStackType = StackType.NONE;
                break;
        }
        switch (this.darkValue) {
            case DRAW_2:
                this.darkStackType = StackType.DRAW;
                break;
            case DRAW_5:
                this.darkStackType = StackType.DRAW;
                break;
            case PRESS_1:
                this.darkStackType = StackType.PRESS;
                break;
            case PRESS_3:
                this.darkStackType = StackType.PRESS;
                break;
            case SPIN_1:
                this.darkStackType = StackType.SPIN;
                break;
            case SPIN_2:
                this.darkStackType = StackType.SPIN;
                break;
            case WILD_DRAW_4:
                this.darkStackType = StackType.DRAW;
                break;
            case WILD_PRESS_2:
                this.darkStackType = StackType.PRESS;
                break;
            case WILD_SHIELD:
                this.darkStackType = StackType.ALL;
                break;
            case WILD_TIMES_2:
                this.darkStackType = StackType.ALL;
                break;
            case WILD_SPIN_2:
                this.darkStackType = StackType.SPIN;
                break;
            case WILD_SPIN_3:
                this.darkStackType = StackType.SPIN;
                break;
            default:
                this.darkStackType = StackType.NONE;
                break;
        }
    }

    public Card(LightColor lightColor, Value lightValue, DarkColor darkColor, Value darkValue, Game game) {
        this.lightColor = lightColor;
        this.activeLightColor = lightColor;
        this.lightValue = lightValue;
        this.darkColor = darkColor;
        this.activeDarkColor = darkColor;
        this.darkValue = darkValue;
        this.game = game;
        this.setStackType();
    }

    protected Card(LightColor lightColor, Value lightValue, DarkColor darkColor, Value darkValue) {
        this.lightColor = lightColor;
        this.activeLightColor = lightColor;
        this.lightValue = lightValue;
        this.darkColor = darkColor;
        this.activeDarkColor = darkColor;
        this.darkValue = darkValue;
        this.setStackType();
    }

    public void setHolder(Player holder) {
        this.holder = holder;
    }

    public Player getHolder() {
        return this.holder;
    }

    public String toString() {
        String card_info;

        if (game.getIsDarkMode()) {
            card_info = this.activeDarkColor + " " + this.darkValue;
        } else {
            card_info = this.activeLightColor + " " + this.lightValue;
        }

        return card_info;
    }

    public void setLightSide(LightColor color, Value value) {
        this.lightColor = color;
        this.lightValue = value;
    }

    public void setDarkSide(DarkColor color, Value value) {
        this.darkColor = color;
        this.darkValue = value;
    }

    public LightColor getLightColor() {
        return this.lightColor;
    }

    public DarkColor getDarkColor() {
        return this.darkColor;
    }

    public Value getLightValue() {
        return this.lightValue;
    }

    public Value getDarkValue() {
        return this.darkValue;
    }

    public LightColor getActiveLightColor() {
        return this.activeLightColor;
    }

    public DarkColor getActiveDarkColor() {
        return this.activeDarkColor;
    }

    public boolean isPlayable(Card topCard, boolean isDarkMode) {
        if (isDarkMode) {
            if (topCard.getDarkValue() == this.darkValue) {
                return true;
            }
            if (topCard.getActiveDarkColor() == DarkColor.WILD) {
                return true;
            }
            switch (this.darkColor) {
                case WILD:
                    return true;
                case AQUA:
                    if ((topCard.getActiveDarkColor() == DarkColor.AQUA)
                            || (topCard.getActiveDarkColor() == DarkColor.WILD_AQUA)) {
                        return true;
                    } else {
                        break;
                    }
                case MAGENTA:
                    if ((topCard.getActiveDarkColor() == DarkColor.MAGENTA)
                            || (topCard.getActiveDarkColor() == DarkColor.WILD_MAGENTA)) {
                        return true;
                    } else {
                        break;
                    }
                case LIME:
                    if ((topCard.getActiveDarkColor() == DarkColor.LIME)
                            || (topCard.getActiveDarkColor() == DarkColor.WILD_LIME)) {
                        return true;
                    } else {
                        break;
                    }
                case CURRANT:
                    if ((topCard.getActiveDarkColor() == DarkColor.CURRANT)
                            || (topCard.getActiveDarkColor() == DarkColor.WILD_CURRANT)) {
                        return true;
                    } else {
                        break;
                    }
                case SEMIWILD:
                    if ((topCard.getActiveDarkColor() == DarkColor.WILD_AQUA)
                            || (topCard.getActiveDarkColor() == DarkColor.WILD_MAGENTA)
                            || (topCard.getActiveDarkColor() == DarkColor.WILD_LIME)
                            || (topCard.getActiveDarkColor() == DarkColor.WILD_CURRANT)) {
                        return true;
                    } else {
                        break;
                    }
                default:
                    break;
            }
            switch (topCard.getDarkValue()) {
                case N_NINE:
                    if (this.getDarkValue() == Value.NINE)
                        return true;
                    else
                        return false;
                case N_EIGHT:
                    if (this.getDarkValue() == Value.EIGHT)
                        return true;
                    else
                        return false;
                case N_SEVEN:
                    if (this.getDarkValue() == Value.SEVEN)
                        return true;
                    else
                        return false;
                case N_SIX:
                    if (this.getDarkValue() == Value.SIX)
                        return true;
                    else
                        return false;
                case N_FIVE:
                    if (this.getDarkValue() == Value.FIVE)
                        return true;
                    else
                        return false;
                case N_FOUR:
                    if (this.getDarkValue() == Value.FOUR)
                        return true;
                    else
                        return false;
                case N_THREE:
                    if (this.getDarkValue() == Value.THREE)
                        return true;
                    else
                        return false;
                case N_TWO:
                    if (this.getDarkValue() == Value.TWO)
                        return true;
                    else
                        return false;
                case N_ONE:
                    if (this.getDarkValue() == Value.ONE)
                        return true;
                    else
                        return false;
                case ONE:
                    if (this.getDarkValue() == Value.N_ONE)
                        return true;
                    else
                        return false;
                case TWO:
                    if (this.getDarkValue() == Value.N_TWO)
                        return true;
                    else
                        return false;
                case THREE:
                    if (this.getDarkValue() == Value.N_THREE)
                        return true;
                    else
                        return false;
                case FOUR:
                    if (this.getDarkValue() == Value.N_FOUR)
                        return true;
                    else
                        return false;
                case FIVE:
                    if (this.getDarkValue() == Value.N_FIVE)
                        return true;
                    else
                        return false;
                case SIX:
                    if (this.getDarkValue() == Value.N_SIX)
                        return true;
                    else
                        return false;
                case SEVEN:
                    if (this.getDarkValue() == Value.N_SEVEN)
                        return true;
                    else
                        return false;
                case EIGHT:
                    if (this.getDarkValue() == Value.N_EIGHT)
                        return true;
                    else
                        return false;
                case NINE:
                    if (this.getDarkValue() == Value.N_NINE)
                        return true;
                    else
                        return false;
                default:
                    return false;
            }

        } else {
            if (topCard.getLightValue() == this.lightValue) {
                return true;
            }
            if (topCard.getActiveLightColor() == LightColor.WILD) {
                return true;
            }

            switch (this.lightColor) {
                case WILD:
                    return true;
                case RED:
                    if ((topCard.getActiveLightColor() == LightColor.RED)
                            || (topCard.getActiveLightColor() == LightColor.WILD_RED)) {
                        return true;
                    } else {
                        break;
                    }
                case BLUE:
                    if ((topCard.getActiveLightColor() == LightColor.BLUE)
                            || (topCard.getActiveLightColor() == LightColor.WILD_BLUE)) {
                        return true;
                    } else {
                        break;
                    }
                case YELLOW:
                    if ((topCard.getActiveLightColor() == LightColor.YELLOW)
                            || (topCard.getActiveLightColor() == LightColor.WILD_YELLOW)) {
                        return true;
                    } else {
                        break;
                    }
                case GREEN:
                    if ((topCard.getActiveLightColor() == LightColor.GREEN)
                            || (topCard.getActiveLightColor() == LightColor.WILD_GREEN)) {
                        return true;
                    } else {
                        break;
                    }
                case ORANGE:
                    if ((topCard.getActiveLightColor() == LightColor.ORANGE)
                            || (topCard.getActiveLightColor() == LightColor.WILD_ORANGE)) {
                        return true;
                    } else {
                        break;
                    }
                case PURPLE:
                    if ((topCard.getActiveLightColor() == LightColor.PURPLE)
                            || (topCard.getActiveLightColor() == LightColor.WILD_PURPLE)) {
                        return true;
                    } else {
                        break;
                    }
                default:
                    break;
            }
            switch (topCard.getLightValue()) {
                case N_NINE:
                    if (this.getLightValue() == Value.NINE)
                        return true;
                    else
                        return false;
                case N_EIGHT:
                    if (this.getLightValue() == Value.EIGHT)
                        return true;
                    else
                        return false;
                case N_SEVEN:
                    if (this.getLightValue() == Value.SEVEN)
                        return true;
                    else
                        return false;
                case N_SIX:
                    if (this.getLightValue() == Value.SIX)
                        return true;
                    else
                        return false;
                case N_FIVE:
                    if (this.getLightValue() == Value.FIVE)
                        return true;
                    else
                        return false;
                case N_FOUR:
                    if (this.getLightValue() == Value.FOUR)
                        return true;
                    else
                        return false;
                case N_THREE:
                    if (this.getLightValue() == Value.THREE)
                        return true;
                    else
                        return false;
                case N_TWO:
                    if (this.getLightValue() == Value.TWO)
                        return true;
                    else
                        return false;
                case N_ONE:
                    if (this.getLightValue() == Value.ONE)
                        return true;
                    else
                        return false;
                case ONE:
                    if (this.getLightValue() == Value.N_ONE)
                        return true;
                    else
                        return false;
                case TWO:
                    if (this.getLightValue() == Value.N_TWO)
                        return true;
                    else
                        return false;
                case THREE:
                    if (this.getLightValue() == Value.N_THREE)
                        return true;
                    else
                        return false;
                case FOUR:
                    if (this.getLightValue() == Value.N_FOUR)
                        return true;
                    else
                        return false;
                case FIVE:
                    if (this.getLightValue() == Value.N_FIVE)
                        return true;
                    else
                        return false;
                case SIX:
                    if (this.getLightValue() == Value.N_SIX)
                        return true;
                    else
                        return false;
                case SEVEN:
                    if (this.getLightValue() == Value.N_SEVEN)
                        return true;
                    else
                        return false;
                case EIGHT:
                    if (this.getLightValue() == Value.N_EIGHT)
                        return true;
                    else
                        return false;
                case NINE:
                    if (this.getLightValue() == Value.N_NINE)
                        return true;
                    else
                        return false;
                default:
                    return false;
            }

        }
    }

    public enum LightColor {
        RED, BLUE, YELLOW, GREEN, ORANGE, PURPLE, WILD_RED, WILD_BLUE, WILD_GREEN, WILD_YELLOW, WILD_ORANGE,
        WILD_PURPLE, WILD
    }

    public enum DarkColor {
        AQUA, MAGENTA, LIME, CURRANT, SEMIWILD, WILD_AQUA, WILD_MAGENTA, WILD_LIME, WILD_CURRANT, WILD
    }

    public enum Value {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, N_ONE, N_TWO, N_THREE, N_FOUR, N_FIVE, N_SIX,
        N_SEVEN, N_EIGHT, N_NINE, FLIP, REVERSE, TOWER_DESTROY, TOWER_BUILD, THIEF, SWAP, ROTATE, DISCARD_ALL, TARGET_DRAW_1, TARGET_DRAW_2,
        SKIP, SKIP_ALL, DRAW_2, DRAW_5, PRESS_1, PRESS_3, SPIN_1, SPIN_2, JACKPOT, WILD_REGULAR, WILD_SKIP, WILD_REVERSE, WILD_SWAP,
        WILD_JACKPOT, WILD_DRAW_4, WILD_PRESS_2, WILD_PRESS_LOSE, WILD_EVERYONE_PRESS, WILD_SHIELD, WILD_TIMES_2, WILD_SPIN_2,
        WILD_SPIN_3, WILD_DRAW_TO_MATCH, WILD_REDISTRIBUTE
    }

    //TODO not implemented yet
    //WILD_JACKPOT, JACKPOT
    //WILD_PRESS_LOSE
    //WILD_EVERYONE_PRESS
    //WILD_DRAW_TO_MATCH
    //WILD_REDISTRIBUTE

    public enum StackType {
        NONE, PRESS, DRAW, SPIN, ALL
    }

    public CardBase getCardBase() {
        return new CardBase(this);
    }

    public int getPointValue(boolean darkMode) {
        Card.Value val;
        int points = 0;
        if (darkMode) {
            val = this.darkValue;
        }
        else {
            val = this.lightValue;
        }

        switch (val) {
            case N_NINE:
                    points += POINT_MULTIPLIER * 9;
                    break;
                case N_EIGHT:
                    points += POINT_MULTIPLIER * 8;
                    break;
                case N_SEVEN:
                    points += POINT_MULTIPLIER * 7;
                    break;
                case N_SIX:
                    points += POINT_MULTIPLIER * 6;
                    break;
                case N_FIVE:
                    points += POINT_MULTIPLIER * 5;
                    break;
                case N_FOUR:
                    points += POINT_MULTIPLIER * 4;
                    break;
                case N_THREE:
                    points += POINT_MULTIPLIER * 3;
                    break;
                case N_TWO:
                    points += POINT_MULTIPLIER * 2;
                    break;
                case N_ONE:
                    points += POINT_MULTIPLIER * 1;
                    break;
                case ZERO:
                    points += POINT_MULTIPLIER * 10;
                    break;
                case ONE:
                    points += POINT_MULTIPLIER * 1;
                    break;
                case TWO:
                    points += POINT_MULTIPLIER * 2;
                    break;
                case THREE:
                    points += POINT_MULTIPLIER * 3;
                    break;
                case FOUR:
                    points += POINT_MULTIPLIER * 4;
                    break;
                case FIVE:
                    points += POINT_MULTIPLIER * 5;
                    break;
                case SIX:
                    points += POINT_MULTIPLIER * 6;
                    break;
                case SEVEN:
                    points += POINT_MULTIPLIER * 7;
                    break;
                case EIGHT:
                    points += POINT_MULTIPLIER * 8;
                    break;
                case NINE:
                    points += POINT_MULTIPLIER * 9;
                    break;
                case REVERSE:
                    points += POINT_MULTIPLIER * 15;
                    break;
                case ROTATE:
                    points += POINT_MULTIPLIER * 15;
                    break;
                case SWAP:
                    points += POINT_MULTIPLIER * 15;
                    break;
                case SKIP:
                    points += POINT_MULTIPLIER * 15;
                    break;
                case SKIP_ALL:
                    points += POINT_MULTIPLIER * 15;
                    break;
                case FLIP:
                    points += POINT_MULTIPLIER * 15;
                    break;
                case PRESS_1:
                    points += POINT_MULTIPLIER * 20;
                    break;
                case PRESS_3:
                    points += POINT_MULTIPLIER * 20;
                    break;
                case SPIN_1:
                    points += POINT_MULTIPLIER * 20;
                    break;
                case SPIN_2:
                    points += POINT_MULTIPLIER * 20;
                    break;
                case DRAW_2:
                    points += POINT_MULTIPLIER * 20;
                    break;
                case DRAW_5:
                    points += POINT_MULTIPLIER * 20;
                    break;
                case TARGET_DRAW_1:
                    points += POINT_MULTIPLIER * 20;
                    break;
                case TARGET_DRAW_2:
                    points += POINT_MULTIPLIER * 20;
                    break;
                case DISCARD_ALL:
                    points += POINT_MULTIPLIER * 15;
                    break;
                case THIEF:
                    points += POINT_MULTIPLIER * 20;
                    break;
                case TOWER_BUILD:
                    points += POINT_MULTIPLIER * 20;
                    break;
                case TOWER_DESTROY:
                    points += POINT_MULTIPLIER * 20;
                    break;
                case JACKPOT:
                    points += POINT_MULTIPLIER * 15;
                    break;
                case WILD_REGULAR:
                    points += POINT_MULTIPLIER * 30;
                    break;
                case WILD_REVERSE:
                    points += POINT_MULTIPLIER * 35;
                    break;
                case WILD_SWAP:
                    points += POINT_MULTIPLIER * 35;
                    break;
                case WILD_REDISTRIBUTE:
                    points += POINT_MULTIPLIER * 35;
                    break;
                case WILD_SKIP:
                    points += POINT_MULTIPLIER * 35;
                    break;
                case WILD_DRAW_4:
                    points += POINT_MULTIPLIER * 40;
                    break;
                case WILD_DRAW_TO_MATCH:
                    points += POINT_MULTIPLIER * 40;
                    break;
                case WILD_PRESS_2:
                    points += POINT_MULTIPLIER * 40;
                    break;
                case WILD_EVERYONE_PRESS:
                    points += POINT_MULTIPLIER * 40;
                    break;
                case WILD_PRESS_LOSE:
                    points += POINT_MULTIPLIER * 40;
                    break;
                case WILD_SPIN_2:
                    points += POINT_MULTIPLIER * 40;
                    break;
                case WILD_SPIN_3:
                    points += POINT_MULTIPLIER * 40;
                    break;
                case WILD_TIMES_2:
                    points += POINT_MULTIPLIER * 40;
                    break;
                case WILD_SHIELD:
                    points += POINT_MULTIPLIER * 35;
                    break;
                case WILD_JACKPOT:
                    points += POINT_MULTIPLIER * 35;
                    break;
        }
        return points;
    }

    public int getID(boolean darkMode) {
        int cardID = 0;
        if (darkMode) {
            switch (this.darkColor) {
                case AQUA:
                    cardID += 1000;
                    break;
                case LIME:
                    cardID += 2000;
                    break;
                case MAGENTA:
                    cardID += 3000;
                    break;
                case CURRANT:
                    cardID += 4000;
                    break;
                case SEMIWILD:
                    cardID += 5000;
                    break;
                case WILD:
                    cardID += 6000;
                    break;
                default:
                    break;

            }
            switch (this.darkValue) {
                case N_NINE:
                    cardID += 1;
                    break;
                case N_EIGHT:
                    cardID += 2;
                    break;
                case N_SEVEN:
                    cardID += 3;
                    break;
                case N_SIX:
                    cardID += 4;
                    break;
                case N_FIVE:
                    cardID += 5;
                    break;
                case N_FOUR:
                    cardID += 6;
                    break;
                case N_THREE:
                    cardID += 7;
                    break;
                case N_TWO:
                    cardID += 8;
                    break;
                case N_ONE:
                    cardID += 9;
                    break;
                case ZERO:
                    cardID += 10;
                    break;
                case ONE:
                    cardID += 11;
                    break;
                case TWO:
                    cardID += 12;
                    break;
                case THREE:
                    cardID += 13;
                    break;
                case FOUR:
                    cardID += 14;
                    break;
                case FIVE:
                    cardID += 15;
                    break;
                case SIX:
                    cardID += 16;
                    break;
                case SEVEN:
                    cardID += 17;
                    break;
                case EIGHT:
                    cardID += 18;
                    break;
                case NINE:
                    cardID += 19;
                    break;
                case REVERSE:
                    cardID += 20;
                    break;
                case ROTATE:
                    cardID += 21;
                    break;
                case SWAP:
                    cardID += 22;
                    break;
                case SKIP:
                    cardID += 23;
                    break;
                case SKIP_ALL:
                    cardID += 24;
                    break;
                case FLIP:
                    cardID += 25;
                    break;
                case PRESS_1:
                    cardID += 26;
                    break;
                case PRESS_3:
                    cardID += 27;
                    break;
                case SPIN_1:
                    cardID += 28;
                    break;
                case SPIN_2:
                    cardID += 29;
                    break;
                case DRAW_2:
                    cardID += 30;
                    break;
                case DRAW_5:
                    cardID += 31;
                    break;
                case TARGET_DRAW_1:
                    cardID += 32;
                    break;
                case TARGET_DRAW_2:
                    cardID += 33;
                    break;
                case DISCARD_ALL:
                    cardID += 34;
                    break;
                case THIEF:
                    cardID += 35;
                    break;
                case TOWER_BUILD:
                    cardID += 36;
                    break;
                case TOWER_DESTROY:
                    cardID += 37;
                    break;
                case JACKPOT:
                    cardID += 38;
                    break;
                case WILD_REGULAR:
                    cardID += 39;
                    break;
                case WILD_REVERSE:
                    cardID += 40;
                    break;
                case WILD_SWAP:
                    cardID += 41;
                    break;
                case WILD_REDISTRIBUTE:
                    cardID += 42;
                    break;
                case WILD_SKIP:
                    cardID += 43;
                    break;
                case WILD_DRAW_4:
                    cardID += 44;
                    break;
                case WILD_DRAW_TO_MATCH:
                    cardID += 45;
                    break;
                case WILD_PRESS_2:
                    cardID += 46;
                    break;
                case WILD_EVERYONE_PRESS:
                    cardID += 47;
                    break;
                case WILD_PRESS_LOSE:
                    cardID += 48;
                    break;
                case WILD_SPIN_2:
                    cardID += 49;
                    break;
                case WILD_SPIN_3:
                    cardID += 50;
                    break;
                case WILD_TIMES_2:
                    cardID += 51;
                    break;
                case WILD_SHIELD:
                    cardID += 52;
                    break;
                case WILD_JACKPOT:
                    cardID += 53;
                    break;
            }
        } else {
            switch (this.lightColor) {
                case RED:
                    cardID += 1000;
                    break;
                case BLUE:
                    cardID += 2000;
                    break;
                case GREEN:
                    cardID += 3000;
                    break;
                case YELLOW:
                    cardID += 4000;
                    break;
                case ORANGE:
                    cardID += 5000;
                    break;
                case PURPLE:
                    cardID += 6000;
                    break;
                case WILD:
                    cardID += 7000;
                    break;
                default:
                    break;
            }
            switch (this.lightValue) {
                case N_NINE:
                    cardID += 1;
                    break;
                case N_EIGHT:
                    cardID += 2;
                    break;
                case N_SEVEN:
                    cardID += 3;
                    break;
                case N_SIX:
                    cardID += 4;
                    break;
                case N_FIVE:
                    cardID += 5;
                    break;
                case N_FOUR:
                    cardID += 6;
                    break;
                case N_THREE:
                    cardID += 7;
                    break;
                case N_TWO:
                    cardID += 8;
                    break;
                case N_ONE:
                    cardID += 9;
                    break;
                case ZERO:
                    cardID += 10;
                    break;
                case ONE:
                    cardID += 11;
                    break;
                case TWO:
                    cardID += 12;
                    break;
                case THREE:
                    cardID += 13;
                    break;
                case FOUR:
                    cardID += 14;
                    break;
                case FIVE:
                    cardID += 15;
                    break;
                case SIX:
                    cardID += 16;
                    break;
                case SEVEN:
                    cardID += 17;
                    break;
                case EIGHT:
                    cardID += 18;
                    break;
                case NINE:
                    cardID += 19;
                    break;
                case REVERSE:
                    cardID += 20;
                    break;
                case ROTATE:
                    cardID += 21;
                    break;
                case SWAP:
                    cardID += 22;
                    break;
                case SKIP:
                    cardID += 23;
                    break;
                case SKIP_ALL:
                    cardID += 24;
                    break;
                case FLIP:
                    cardID += 25;
                    break;
                case PRESS_1:
                    cardID += 26;
                    break;
                case PRESS_3:
                    cardID += 27;
                    break;
                case SPIN_1:
                    cardID += 28;
                    break;
                case SPIN_2:
                    cardID += 29;
                    break;
                case DRAW_2:
                    cardID += 30;
                    break;
                case DRAW_5:
                    cardID += 31;
                    break;
                case TARGET_DRAW_1:
                    cardID += 32;
                    break;
                case TARGET_DRAW_2:
                    cardID += 33;
                    break;
                case DISCARD_ALL:
                    cardID += 34;
                    break;
                case THIEF:
                    cardID += 35;
                    break;
                case TOWER_BUILD:
                    cardID += 36;
                    break;
                case TOWER_DESTROY:
                    cardID += 37;
                    break;
                case JACKPOT:
                    cardID += 38;
                    break;
                case WILD_REGULAR:
                    cardID += 39;
                    break;
                case WILD_REVERSE:
                    cardID += 40;
                    break;
                case WILD_SWAP:
                    cardID += 41;
                    break;
                case WILD_REDISTRIBUTE:
                    cardID += 42;
                    break;
                case WILD_SKIP:
                    cardID += 43;
                    break;
                case WILD_DRAW_4:
                    cardID += 44;
                    break;
                case WILD_DRAW_TO_MATCH:
                    cardID += 45;
                    break;
                case WILD_PRESS_2:
                    cardID += 46;
                    break;
                case WILD_EVERYONE_PRESS:
                    cardID += 47;
                    break;
                case WILD_PRESS_LOSE:
                    cardID += 48;
                    break;
                case WILD_SPIN_2:
                    cardID += 49;
                    break;
                case WILD_SPIN_3:
                    cardID += 50;
                    break;
                case WILD_TIMES_2:
                    cardID += 51;
                    break;
                case WILD_SHIELD:
                    cardID += 52;
                    break;
                case WILD_JACKPOT:
                    cardID += 53;
                    break;
            }

        }
        return cardID;
    }

    public static int getPriorityValue(Tower tower, Card card, Player player) {
        int priorityVal = 0;

        Card.Value cardVal;

        //Determines the card value depending if the game is currently in darkmode or lightmode
        if (tower.getGame().getIsDarkMode()) {
            cardVal = card.getDarkValue();
        }
        else {
            cardVal = card.getLightValue();
        }

        //If the tower is owned
        if (tower.getIsOwned()) {
            //If the owner of the tower is the same as this player
            if (tower.getOwner().getPlayerNum() == player.getPlayerNum()) {
                switch (cardVal) {
                    case N_NINE:    
                    case N_EIGHT:
                    case N_SEVEN:
                    case N_SIX:
                    case N_FIVE:
                    case N_FOUR:
                    case N_THREE:
                    case N_TWO:
                    case N_ONE:
                        priorityVal += 9;
                        break;
                    case ZERO:
                    case ONE:
                    case TWO:
                    case THREE:
                    case FOUR:
                    case FIVE:
                    case SIX:
                    case SEVEN:
                    case EIGHT:
                    case NINE:
                        priorityVal += 10;
                        break;
    
                    case REVERSE:
                    case ROTATE:
                    case SWAP:
                    case SKIP:
                    case SKIP_ALL:
                        priorityVal += 6;
                        break;
                    case FLIP:
                        priorityVal += 4;
                        break;
    
                    case PRESS_1:
                    case PRESS_3:
                    case SPIN_1:
                    case SPIN_2:
                    case DRAW_2:
                    case DRAW_5:
                    case TARGET_DRAW_1:
                    case TARGET_DRAW_2:
                        priorityVal += 5;
                        break;
                    case DISCARD_ALL:
                        priorityVal += 12;
                        break;
    
                    case THIEF:
                        priorityVal += 8;
                        break;
                    case TOWER_BUILD:
                        priorityVal += 13;
                        break;
                    case TOWER_DESTROY:
                        priorityVal -= 20;
                        break;
                    case JACKPOT:
                        priorityVal += 11;
                        break;
    
                    case WILD_REGULAR:
                        priorityVal -= 10;
                        break;
    
                    case WILD_REVERSE:
                        priorityVal -= 11;
                        break;
    
                    case WILD_SWAP:
                        priorityVal -= 11;
                        break;
    
                    case WILD_REDISTRIBUTE:
                        priorityVal -= 13;
                        break;
    
                    case WILD_SKIP:
                        priorityVal -= 11;
                        break;
    
                    case WILD_DRAW_4:
                        priorityVal -= 12;
                        break;
    
                    case WILD_DRAW_TO_MATCH:
                        priorityVal -= 13;
                        break;
    
                    case WILD_PRESS_2:
                    case WILD_EVERYONE_PRESS:
                        priorityVal -= 13;
                        break;
    
                    case WILD_PRESS_LOSE:
                        priorityVal -= 13;
                        break;
    
                    case WILD_SPIN_2:
                    case WILD_SPIN_3:
                        priorityVal -= 12;
                        break;
    
                    case WILD_TIMES_2:
                    case WILD_SHIELD:
                        priorityVal -= 15;
                        break;
    
                    case WILD_JACKPOT:
                        priorityVal -= 10;
                        break;
                }
            }

            //Else the owner of the tower is different than this player
            else {
                switch (cardVal) {
                    case N_NINE:    
                    case N_EIGHT:
                    case N_SEVEN:
                    case N_SIX:
                    case N_FIVE:
                    case N_FOUR:
                    case N_THREE:
                    case N_TWO:
                    case N_ONE:
                        priorityVal += 9;
                        break;
                    case ZERO:
                        priorityVal += 10;
                        break;
                    case ONE:
                    case TWO:
                    case THREE:
                    case FOUR:
                    case FIVE:
                    case SIX:
                    case SEVEN:
                    case EIGHT:
                    case NINE:
                        priorityVal += 0;
                        break;
    
                    case REVERSE:
                    case ROTATE:
                    case SWAP:
                    case SKIP:
                    case SKIP_ALL:
                        priorityVal += 6;
                        break;
                    case FLIP:
                        priorityVal += 4;
                        break;
    
                    case PRESS_1:
                    case PRESS_3:
                    case SPIN_1:
                    case SPIN_2:
                    case DRAW_2:
                    case DRAW_5:
                    case TARGET_DRAW_1:
                    case TARGET_DRAW_2:
                        priorityVal += 5;
                        break;
                    case DISCARD_ALL:
                        priorityVal += 12;
                        break;
    
                    case THIEF:
                        priorityVal -= 8;
                        break;
                    case TOWER_BUILD:
                        priorityVal += 13;
                        break;
                    case TOWER_DESTROY:
                        priorityVal += 13;
                        break;
                    case JACKPOT:
                        priorityVal += 11;
                        break;
    
                    case WILD_REGULAR:
                        priorityVal -= 10;
                        break;
    
                    case WILD_REVERSE:
                        priorityVal -= 11;
                        break;
    
                    case WILD_SWAP:
                        priorityVal -= 11;
                        break;
    
                    case WILD_REDISTRIBUTE:
                        priorityVal -= 13;
                        break;
    
                    case WILD_SKIP:
                        priorityVal -= 11;
                        break;
    
                    case WILD_DRAW_4:
                        priorityVal -= 12;
                        break;
    
                    case WILD_DRAW_TO_MATCH:
                        priorityVal -= 13;
                        break;
    
                    case WILD_PRESS_2:
                    case WILD_EVERYONE_PRESS:
                        priorityVal -= 13;
                        break;
    
                    case WILD_PRESS_LOSE:
                        priorityVal -= 13;
                        break;
    
                    case WILD_SPIN_2:
                    case WILD_SPIN_3:
                        priorityVal -= 12;
                        break;
    
                    case WILD_TIMES_2:
                    case WILD_SHIELD:
                        priorityVal -= 15;
                        break;
    
                    case WILD_JACKPOT:
                        priorityVal -= 10;
                        break;
                }
            }

        }
        
        //Else the tower is no owned
        else {
            switch (cardVal) {
                case N_NINE:    
                case N_EIGHT:
                case N_SEVEN:
                case N_SIX:
                case N_FIVE:
                case N_FOUR:
                case N_THREE:
                case N_TWO:
                case N_ONE:
                    priorityVal += 9;
                        break;
                case ZERO:
                case ONE:
                case TWO:
                case THREE:
                case FOUR:
                case FIVE:
                case SIX:
                case SEVEN:
                case EIGHT:
                case NINE:
                    priorityVal += 10;
                    break;

                case REVERSE:
                case ROTATE:
                case SWAP:
                case SKIP:
                case SKIP_ALL:
                    priorityVal += 6;
                    break;
                case FLIP:
                    priorityVal += 4;
                    break;

                case PRESS_1:
                case PRESS_3:
                case SPIN_1:
                case SPIN_2:
                case DRAW_2:
                case DRAW_5:
                case TARGET_DRAW_1:
                case TARGET_DRAW_2:
                    priorityVal += 5;
                    break;
                case DISCARD_ALL:
                    priorityVal += 12;
                    break;

                case THIEF:
                    priorityVal += 8;
                    break;
                case TOWER_BUILD:
                    priorityVal += 13;
                    break;
                case TOWER_DESTROY:
                    priorityVal += 13;
                    break;
                case JACKPOT:
                    priorityVal += 11;
                    break;

                case WILD_REGULAR:
                    priorityVal -= 10;
                    break;

                case WILD_REVERSE:
                    priorityVal -= 11;
                    break;

                case WILD_SWAP:
                    priorityVal -= 11;
                    break;

                case WILD_REDISTRIBUTE:
                    priorityVal -= 13;
                    break;

                case WILD_SKIP:
                    priorityVal -= 11;
                    break;

                case WILD_DRAW_4:
                    priorityVal -= 12;
                    break;

                case WILD_DRAW_TO_MATCH:
                    priorityVal -= 13;
                    break;

                case WILD_PRESS_2:
                case WILD_EVERYONE_PRESS:
                    priorityVal -= 13;
                    break;

                case WILD_PRESS_LOSE:
                    priorityVal -= 13;
                    break;

                case WILD_SPIN_2:
                case WILD_SPIN_3:
                    priorityVal -= 12;
                    break;

                case WILD_TIMES_2:
                case WILD_SHIELD:
                    priorityVal -= 15;
                    break;

                case WILD_JACKPOT:
                    priorityVal -= 10;
                    break;
            }
        }
        return priorityVal;
    }



}
