import Accounts.AccountDebet;
import Accounts.IAccount;
import Banks.CentralBank;
import Banks.CommonBank;
import Users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    void testRegisteringBank(){
        CentralBank centralBank = new CentralBank();

        centralBank.registerBank("Sber");

        Assertions.assertEquals(centralBank.registredBanks.size(), 1);
    }

    @Test
    void testRegisteringAccount(){
        CommonBank bank = new CommonBank("Sber");

        IAccount account = new AccountDebet(5, 0, 0);
        bank.registerAccount(account);

        Assertions.assertEquals(bank.getAccountById(0), account);
    }

    @Test
    void testRegisteringUser(){
        var bank = new CommonBank("Sber");
        var user = User.builder()
                .Id(0)
                .name("fedor")
                .secondName("golovlev")
                .address("esnina")
                .passportNumber("123")
                .build();

        bank.registerUser(user);

        Assertions.assertEquals(bank.getUserByName("fedor"), user);
    }
}
