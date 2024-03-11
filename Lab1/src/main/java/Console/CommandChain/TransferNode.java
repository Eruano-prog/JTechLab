package Console.CommandChain;

import Accounts.IAccount;
import Banks.ICommonBank;
import Transaction.ITransaction;
import Transaction.TransactionDeposit;
import Transaction.TransactionTransfer;

import java.util.Scanner;

public class TransferNode extends ChainNode{
    @Override
    public void act(OrderContext context) {
        if (context.commandId != 4){
            next(context);
            return;
        }

        Scanner in = new Scanner(System.in);
        System.out.println("Enter you`re bank");
        String bankName = in.nextLine();
        System.out.println("Enter you`re account id from where you want to transfer");
        Integer accountFromId = in.nextInt();
        System.out.println("Enter you`re account id where you want to transfer");
        Integer accountToId = in.nextInt();
        System.out.println("Enter deposit amount");
        Double amount = in.nextDouble();



        ICommonBank bank = context.centralBank.getBankByName(bankName);
        IAccount accountFrom = bank.getAccountById(accountFromId);
        IAccount accountTo = bank.getAccountById(accountToId);

        ITransaction transaction = new TransactionTransfer(accountFrom, accountTo, amount);

        context.centralBank.registerTransaction(transaction);
    }
}
