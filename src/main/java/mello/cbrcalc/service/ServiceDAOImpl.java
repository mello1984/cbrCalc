package mello.cbrcalc.service;


import mello.cbrcalc.dao.ValCodeRepository;
import mello.cbrcalc.dao.ValRateRepository;
import mello.cbrcalc.entity.ValCode;
import mello.cbrcalc.entity.ValCodeRoot;
import mello.cbrcalc.entity.ValRate;
import mello.cbrcalc.entity.ValRateRoot;
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
    private ValCodeRepository valCodeRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${cbr_calc.currency_catalog_url}")
    private String currency_catalog_url;

    @Value("${cbr_calc.daily_url}")
    private String daily_url;

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
        ValRate result = valRateRepository.findFirstByValutaIdOrderByDateDesc(id);
        System.out.println("findValRateById: " + result);
        if (result.getDate() != LocalDate.now()) {
            updateRateDB();
            System.out.println("SERVICE: try to update rates");
            result = valRateRepository.findFirstByValutaIdOrderByDateDesc(id);
        }
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
    public boolean updateCodeDB() {
        ResponseEntity<ValCodeRoot> response = restTemplate.exchange(
                currency_catalog_url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        if (response.hasBody()) {
            response.getBody().list.forEach(this::saveOrUpdateValCode);
        }
        return response.hasBody();
    }

}
