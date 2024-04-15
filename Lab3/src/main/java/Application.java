//import Controllers.ControllerConfiguration;
//import Repositories.DLLConfiguration;
//import Services.BLLConfiguration;
//import org.apache.catalina.Context;
//import org.apache.catalina.LifecycleException;
//import org.apache.catalina.startup.Tomcat;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//
//import java.io.File;
//
//public class Application {
//
//    public static void main(String[] args) throws LifecycleException {
//
//        // Создаем контекст Spring для нашего приложения
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(
//                BLLConfiguration.class,
//                ControllerConfiguration.class,
//                DLLConfiguration.class
//        );
//        context.refresh();
//
//        // Создаем диспетчер сервлетов Spring MVC
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
//
//        // Создаем и настраиваем контейнер сервлетов
//        Tomcat tomcat = new Tomcat();
//        tomcat.setPort(8080);
//        String webAppPath = new File(".").getAbsolutePath();
//        Context servletContext = tomcat.addContext("", webAppPath);
//
//
//        // Добавляем диспетчер сервлетов в контейнер сервлетов
//        Tomcat.addServlet(servletContext, "dispatcherServlet", dispatcherServlet);
//        servletContext.addServletMappingDecoded("", "dispatcherServlet");
//
//        // Запускаем веб-контейнер
//        tomcat.start();
//        tomcat.getServer().await();
//    }
//}
