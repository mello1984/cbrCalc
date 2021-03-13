package mello.cbrcalc.service;


import mello.cbrcalc.dao.ValCodeRepository;
import mello.cbrcalc.dao.ValRateRepository;
import mello.cbrcalc.entity.ValCode;
import mello.cbrcalc.entity.ValRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceDAOImpl implements ServiceDAO {
    @Autowired
    private ValCodeRepository valCodeRepository;

    @Autowired
    private ValRateRepository valRateRepository;

    @Override
    public ValCode findValutaById(String id) {
        return valCodeRepository.findById(id).get();
    }

    @Override
    public List<ValCode> getValutaCodes() {
        return valCodeRepository.findAll();
    }

    @Override
    public void saveOrUpdateValCode(ValCode v) {
        valCodeRepository.save(v);
    }

    @Override
    public void saveOrUpdateValRate(ValRate v) {
        valRateRepository.save(v);
    }

    @Override
    public ValRate findValRateById(String id) {
        return valRateRepository.findValRateByValutaIdAndAndDate(id, LocalDate.now());
    }

}
