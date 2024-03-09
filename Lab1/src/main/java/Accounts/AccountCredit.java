package Accounts;

import Transaction.OperationResult;

public class AccountCredit implements IAccount {
    private Double balance = 0.0;
    private Integer limit;
    private Double commission;
    private Double accumulatedBalance = 0.0;
    private Integer host;
    private Integer id;

    AccountCredit(int limit, double commission, int host, int id) {
        this.limit = limit;
        this.commission = commission;
        this.host = host;
        this.id = id;
    }

    @Override
    public Double getAccountBalance() {
        return balance;
    }

    @Override
    public void setAccountBalance(Double amount) {
        balance = amount;
    }

    @Override
    public OperationResult withdrawMoney(Double value) {
        if (balance - value < limit) {
            return OperationResult.NOT_ENOUGH_MONEY;
        }

        balance = balance - value;
        return OperationResult.SUCCESS;
    }

    @Override
    public OperationResult depositMoney(Double value) {
        balance += value;
        return OperationResult.SUCCESS;
    }

    @Override
    public void countPercentage() {
        accumulatedBalance += commission;
    }

    @Override
    public void checkOut() {
        balance -= accumulatedBalance;
        accumulatedBalance = 0.0;
    }

    @Override
    public void changeConditions(double newPercentage) {
        if (newPercentage < 0) return;
        commission = newPercentage;
    }

    @Override
    public Integer getHostID() {
        return host;
    }

    @Override
    public Integer getID() {
        return id;
    }
}
