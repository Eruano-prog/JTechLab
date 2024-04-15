import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

import Controllers.ControllerConfiguration;
import Repositories.DLLConfiguration;
import Services.BLLConfiguration;

public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Создаем контекст Spring для нашего приложения
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(
                BLLConfiguration.class,
                ControllerConfiguration.class,
                DLLConfiguration.class
        );
        context.refresh();

        servletContext.addListener(new ContextLoaderListener(context));

        // Создаем диспетчер сервлетов Spring MVC
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

        // Регистрируем диспетчер сервлетов
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
