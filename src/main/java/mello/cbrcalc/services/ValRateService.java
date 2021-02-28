package mello.cbrcalc.services;

import mello.cbrcalc.dao.ValRateDAO;
import mello.cbrcalc.xml.ValRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValRateService {
    @Autowired
    private ValRateDAO dao;

    public void saveOrUpdate(ValRate valRate) {
        dao.saveOrUpdate(valRate);
    }

    public void update(ValRate valRate) {
        dao.update(valRate);
    }

    public void delete(ValRate valRate) {
        dao.delete(valRate);
    }
}
