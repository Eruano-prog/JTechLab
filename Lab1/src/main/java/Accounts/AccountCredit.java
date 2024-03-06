package Accounts;

import Transaction.OperationResult;

public class AccountCredit implements IAccount {
    private Double balance = 0.0;
    private Integer limit;
    private Double commission;
    private Double accumulatedBalance = 0.0;

    AccountCredit(int limit, double commission) {
        this.limit = limit;
        this.commission = commission;
    }

    @Override
    public Double getAccountBalance() {
        return balance;
    }

    @Override
    public OperationResult withdrawMoney(int value) {
        if (balance - value < limit) {
            return OperationResult.NotEnoughMoney;
        }

        balance = balance - value;
        return OperationResult.Success;
    }

    @Override
    public OperationResult depositMoney(Double value) {
        balance += value;
        return OperationResult.Success;
    }

    @Override
    public void countPercentage() {}

    @Override
    public void checkOut() {
        balance += accumulatedBalance;
    }

    @Override
    public void changeConditions(double newPercentage) {
        if (newPercentage < 0) return;
        currentPercentage = newPercentage;
    }
}
