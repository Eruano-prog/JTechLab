package Accounts;

import Transaction.OperationResult;

public class AccountDebet implements IAccount {
    private Double balance = 0.0;
    private Double currentPercentage;
    private Double accumulatedBalance = 0.0;

    public AccountDebet(double percentage) {
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

        balance -= value;
        return OperationResult.Success;
    }

    @Override
    public OperationResult depositMoney(Double value) {
        balance += value;
        return OperationResult.Success;
    }

    @Override
    public void countPercentage() {
        accumulatedBalance += (balance * currentPercentage) / 100;
    }

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
