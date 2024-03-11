package Console;

import Banks.CentralBank;
import Banks.CommonBank;
import Banks.ICommonBank;
import Users.User;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] input){
        Scanner in = new Scanner(System.in);
        int cmd = 0;

        while (cmd != 5) {
            System.out.println("Choose operation to act\n1. Register\n2. Withdraw\n3. Deposit\n4. Transfer\n5. Exit");
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
