package mello.cbrcalc.servingwebcontent;

import mello.cbrcalc.dao.ValCodeDAO;
import mello.cbrcalc.xml.ValCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    ValCodeDAO valCodeDAO;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("test_attr", valCodeDAO.findValutaById("R01100").toString());
        return "greeting";
    }

    @GetMapping("/val_code")
    public String valCodePage(Model model) {
        List<ValCode> valCodeList = valCodeDAO.getValutaCodes();
        model.addAttribute("valCodeList", valCodeList);
        return "val_code";
    }


}