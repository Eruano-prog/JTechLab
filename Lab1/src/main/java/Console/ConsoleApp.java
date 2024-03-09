package Console;

import Banks.CentralBank;
import Banks.CommonBank;
import Banks.ICommonBank;
import Console.CommandChain.*;
import Users.User;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] input){
        Scanner in = new Scanner(System.in);
        int cmd = 0;
        var bank = new CentralBank();

        var chain = new RegisterNode();
        chain.setNext(new WithdrawNode())
                .setNext(new DepositNode())
                .setNext(new TransferNode());

        while (cmd != 5) {
            System.out.println("Choose operation to act\n1. Register\n2. Withdraw\n3. Deposit\n4. Transfer\n5. Exit");
            cmd = in.nextInt();

            var context = new OrderContext(cmd, bank);

            chain.act(context);
        }
    }

    private CentralBank InitData(){
        CentralBank centralBank = new CentralBank();

        var user = User.builder()
                .name("Fedor")
                .Id(0)
                .secondName("Golovlev")
                .address("Esenina")
                .passportNumber("123")
                        .build();

        ICommonBank bank = new CommonBank("Sber")
                .registerUser(user);


        return centralBank;
    }
}
