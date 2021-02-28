package mello.cbrcalc.dao;

import mello.cbrcalc.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
public class DAO {
    void save(Object o) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(o);
        transaction.commit();
        session.close();
    }

    void update(Object o) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(o);
        transaction.commit();
        session.close();
    }

    void saveOrUpdate(Object o) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(o);
        transaction.commit();
        session.close();
    }

    void delete(Object o) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(o);
        transaction.commit();
        session.close();
    }
}
