package Console.CommandChain;

public interface IChainNode {
    void setNext(IChainNode node);
    void next(OrderContext context);
    void act(OrderContext answer);
}
