package com.techelevator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MakeChange {

    public static String makeChange (double currentMoney) {

        Coin[] coins = new Coin[]{new Quarter(), new Dime(), new Nickel()};   // Instantiate
        currentMoney *= 100;   // multiply the money we have * 100, so it's in cents
        String result = " ";

        // New Hashmap to store our change
        Map<Coin, Integer> change = new HashMap<Coin, Integer>();

        for (Coin coin : coins) {   // iterate through the array of coins

            if (currentMoney <= 0) {   // if there is no money in change
                break;
            }

            // find out how many cents are owed by dividing currentMoney by the coins
            int cents = (int) ((int) currentMoney / coin.getValue());

            if (cents > 0) {   //if we have change to give
                currentMoney = currentMoney % (coin.getValue() * cents);
                change.put(coin, cents);
            }
        }

        for (Coin coin : change.keySet()) {
            result += (change.get(coin) + " " + coin.getName() + "(s)" + " ");  // format to return the change
        }
        return result;

    }
}



