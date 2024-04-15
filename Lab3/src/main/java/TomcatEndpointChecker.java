//import javax.management.MBeanServer;
//import javax.management.ObjectName;
//import java.lang.management.ManagementFactory;
//import java.util.Set;
//
//public class TomcatEndpointChecker {
//
//    public static void main(String[] args) throws Exception {
//        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
//        ObjectName objectName = new ObjectName("Catalina:type=Mapper,context=/,class=org.apache.catalina.mapper.Mapper");
//        Set<ObjectName> mappers = mBeanServer.queryNames(objectName, null);
//
//        System.out.println("Mapped Endpoint: ");
//        for (ObjectName mapper : mappers) {
//            String[] patterns = (String[]) mBeanServer.getAttribute(mapper, "mappings");
//            for (String pattern : patterns) {
//                System.out.println("Mapped Endpoint: " + pattern);
//            }
//        }
//    }
//}
