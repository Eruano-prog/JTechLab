package Console.CommandChain;

public abstract class ChainNode implements IChainNode{
    IChainNode next;
    @Override
    public IChainNode setNext(IChainNode node) {
        next = node;
        return node;
    }
    @Override
    public void next(OrderContext context){
        if (next == null) return;
        next.act(context);
    }
}
