package Banks;

import Accounts.IAccount;
import Users.User;

import java.util.ArrayList;

/**
 * Class that represents bank that stores information about users
 */
public class CommonBank implements ICommonBank{
    String name;
    ArrayList<IAccount> accounts;
    ArrayList<User> users;

    public CommonBank(String name){
        this.name = name;
        accounts = new ArrayList<>();
        users = new ArrayList<>();
    }

    @Override
    public ICommonBank registerUser(User user){
        users.add(user);
        return this;
    }

    @Override
    public ICommonBank registerAccount(IAccount account){
        accounts.add(account);
        return this;
    }

    @Override
    public User getUserByName(String name){
        var stream = users.stream();

        return stream.filter(x -> x.getName() == name)
                .findFirst()
                .get();
    }

    @Override
    public IAccount getAccountById(Integer id){
        var stream = accounts.stream();

        return stream.filter(x -> x.getID() == id)
                .findFirst()
                .get();
    }

    @Override
    public void countPercentages() {
        var stream = accounts.stream();
        stream.forEach(x -> x.countPercentage());
    }
    @Override
    public String getName() {
        return name;
    }
}
