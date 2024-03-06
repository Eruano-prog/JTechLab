package Banks;

import java.util.ArrayList;
import java.util.stream.Stream;

public class CentralBank {
    ArrayList<ICommonBank> registredBanks = new ArrayList<>();

    public void registreBank(){

    }

    public void intiCalculation(){
        Stream<ICommonBank> stream = registredBanks.stream();
        stream.forEach(x -> x.countPercentages());
    }
}
