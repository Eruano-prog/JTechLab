package Transaction;

import Accounts.IAccount;

/**
 * Transaction that allows to withdraw from some account.
 * To let it act, you should call centralBank method.
 */
public class TransactionWithdraw implements ITransaction {

    private IAccount account;
    private Double amount;
    private TransactionStatus status;

    public TransactionWithdraw(IAccount account, Double amount){
        this.account = account;
        this.amount = amount;
    }
    @Override
    public void act() {
        var result = account.withdrawMoney(amount);

        if (result == OperationResult.Success){
            status = TransactionStatus.Finished;
            return;
        }

        status = TransactionStatus.Canceled;
    }

    @Override
    public void cancel() {
        if (status != TransactionStatus.Finished){
            return;
        }

        account.setAccountBalance(account.getAccountBalance() + amount);
        status = TransactionStatus.Canceled;
    }

    @Override
    public TransactionStatus getStatus() {
        return status;
    }
}
