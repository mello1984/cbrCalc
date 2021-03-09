package mello.cbrcalc.dao;

import mello.cbrcalc.xml.ValCode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValCodeDAO extends DAO {
    @Autowired
    SessionFactory sessionFactory;

    public ValCode findValutaById(String id) {
        return sessionFactory.openSession().get(ValCode.class, id);
    }

    public List<ValCode> getValutaCodes() {
        return (List<ValCode>) sessionFactory.openSession().createQuery("from ValCode").list();
    }

    public void saveOrUpdate(ValCode v) {
        super.saveOrUpdate(v);
    }

    public void update(ValCode v) {
        super.update(v);
    }

    public void delete(ValCode v) {
        super.delete(v);
    }

}
