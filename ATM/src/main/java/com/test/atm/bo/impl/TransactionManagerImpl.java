package com.test.atm.bo.impl;

import com.test.atm.ATMConstants;
import com.test.atm.bo.TransactionManager;
import com.test.atm.model.AccountTransactions;
import com.test.atm.model.ServiceResponse;

//class inheriting from interface TransactionManager and implementing the functions in the interface.
public class TransactionManagerImpl implements TransactionManager {

    // Use Override statement to implement the functions in the interface
    @Override
    // Returns the account Balance

    public ServiceResponse checkBalance() {
        ServiceResponse serviceResponse = null;

        AccountTransactions accountTransactions = AccountTransactions.getInstance();
        serviceResponse = new ServiceResponse(200, "Success", "BALANCE \n" + accountTransactions.getCurrentBalance());

        return serviceResponse;
    }

    @Override
    // Enables user to Deposit amount and checks all validations when doing a deposit.

    public ServiceResponse deposit(int amount) {
        ServiceResponse serviceResponse = null;

        AccountTransactions accountTransactions = AccountTransactions.getInstance();

        //check if amount to be deposited exceeds the maximum allowable per transaction
        if (amount <= ATMConstants.getMAX_DEPOSIT_PER_TXN()) {

            //get total sum deposited today
            double sumDepositedToday = accountTransactions.getSumDepositedToday();

            //get number of deposits done today
            int depositsDoneToday = accountTransactions.getDepositsDoneToday();

            //check if number of deposits done today is less than the maximum allowable number of deposits
            if (depositsDoneToday < ATMConstants.getMAX_DEP_FREQ()) {
                //check if sum of deposits done today is less than the maximum allowable total
                if (sumDepositedToday <= ATMConstants.getMAX_DEPOSIT_PER_DAY()) {
                    //check if amount to be deposited will exceed the total allowable deposit
                    if ((sumDepositedToday + amount) <= ATMConstants.getMAX_DEPOSIT_PER_DAY()) {
                        //add amount to be deposited to the current account balance
                        accountTransactions.setCurrentBalance(accountTransactions.getCurrentBalance() + amount);

                        //add deposited amount to sum deposited today
                        accountTransactions.setSumDepositedToday(accountTransactions.getSumDepositedToday() + amount);

                        //increment the number of deposits done today by 1
                        accountTransactions.setDepositsDoneToday(accountTransactions.getDepositsDoneToday() + 1);

                        serviceResponse = new ServiceResponse(200, "Success", "Deposited " + amount);
                    } else {
                        serviceResponse = new ServiceResponse(500, "Error", "Depositing " + amount + " Will Exceed Maximum Amount Depositable Per Day. "
                                + "Try Amount Less Or Equal To " + (ATMConstants.getMAX_DEPOSIT_PER_DAY() - sumDepositedToday));
                    }
                } else {
                    serviceResponse = new ServiceResponse(500, "Error", "Exceeded Maximum Deposits Per Day");
                }
            } else {
                serviceResponse = new ServiceResponse(500, "Error", "Exceeded Maximum Deposit Frequency For Today");
            }
        } else {
            serviceResponse = new ServiceResponse(500, "Error", "The Amount Exceeds Maximum Amount Depositable Per Transaction");
        }

        return serviceResponse;
    }

    @Override

    // Enables user to Withdraw amounts and checks all validations when doing withdrawals.
    public ServiceResponse withdraw(int amount) {
        ServiceResponse serviceResponse = null;

        AccountTransactions accountTransactions = AccountTransactions.getInstance();

        if (amount <= ATMConstants.getMAX_WITHDRAW_PER_TXN()) {

            double sumWithdrawnToday = accountTransactions.getSumWithdrawnToday();
            int withdrawalsDoneToday = accountTransactions.getWithdrawalsDoneToday();

            if (withdrawalsDoneToday < ATMConstants.getMAX_WITHDRAW_FREQ()) {
                if (sumWithdrawnToday <= ATMConstants.getMAX_DEPOSIT_PER_DAY()) {
                    if ((sumWithdrawnToday + amount) <= ATMConstants.getMAX_WITHDRAW_PER_DAY()) {
                        if (accountTransactions.getCurrentBalance() >= amount) {
                            accountTransactions.setCurrentBalance(accountTransactions.getCurrentBalance() - amount);
                            accountTransactions.setSumWithdrawnToday(accountTransactions.getSumWithdrawnToday() + amount);
                            accountTransactions.setWithdrawalsDoneToday(accountTransactions.getWithdrawalsDoneToday() + 1);

                            serviceResponse = new ServiceResponse(200, "Success", "Withdrawn " + amount);
                        } else {
                            serviceResponse = new ServiceResponse(500, "Error", "Insufficient Account Balance");
                        }
                    } else {
                        serviceResponse = new ServiceResponse(500, "Error", "Withdrawing " + amount + " Will Exceed Maximum Amount Withdrawable Per Day. "
                                + "Try Amount Less Or Equal To " + (ATMConstants.getMAX_WITHDRAW_PER_DAY() - sumWithdrawnToday));
                    }
                } else {
                    serviceResponse = new ServiceResponse(500, "Error", "Exceeded Maximum Withdrawals Per Day");
                }
            } else {
                serviceResponse = new ServiceResponse(500, "Error", "Exceeded Maximum Withdrawal Frequency For Today");
            }
        } else {
            serviceResponse = new ServiceResponse(500, "Error", "The Amount Exceeds Maximum Amount Withdrawable Per Transaction");
        }

        return serviceResponse;
    }
}
