package mello.cbrcalc.dao;

import mello.cbrcalc.xml.ValRate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


public interface ValRateDAO {
    void saveOrUpdate(ValRate v);
}
