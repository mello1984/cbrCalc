package mello.cbrcalc.dao;

import mello.cbrcalc.utils.HibernateSessionFactoryUtil;
import mello.cbrcalc.xml.ValCode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ValCodeDAO {

    public ValCode findValutaById(String id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(ValCode.class, id);
    }

    public List<ValCode> getValutaCodes() {
        return (List<ValCode>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from ValCode").list();
    }

    public void saveOrUpdate(ValCode valuta) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(valuta);
        transaction.commit();
        session.close();
    }

    public void update(ValCode valuta) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(valuta);
        transaction.commit();
        session.close();
    }

    public void delete(ValCode valuta) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(valuta);
        transaction.commit();
        session.close();
    }

}
