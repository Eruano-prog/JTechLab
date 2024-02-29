package Transaction;

public interface ITransaction {
    void act();
    void cancel();
    TransactionStatus getStatus();
}
