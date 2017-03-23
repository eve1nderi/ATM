package com.test.atm;

import com.test.atm.bo.impl.TransactionManagerImpl;
import com.test.atm.model.ServiceResponse;
import java.io.Console;

/**
 *
 * @author wanderi
 */
public class ATMDriver {

    public static void main(String[] args) {

        boolean shouldRun = true;

        Console console = System.console();
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        } else {
            while (shouldRun) {
                int menu;
                displayMenu();

                //reads input from user
                String command = console.readLine("\n> ");

                //if user input is 1, display menu for getting balance
                if (command.equalsIgnoreCase("1")) {
                    menu = 1;
                    processMenu(console, menu);
                } //if user input is 2, display menu for getting Deposit
                else if (command.equalsIgnoreCase("2")) {
                    menu = 2;
                    processMenu(console, menu);
                } //if user input is 3, display menu for getting Withdraw
                else if (command.equalsIgnoreCase("3")) {
                    menu = 3;
                    processMenu(console, menu);
                } //if user input is 4, display menu for Quit
                else if (command.equalsIgnoreCase("4")) {
                    System.out.println("QUIT\n\n"
                            + "Are you sure you want to quit? (yes/no)");
                    String quit = console.readLine("\n> ");
                    if (quit.equalsIgnoreCase("yes")) {
                        System.exit(0);
                    }
                } else if (command.equalsIgnoreCase("menu")) {
                    displayMenu();
                } else {
                    System.out.println("Command not supported. Type 'menu' then press enter to see available options.");
                }
            }
        }
    }

    private static void displayMenu() {
        System.out.println(
                "1. Balance \n"
                + "2. Deposit \n"
                + "3. Withdrawal \n"
                + "4. Quit \n\n"
                + "Enter Menu Option");
    }

    private static void processMenu(Console console, int choice) {
        TransactionManagerImpl transactionManagerImpl = new TransactionManagerImpl();
        if (choice == 1) {
            ServiceResponse serviceResponse = transactionManagerImpl.checkBalance();
            if (serviceResponse.getCode() == 200) {
                System.out.println(serviceResponse.getResponse());
            } else {
                System.out.println("Error: " + serviceResponse.getResponse());
            }
            System.out.println("\n*************************************************************************************************************\n");
        }

        if (choice == 2) {
            String value = console.readLine("\nEnter amount and press enter (or type menu and press enter to go back to main menu) > ");
            if (value.equalsIgnoreCase("menu")) {
                displayMenu();
            } else {
                int amount = getAmount(value);
                if (amount > 0) {
                    ServiceResponse serviceResponse = transactionManagerImpl.deposit(amount);
                    if (serviceResponse.getCode() == 200) {
                        System.out.println(serviceResponse.getResponse());
                    } else {
                        System.out.println("Error: " + serviceResponse.getResponse());
                    }
                } else {
                    System.out.println("Error: Invalid input");
                }
            }
            System.out.println("\n*************************************************************************************************************\n");
        }

        if (choice == 3) {
            String value = console.readLine("\nEnter amount and press enter (or type menu and press enter to go back to main menu) > ");
            if (value.equalsIgnoreCase("menu")) {
                displayMenu();
            } else {
                int amount = getAmount(value);
                if (amount > 0) {
                    ServiceResponse serviceResponse = transactionManagerImpl.withdraw(amount);
                    if (serviceResponse.getCode() == 200) {
                        System.out.println(serviceResponse.getResponse());
                    } else {
                        System.out.println("Error: " + serviceResponse.getResponse());
                    }
                } else {
                    System.out.println("Error: Invalid input");
                }
            }
            System.out.println("\n*************************************************************************************************************\n");
        }
    }

    private static int getAmount(String value) {
        int converted = -1;
        try {
            converted = Integer.parseInt(value);
        } catch (Exception ex) {
        }

        return converted;
    }
}
