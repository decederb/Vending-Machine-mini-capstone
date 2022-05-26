package com.techelevator;

public class CustomerMoney {

    // default starting money in vending machine
        private double currentMoney = 0.00;

    public double getCurrentMoney() {
        return currentMoney;
}

    public void addCurrentMoney(double moneyToAdd){
        this.currentMoney += moneyToAdd;
        }

    public void subtractCurrentMoney(double moneyToSub){
        if (moneyToSub <= this.currentMoney){
        this.currentMoney -= moneyToSub;

        }
    }
}


