package mello.cbrcalc.servingwebcontent;

import mello.cbrcalc.dao.ValCodeDAO;
import mello.cbrcalc.services.ValCodeService;
import mello.cbrcalc.xml.ValCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BaseController {
    @Autowired
    ValCodeService valCodeService;

    @GetMapping("/")
    public String indexPage(Model model) {
        return "index";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("test_attr", valCodeService.findValutaById("R01100").toString());
        return "greeting";
    }

    @GetMapping("/val_code")
    public String valCodePage(Model model) {
        List<ValCode> valCodeList = valCodeService.getValutaCodes();
        model.addAttribute("valCodeList", valCodeList);
        return "val_code";
    }

    @GetMapping("/exchange")
    public String exchangePage(Model model) {
        List<ValCode> valCodeList = valCodeService.getValutaCodes();
        valCodeList = valCodeList.stream()
                .filter(v -> !v.getIsoCharCode().isEmpty())
                .sorted(Comparator.comparing(ValCode::getIsoCharCode))
                .collect(Collectors.toList());
        model.addAttribute("valCodes", valCodeList);

        List<String> codes = valCodeList.stream().map(ValCode::getIsoCharCode).filter(s -> !s.isEmpty()).sorted().collect(Collectors.toList());
        model.addAttribute("codes", codes);

        return "exchange";
    }

}