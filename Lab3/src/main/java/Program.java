import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Program {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                BLLConfiguration.class,
                ControllerConfiguration.class,
                DLLConfiguration.class);

        
    }
}
