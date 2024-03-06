package Accounts;

import Transaction.OperationResult;

public interface IAccount {
    Double getAccountBalance();
    OperationResult withdrawMoney(int value);
    OperationResult depositMoney(Double value);
    void countPercentage();
    void checkOut();
    void changeConditions(double newPercentage);
    void withName(String name);
    void
}
