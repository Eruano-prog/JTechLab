package Accounts;

import Transaction.OperationResult;

public interface IAccount {
    Double getAccountBalance();
    void setAccountBalance(Double amount);
    OperationResult withdrawMoney(Double value);
    OperationResult depositMoney(Double value);
    void countPercentage();
    void checkOut();
    void changeConditions(double newPercentage);
    Integer getHostID();
    Integer getID();
}
