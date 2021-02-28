package mello.cbrcalc.services;


import mello.cbrcalc.dao.ValCodeDAO;
import mello.cbrcalc.xml.ValCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValCodeService {
    @Autowired
    private ValCodeDAO dao;

    public ValCode findValutaById(String id) {
        return dao.findValutaById(id);
    }

    public List<ValCode> getValutaCodes() {
        return dao.getValutaCodes();
    }

    public void saveOrUpdate(ValCode valuta) {
        dao.saveOrUpdate(valuta);
    }

    public void update(ValCode valuta) {
        dao.update(valuta);
    }

    public void delete(ValCode valuta) {
        dao.delete(valuta);
    }

}
