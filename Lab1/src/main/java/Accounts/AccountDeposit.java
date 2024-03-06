package Accounts;

import Transaction.OperationResult;

import java.util.Date;

public class AccountDeposit implements IAccount{
    private Double balance = 0.0;
    private Double currentPercentage;
    private Double accumulatedBalance = 0.0;
    private Date expireDate;

    public AccountDeposit(double percentage, ) {
        currentPercentage = percentage;
    }

    @Override
    public Double getAccountBalance() {
        return balance;
    }

    @Override
    public OperationResult withdrawMoney(int value) {
        if (balance - value < 0) {
            return OperationResult.NotEnoughMoney;
        }
        if ()

        balance -= value;
        return OperationResult.Success;
    }

    @Override
    public OperationResult depositMoney(Double value) {
        return null;
    }

    @Override
    public void countPercentage() {

    }

    @Override
    public void checkOut() {

    }

    @Override
    public void changeConditions(double newPercentage) {

    }
}
