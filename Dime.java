package com.techelevator;

public class Dime implements Coin{


    @Override
    public String getName() {
        return "Dime";
    }

    @Override
    public double getValue() {
        return 0.10;
    }
}
