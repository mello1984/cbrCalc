package mello.cbrcalc.dao;

import mello.cbrcalc.xml.ValRate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


public interface ValRateDAO {
    void saveOrUpdate(ValRate v);

    ValRate findValRateById(String id);

    ValRate findValRateByIdOnDate(String id, LocalDate ld);
}
