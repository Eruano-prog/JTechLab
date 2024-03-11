package Transaction;

import Accounts.IAccount;
import com.sun.net.httpserver.Authenticator;

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
        status = TransactionStatus.Processing;
    }

    @Override
    public void act() {
        OperationResult result = account.depositMoney(amount);

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

        account.setAccountBalance(account.getAccountBalance() - amount);
        status = TransactionStatus.Canceled;
    }

    @Override
    public TransactionStatus getStatus() {
        return status;
    }
}
