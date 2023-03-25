package com.example;

import java.util.Random;

public class NameGenerator {
    private static final String[] names1 = {
        "Jordan",
        "Liam",
        "Stefan",
        "Cassie",
        "Jason"
    };
    private static final String[] names2 = {
        "Steve",
        "Randy",
        "Ellie"
    };
    private static final String[] names3 = {
        "Dave",
        "Bill",
        "Amanda"
    };
    private static final String[] names4 = {
        "Nora",
        "Benny",
        "Alice"
    };

    private static final String[] foods1 = {
        "Banana",
        "Apple",
        "Cucumber",
        "Mustard",
        "Egg"
    };

    private static final String[] furnitures1 = {
        "Table",
        "Chair",
        "Desk",
        "Lamp",
        "Television"
    };

    private Random rand;

    public String getRandomName(int playerNumber) {
        if (playerNumber == 1) {
            int randomNumber = rand.nextInt(names1.length);
            return names1[randomNumber];
        }
        else if (playerNumber == 2) {
            int randomNumber = rand.nextInt(names2.length);
            return names2[randomNumber];
        }
        else if (playerNumber == 3) {
            int randomNumber = rand.nextInt(names3.length);
            return names3[randomNumber];
        }
        else {
            int randomNumber1 = rand.nextInt(foods1.length);
            int randomNumber2 = rand.nextInt(furnitures1.length);

            String name = foods1[randomNumber1] + furnitures1[randomNumber2] + String.format("%02d", rand.nextInt(100));
            return name;
        }

    }
    public NameGenerator() {
        rand = new Random();
    }

}
