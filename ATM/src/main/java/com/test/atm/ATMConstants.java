package com.test.atm;

// A class to hold ATM Constant Variables.
public class ATMConstants {

    private static final int MAX_DEPOSIT_PER_DAY = 150000;
    private static final int MAX_DEPOSIT_PER_TXN = 40000;
    private static final int MAX_DEP_FREQ = 4;
    private static final int MAX_WITHDRAW_PER_DAY = 50000;
    private static final int MAX_WITHDRAW_PER_TXN = 20000;
    private static final int MAX_WITHDRAW_FREQ = 3;

    // Returns the Maximum Allowable Deposits per day.
    public static int getMAX_DEPOSIT_PER_DAY() {
        return MAX_DEPOSIT_PER_DAY;
    }

    // Returns the Maximum Allowable deposits per Transaction.
    public static int getMAX_DEPOSIT_PER_TXN() {
        return MAX_DEPOSIT_PER_TXN;
    }

    // Returns the Maximum number of Allowable Deposits transactions per Day.
    public static int getMAX_DEP_FREQ() {
        return MAX_DEP_FREQ;
    }

    // Returns the Maximum Allowable withdrawal per day.
    public static int getMAX_WITHDRAW_PER_DAY() {
        return MAX_WITHDRAW_PER_DAY;
    }

    // Returns the Maximum Allowable withdrawal per .
    public static int getMAX_WITHDRAW_PER_TXN() {
        return MAX_WITHDRAW_PER_TXN;
    }

    // Returns the Maximum number of Allowable Withdrawals transactions per Day.
    public static int getMAX_WITHDRAW_FREQ() {
        return MAX_WITHDRAW_FREQ;
    }
}
