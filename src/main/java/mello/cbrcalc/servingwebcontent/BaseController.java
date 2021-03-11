package mello.cbrcalc.servingwebcontent;

import lombok.extern.slf4j.Slf4j;
import mello.cbrcalc.downloader.ValCodeDownloader;
import mello.cbrcalc.downloader.ValRateDownloader;
import mello.cbrcalc.service.ServiceDAO;
import mello.cbrcalc.xml.ValCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class BaseController {
    @Autowired
    ServiceDAO service;
    @Autowired
    ValCodeDownloader valCodeDownloader;
    @Autowired
    ValRateDownloader valRateDownloader;

    @GetMapping("/")
    public String indexPage(Model model) {
        return "index";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("test_attr", service.findValutaById("R01100").toString());
        return "greeting";
    }

    @GetMapping("/val_code")
    public String valCodePage(Model model) {
        List<ValCode> valCodeList = service.getValutaCodes();
        model.addAttribute("valCodeList", valCodeList);
        return "val_code";
    }

    @GetMapping("/exchange")
    public String exchangePage(Model model) {
        List<ValCode> valCodeList = service.getValutaCodes();
        valCodeList = valCodeList.stream()
                .filter(v -> !v.getIsoCharCode().isEmpty())
                .sorted(Comparator.comparing(ValCode::getIsoCharCode))
                .collect(Collectors.toList());
        model.addAttribute("valCodes", valCodeList);

        List<String> codes = valCodeList.stream().map(ValCode::getIsoCharCode).filter(s -> !s.isEmpty()).sorted().collect(Collectors.toList());
        model.addAttribute("codes", codes);

        return "exchange";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        return "update_db";
    }

    @PostMapping("/updateCurrencyDataBase")
    public String updateCurrencyDataBaseForm() {

        try {
            valCodeDownloader.updateCodesTable();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        return "update_success";
    }

    @PostMapping("/updateRateDataBase")
    public String updateRateDataBaseForm() {

        try {
            valRateDownloader.updateCodesTable();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        return "update_success";
    }

}