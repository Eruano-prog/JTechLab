package Banks;

import Accounts.IAccount;
import Users.User;

public interface ICommonBank {
    void countPercentages();
    ICommonBank registerUser(User user);
    ICommonBank registerAccount(IAccount account);
    User getUserByName(String name);
    public IAccount getAccountById(Integer id);
    String getName();
}
