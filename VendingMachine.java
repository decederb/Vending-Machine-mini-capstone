package com.techelevator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachine {
    private Map<String, Item> itemList = new HashMap<>();
    private int totalSales = 0;

    public VendingMachine(File f) {
        try (FileReader fr = new FileReader(f);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] itemProperties;
                itemProperties = line.split("\\|");
                int price = (int) (Double.parseDouble(itemProperties[2]) * 100);
                itemList.put(itemProperties[0], new Item(itemProperties[0], itemProperties[1], price, itemProperties[3]));
            }
        } catch (Exception e) {
            System.out.println("There is a problem with the file.");
            System.out.println(e.getMessage());
        }

    }

    public List<String> displayStock() {
        List<String> itemListStrings = new ArrayList<>();
        Set<String> keys = itemList.keySet();
        for (String s : keys) {
            Item i = itemList.get(s);
            StringBuffer itemString = new StringBuffer();
            DecimalFormat df = new DecimalFormat("###.00");
            itemString.append(i.getName() + " | ");
            itemString.append(i.getSlot() + " | ");
            itemString.append("$" + df.format((double) i.getPrice() / 100) + " | ");
            if (i.getStock() == 0)
                itemString.append("SOLD OUT");
            else
                itemString.append(i.getStock());
            itemListStrings.add(itemString.toString());
        }
        return itemListStrings;
    }

    public void printSalesReport() {
        List<String> itemListStrings = new ArrayList<>();
        Set<String> keys = itemList.keySet();
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-uuuu_hh:mm_a");
        File report = new File("VendoMaticSales" + formatter.format(dateTime) + ".csv");
        try (PrintWriter pw = new PrintWriter(new FileWriter(report, true))) {
            for (String s : keys) {
                Item i = itemList.get(s);
                String lineToPrint = i.getName() + "|" + i.getSales();
                pw.println(lineToPrint);
                i.resetSalesCounter();
            }
            Transaction moneyFormatter = new Transaction();
            pw.println("\n**TOTAL SALES** " + moneyFormatter.getBalanceInDollars(totalSales));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public String purchase(Transaction t, String slot) {
        System.out.println(slot);
        if(!itemList.containsKey(slot.toUpperCase())){
            return "Item not found";
        }
        Item currentItem = itemList.get(slot.toUpperCase());
        String test = "Gum";
        boolean check = test.equals(currentItem.getType());
        if (currentItem.getType().equals("Gum")) {
            Gum tempGum = new Gum(currentItem.getSlot(), currentItem.getName(), currentItem.getPrice());
            String testString = tempGum.getSound();
            currentItem.setSound(tempGum.getSound());
        } else if (currentItem.getType().equals("Beverage")) {
            Beverage tempBev = new Beverage(currentItem.getSlot(), currentItem.getName(), currentItem.getPrice());
            currentItem.setSound(tempBev.getSound());
        } else if (currentItem.getType().equals("Candy")) {
            Candy tempCandy = new Candy(currentItem.getSlot(), currentItem.getName(), currentItem.getPrice());
            currentItem.setSound(tempCandy.getSound());
        } else if (currentItem.getType().equals("Chip")) {
            Chip tempChip = new Chip(currentItem.getSlot(), currentItem.getName(), currentItem.getPrice());
            currentItem.setSound(tempChip.getSound());
        }

        if (currentItem.getStock() == 0) {
            return "There are no more of this item left to purchase.";
        }
        if (currentItem.getPrice() < t.getBalance()) {
            t.printTransaction(currentItem.getName(), currentItem.getSlot(), currentItem.getPrice());
            currentItem.decrementStock();
            currentItem.incrementSales();
            totalSales += currentItem.getPrice();
            t.setBalance(t.getBalance() - currentItem.getPrice());
            return "You have purchased 1 " + currentItem.getName() + " at $" + String.valueOf(Double.valueOf(currentItem.getPrice())/100
            ) +  ". You have $" + String.valueOf(t.getBalance()/100) + " remaining. " + currentItem.getSound();

        } else {
            return "The cost of this item exceeds you current balance.";
        }

    }

    public Item getItem(String slot) {
        return itemList.get(slot);
    }
}