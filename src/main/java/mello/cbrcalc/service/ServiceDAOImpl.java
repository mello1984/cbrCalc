package mello.cbrcalc.service;


import mello.cbrcalc.dao.ValCodeDAO;
import mello.cbrcalc.dao.ValRateDAO;
import mello.cbrcalc.xml.ValCode;
import mello.cbrcalc.xml.ValRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceDAOImpl implements ServiceDAO {
    @Autowired
    private ValCodeDAO valCodeDAO;

    @Autowired
    private ValRateDAO valRateDAO;

    @Override
    public ValCode findValutaById(String id) {
        return valCodeDAO.findValutaById(id);
    }

    @Override
    public List<ValCode> getValutaCodes() {
        return valCodeDAO.getValutaCodes();
    }

    @Override
    public void saveOrUpdateValCode(ValCode v) {
        valCodeDAO.saveOrUpdate(v);
    }

    @Override
    public void saveOrUpdateValRate(ValRate v) {
        valRateDAO.saveOrUpdate(v);
    }

}
