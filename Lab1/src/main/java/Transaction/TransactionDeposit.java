package Transaction;

import Accounts.IAccount;

/**
 * Transaction that allows to deposit to some account.
 * To let it act, you should call centralBank method.
 */
public class TransactionDeposit implements ITransaction {

    private IAccount account;
    private Double amount;
    private TransactionStatus status;

    public TransactionDeposit(IAccount account, Double amount) {
        this.account = account;
        this.amount = amount;
        status = TransactionStatus.PROCESSING;
    }

    @Override
    public void act() {
        OperationResult result = account.depositMoney(amount);

        if (result == OperationResult.SUCCESS){
            status = TransactionStatus.FINISHED;
            return;
        }

        status = TransactionStatus.CANCELED;
    }

    @Override
    public void cancel() {
        if (status != TransactionStatus.FINISHED){
            return;
        }

        account.setAccountBalance(account.getAccountBalance() - amount);
        status = TransactionStatus.CANCELED;
    }

    @Override
    public TransactionStatus getStatus() {
        return status;
    }
}
