package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {
    private PrintWriter pw;
    private Scanner keyboard;

    public Menu(InputStream input, OutputStream output) {
        this.keyboard = new Scanner(input);
        this.pw = new PrintWriter(output);
}

    public Object getChoiceFromOptions(Object[] options) {
        Object choice = null;
        while(choice == null) {
            displayMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;

    }
    private Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = keyboard.nextLine();

        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            System.out.println("This is not an option");
            System.out.println(e.getMessage());
        }

        if (choice == null) {
            pw.println("*** " + userInput + " is not a valid option ***");
        }
        return choice;
    }

    private void displayMenuOptions(Object[] options) {
        pw.println();
        for(int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            pw.println(optionNum + ") " + options[i]);
        }
        pw.println("Please choose an option >>> ");
        pw.flush();

        }
    }