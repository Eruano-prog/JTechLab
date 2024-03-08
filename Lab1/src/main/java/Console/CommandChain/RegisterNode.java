package Console.CommandChain;

public class RegisterNode extends ChainNode{
    @Override
    public void act(OrderContext context) {
        if (context.commandId != 1){
            next(context);
            return;
        }


    }
}
