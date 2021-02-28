package mello.cbrcalc.dao;

import mello.cbrcalc.xml.ValRate;
import org.springframework.stereotype.Component;

@Component
public class ValRateDAO extends DAO {

    public void saveOrUpdate(ValRate v) {
        super.saveOrUpdate(v);
    }

    public void update(ValRate v) {
        super.update(v);
    }

    public void delete(ValRate v) {
        super.delete(v);
    }
}
