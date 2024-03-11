package Console.CommandChain;

import Banks.CentralBank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderContext {
    public final int commandId;
    public final CentralBank centralBank;
}
