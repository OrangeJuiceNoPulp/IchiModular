package com.example;

import java.io.Serializable;

public class CardBase implements Serializable {
    private Card.LightColor lightColor;
    private Card.DarkColor darkColor;
    private Card.Value lightValue;
    private Card.Value darkValue;

    public CardBase(Card card) {
        this.lightColor = card.getLightColor();
        this.darkColor = card.getDarkColor();
        this.lightValue = card.getLightValue();
        this.darkValue = card.getDarkValue();
    }

    public boolean isTheSameCard(Card card) {
        boolean status = true;
        if (card.getLightColor() != this.lightColor) {
            status = false;
        }
        else if (card.getDarkColor() != this.darkColor) {
            status = false;
        }
        else if (card.getLightValue() != this.lightValue) {
            status = false;
        }
        else if (card.getDarkValue() != this.darkValue) {
            status = false;
        }
        return status;
    }
}