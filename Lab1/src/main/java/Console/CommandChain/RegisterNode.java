package Console.CommandChain;

import Banks.ICommonBank;
import Users.User;

import java.util.Scanner;

public class RegisterNode extends ChainNode{
    @Override
    public void act(OrderContext context) {
        if (context.commandId != 1){
            next(context);
            return;
        }

        Scanner in = new Scanner(System.in);


        System.out.println("Enter you`re bank");
        String bankName = in.nextLine();

        System.out.println("Enter your Name");
        String name = in.nextLine();
        System.out.println("Enter your second name");
        String secondName = in.nextLine();
        System.out.println("Enter your address");
        String address = in.nextLine();
        System.out.println("Enter tour passport number");
        String passport = in.nextLine();

        address = address.isEmpty() ? null : address;
        passport = passport.isEmpty() ? null : passport;

        User user = User.builder()
                .name(name)
                .secondName(secondName)
                .address(address)
                .passportNumber(passport)
                .build();

        ICommonBank bank = context.centralBank.getBankByName(bankName);

        bank.registerUser(user);
    }
}
