package com.techelevator;

public class Nickel implements Coin{


    @Override
    public String getName() {
        return "Nickel";
    }

    @Override
    public double getValue() {
        return 0.05;
    }
}
