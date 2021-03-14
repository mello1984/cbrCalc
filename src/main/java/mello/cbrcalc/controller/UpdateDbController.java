package mello.cbrcalc.controller;

import mello.cbrcalc.service.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UpdateDbController {
    @Autowired
    private ServiceDAO service;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/updateCurrencyDataBase")
    public String updateRateCodeDB() {

        boolean result = service.updateCodeDB();
        return result ? "Update successful" : "Update failed";
    }

    @PostMapping("/updateCurrencyDailyDataBase")
    public String updateDailyRateCodeDB() {

        boolean result = service.updateCodeDailyDB();
        return result ? "Update successful" : "Update failed";
    }

    @PostMapping("/updateRateDataBase")
    public String updateRateDB() {
        boolean result = service.updateRateDB();
        return result ? "Update successful" : "Update failed";
    }

}
