//
package com.test.atm.model;

public class AccountTransactions {

    private double currentBalance = 0.0;
    private double sumDepositedToday = 0;
    private int depositsDoneToday = 0;
    private double sumWithdrawnToday = 0;
    private int withdrawalsDoneToday = 0;

    private AccountTransactions() {
    }

    public static AccountTransactions getInstance() {
        return AccountTransactionsHolder.INSTANCE;
    }

    private static class AccountTransactionsHolder {

        private static final AccountTransactions INSTANCE = new AccountTransactions();
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getSumDepositedToday() {
        return sumDepositedToday;
    }

    public void setSumDepositedToday(double sumDepositedToday) {
        this.sumDepositedToday = sumDepositedToday;
    }

    public int getDepositsDoneToday() {
        return depositsDoneToday;
    }

    public void setDepositsDoneToday(int depositsDoneToday) {
        this.depositsDoneToday = depositsDoneToday;
    }

    public double getSumWithdrawnToday() {
        return sumWithdrawnToday;
    }

    public void setSumWithdrawnToday(double sumWithdrawnToday) {
        this.sumWithdrawnToday = sumWithdrawnToday;
    }

    public int getWithdrawalsDoneToday() {
        return withdrawalsDoneToday;
    }

    public void setWithdrawalsDoneToday(int withdrawalsDoneToday) {
        this.withdrawalsDoneToday = withdrawalsDoneToday;
    }
}
