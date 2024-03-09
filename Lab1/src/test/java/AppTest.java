import Accounts.AccountDebet;
import Accounts.IAccount;
import Banks.CentralBank;
import Banks.CommonBank;
import Transaction.TransactionDeposit;
import Transaction.TransactionWithdraw;
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

    @Test
    void testDepositTransaction(){
        CentralBank bank = new CentralBank();

        var user = User.builder()
                .Id(0)
                .name("fedor")
                .secondName("golovlev")
                .address("esnina")
                .passportNumber("123")
                .build();

        var account = new AccountDebet(5, 0, 0);

        bank.registerBank(new CommonBank("Sber").registerUser(user));
        bank.getBankByName("Sber").registerAccount(account);

        var transaction = new TransactionDeposit(account, 200.00);

        bank.registerTransaction(transaction);

        Assertions.assertEquals(account.getAccountBalance(), 200);
    }

    @Test
    void testWithdrawTransaction(){
        CentralBank bank = new CentralBank();

        var user = User.builder()
                .Id(0)
                .name("fedor")
                .secondName("golovlev")
                .address("esnina")
                .passportNumber("123")
                .build();

        var account = new AccountDebet(5, 0, 0);

        bank.registerBank(new CommonBank("Sber").registerUser(user));
        bank.getBankByName("Sber").registerAccount(account);

        var transactionDeposit = new TransactionDeposit(account, 400.00);

        bank.registerTransaction(transactionDeposit);

        var transactionWithdraw = new TransactionWithdraw(account, 100.00);

        bank.registerTransaction(transactionWithdraw);

        Assertions.assertEquals(account.getAccountBalance(), 300);
    }
}
