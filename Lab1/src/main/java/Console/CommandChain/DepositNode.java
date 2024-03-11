package Console.CommandChain;

import Accounts.IAccount;
import Banks.ICommonBank;
import Transaction.ITransaction;
import Transaction.TransactionDeposit;

import java.util.Scanner;

public class DepositNode extends ChainNode{
    @Override
    public void act(OrderContext context) {
        if (context.commandId != 3){
            next(context);
            return;
        }

        Scanner in = new Scanner(System.in);
        System.out.println("Enter you`re bank");
        String bankName = in.nextLine();
        System.out.println("Enter you`re account id");
        Integer accountId = in.nextInt();
        System.out.println("Enter deposit amount");
        Double amount = in.nextDouble();

        ICommonBank bank = context.centralBank.getBankByName(bankName);
        IAccount account = bank.getAccountById(accountId);

        ITransaction transaction = new TransactionDeposit(account, amount);

        context.centralBank.registerTransaction(transaction);
    }
}
