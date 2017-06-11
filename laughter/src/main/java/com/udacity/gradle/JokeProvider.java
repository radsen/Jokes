package com.udacity.gradle;

import java.util.Random;

public class JokeProvider {

    private static String[] jokes = new String[]{
            "Q: What did one cannibal say to the other while they were eating a clown?\n" +
                    "A: \"Does this taste funny to you?\"",
            "My meat is Grade A",
            "If this place is a meat market, you are the prime rib",
            "Q: What did the DNA say to the other DNA?\n" +
                    "A: Do these genes make my butt look fat."
    };

    public String getRandomJoke(){
        Random rnd = new Random();
        int min = 1;
        int randomIdx = rnd.nextInt(jokes.length - min) + min;
        return jokes[randomIdx];
    }
}
