package com.test.atm.bo;

import com.test.atm.model.ServiceResponse;

//contains definition of methods checkBalance(), deposit() and withdraw()
public interface TransactionManager {

    public ServiceResponse checkBalance();

    public ServiceResponse deposit(int amount);

    public ServiceResponse withdraw(int amount);
}
