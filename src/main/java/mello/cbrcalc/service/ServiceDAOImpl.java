package mello.cbrcalc.service;


import mello.cbrcalc.dao.ValCodeDailyRepository;
import mello.cbrcalc.dao.ValRateRepository;
import mello.cbrcalc.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceDAOImpl implements ServiceDAO {
    @Autowired
    private ValCodeDailyRepository valCodeDailyRepository;
    @Autowired
    private ValRateRepository valRateRepository;
    @Autowired
    RestTemplate restTemplate;

    @Value("${cbr_calc.currency_catalog_url}")
    private String currency_catalog_url;
    @Value("${cbr_calc.currrency_daily_catalog_url}")
    private String currency_catalog_daily_url;
    @Value("${cbr_calc.daily_url}")
    private String daily_url;

    @Override
    public ValCodeDaily findValCodeDailyById(String id) {
        return valCodeDailyRepository.findById(id).get();
    }

    @Override
    public List<ValCodeDaily> getValCodeDailys() {
        return valCodeDailyRepository.findAll();
    }

    @Override
    public void saveOrUpdateValCodeDaily(ValCodeDaily v) {
        valCodeDailyRepository.save(v);
    }

    @Override
    public void saveOrUpdateValRate(ValRate v) {
        valRateRepository.save(v);
    }

    @Override
    public ValRate findValRateById(String id) {
//        ValRate result = valRateRepository.findFirstByValutaIdOrderByDateDesc(id);
        ValRate result = valRateRepository.findFirstByCharCodeOrderByDateDesc(id);
//        System.out.println("findValRateById: " + result);
//        if (result.getDate() != LocalDate.now()) {
//            updateRateDB();
//            System.out.println("SERVICE: try to update rates");
//            result = valRateRepository.findFirstByValutaIdOrderByDateDesc(id);
//            result = valRateRepository.findFirstByCharCodeOrderByDateDesc(id);
//        }
        return result;
    }

    @Override
    public boolean updateRateDB() {
        ResponseEntity<ValRateRoot> response = restTemplate.exchange(
                daily_url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        if (response.hasBody()) {
            ValRateRoot valRateRoot = response.getBody();
            valRateRoot.prepareItems();
            valRateRoot.valutes.forEach(this::saveOrUpdateValRate);
        }
        return response.hasBody();
    }

    @Override
    public boolean updateCodeDailyDB() {
        ResponseEntity<ValCodeDailyRoot> response = restTemplate.exchange(
                currency_catalog_daily_url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        if (response.hasBody()) {
            response.getBody().list.forEach(this::saveOrUpdateValCodeDaily);
        }
        return response.hasBody();
    }

    @Override
    public List<String> getCharCodeList(){
        return valRateRepository.getCharCodeList();
    }

    @Override
    public LocalDate getMaxDate() {
        return valRateRepository.getMaxDate();
    }
}
