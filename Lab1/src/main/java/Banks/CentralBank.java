package Banks;

import Transaction.ITransaction;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Class that controls other banks and accounts.
 * It stores registered banks and history of transactions.
 */
@NoArgsConstructor
public class CentralBank {
    public final ArrayList<ICommonBank> registredBanks = new ArrayList<>();
    /**
     * Stores information about transactions of last session
     */
    public final ArrayList<ITransaction> transactions = new ArrayList<>();

    public void registerBank(String bankName){
        registredBanks.add(new CommonBank(bankName));
    }
    public void registerBank(ICommonBank bank){
        registredBanks.add(bank);
    }

    public void registerTransaction(ITransaction transaction){
        transactions.add(transaction);
        transaction.act();
    }

    public void intiCalculation(){
        Stream<ICommonBank> stream = registredBanks.stream();
        stream.forEach(x -> x.countPercentages());
    }

    public ICommonBank getBankByName(String name){
        Stream<ICommonBank> stream = registredBanks.stream();
        ICommonBank bank = stream.filter(x -> x.getName() == name)
                .findFirst().get();
        return bank;
    }
}
