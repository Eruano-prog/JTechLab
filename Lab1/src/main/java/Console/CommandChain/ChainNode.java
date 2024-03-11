package Console.CommandChain;

public abstract class ChainNode implements IChainNode{
    IChainNode next;
    @Override
    public void setNext(IChainNode node) {
        next = node;
    }
    @Override
    public void next(OrderContext context){
        if (next == null) return;
        next.act(context);
    }
}
