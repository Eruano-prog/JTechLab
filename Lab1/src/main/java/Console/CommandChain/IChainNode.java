package Console.CommandChain;

public interface IChainNode {
    IChainNode setNext(IChainNode node);
    void next(OrderContext context);
    void act(OrderContext answer);
}
