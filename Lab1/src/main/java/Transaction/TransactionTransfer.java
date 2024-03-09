package Transaction;

import Accounts.IAccount;

/**
 * Transaction that allows to transfer from one to another account.
 * To let it act, you should call centralBank method.
 */
public class TransactionTransfer implements ITransaction {
    private IAccount accountFrom, accountTo;
    private Double amount;
    private TransactionStatus status;

    public TransactionTransfer(IAccount accountFrom, IAccount accountTo, Double amount) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.status = TransactionStatus.PROCESSING;
    }

    @Override
    public void act() {
        OperationResult resultFrom = accountFrom.withdrawMoney(amount);
        if (resultFrom != OperationResult.SUCCESS){
            status = TransactionStatus.CANCELED;
            return;
        }

        OperationResult resultTo = accountTo.depositMoney(amount);
        if (resultTo != OperationResult.SUCCESS){
            accountFrom.setAccountBalance(accountFrom.getAccountBalance() + amount);
            status = TransactionStatus.CANCELED;
            return;
        }

        status = TransactionStatus.FINISHED;
    }

    @Override
    public void cancel() {
        if (status != TransactionStatus.FINISHED){
            return;
        }

        accountFrom.setAccountBalance(accountFrom.getAccountBalance() + amount);
        accountTo.setAccountBalance(accountTo.getAccountBalance() - amount);

        status = TransactionStatus.CANCELED;
    }

    @Override
    public TransactionStatus getStatus() {
        return status;
    }
}
