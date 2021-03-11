package mello.cbrcalc.dao;

import mello.cbrcalc.xml.ValRate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ValRateDAOImpl implements ValRateDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveOrUpdate(ValRate v) {
        sessionFactory.getCurrentSession().saveOrUpdate(v);
    }
}
