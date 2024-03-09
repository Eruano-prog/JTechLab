package Accounts;

import Transaction.OperationResult;

public class AccountDeposit implements IAccount{
    private Integer host;
    private Double balance = 0.0;
    private Double currentPercentage;
    private Double accumulatedBalance = 0.0;
    private Integer periodsLeft;
    private Integer id;

    public AccountDeposit(double percentage, int periods, int host, int id) {
        currentPercentage = percentage;
        periodsLeft = periods;
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
        if (balance - value < 0) {
            return OperationResult.NOT_ENOUGH_MONEY;
        }
        if (periodsLeft > 0){
            return OperationResult.INVALID_OPERATION;
        }

        balance -= value;
        return OperationResult.SUCCESS;
    }

    @Override
    public OperationResult depositMoney(Double value) {
        return null;
    }

    @Override
    public void countPercentage() {
        accumulatedBalance += (balance*currentPercentage)/365;
        periodsLeft--;
    }

    @Override
    public void checkOut() {
        balance += accumulatedBalance;
        accumulatedBalance = 0.0;
    }

    @Override
    public void changeConditions(double newPercentage) {
        if (newPercentage < 0) return;

        currentPercentage = newPercentage;
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
