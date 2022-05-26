package com.techelevator;

public class Quarter implements Coin{


    @Override
    public String getName() {
        return "Quarter";
    }

    @Override
    public double getValue() {
        return 0.25;
    }
}
