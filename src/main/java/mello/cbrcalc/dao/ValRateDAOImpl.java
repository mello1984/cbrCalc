package mello.cbrcalc.dao;

import mello.cbrcalc.xml.ValRate;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class ValRateDAOImpl implements ValRateDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveOrUpdate(ValRate v) {
        sessionFactory.getCurrentSession().saveOrUpdate(v);
    }

    @Override
    public ValRate findValRateById(String id) {
        return findValRateByIdOnDate(id, LocalDate.now());
    }

    @Override
    public ValRate findValRateByIdOnDate(String valutaId, LocalDate ld) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from ValRate where id=:id and date=:date");
        query.setParameter("id", valutaId);
        query.setParameter("date", ld);

        return (ValRate) query.list().get(0);
    }
}
