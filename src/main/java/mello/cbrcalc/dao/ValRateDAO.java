package mello.cbrcalc.dao;

import mello.cbrcalc.utils.HibernateSessionFactoryUtil;
import mello.cbrcalc.xml.ValRate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
public class ValRateDAO {

    public void saveOrUpdate(ValRate valRate) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(valRate);
        transaction.commit();
        session.close();
    }

    public void update(ValRate valRate) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(valRate);
        transaction.commit();
        session.close();
    }

    public void delete(ValRate valRate) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(valRate);
        transaction.commit();
        session.close();
    }
}
