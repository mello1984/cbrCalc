package mello.cbrcalc.dao;

import mello.cbrcalc.utils.HibernateSessionFactoryUtil;
import mello.cbrcalc.xml.ValCode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValCodeDAO extends DAO {

    public ValCode findValutaById(String id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(ValCode.class, id);
    }

    public List<ValCode> getValutaCodes() {
        return (List<ValCode>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from ValCode").list();
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
