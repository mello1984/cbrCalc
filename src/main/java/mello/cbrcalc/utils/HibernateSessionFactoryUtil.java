//package mello.cbrcalc.utils;
//
//import mello.cbrcalc.xml.ValRate;
//import mello.cbrcalc.xml.ValCode;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//
//public class HibernateSessionFactoryUtil {
//    private static SessionFactory sessionFactory;
//
//    private HibernateSessionFactoryUtil() {
//    }
//
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//                Configuration configuration = new Configuration().configure();
//                configuration.addAnnotatedClass(ValCode.class);
//                configuration.addAnnotatedClass(ValRate.class);
//                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//                sessionFactory = configuration.buildSessionFactory(builder.build());
//
//            } catch (Exception e) {
//                System.out.println("Исключение!" + e);
//            }
//        }
//        return sessionFactory;
//    }
//}