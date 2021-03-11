package mello.cbrcalc.dao;

import mello.cbrcalc.xml.ValCode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ValCodeDAOImpl implements ValCodeDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public ValCode findValutaById(String id) {
        return sessionFactory.getCurrentSession().get(ValCode.class, id);
    }

    @Override
    public List<ValCode> getValutaCodes() {
        return sessionFactory.getCurrentSession()
                .createQuery("from ValCode", ValCode.class).getResultList();
    }

    @Override
    public void saveOrUpdate(ValCode v) {
        sessionFactory.getCurrentSession().saveOrUpdate(v);
    }


}
