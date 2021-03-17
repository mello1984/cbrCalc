package mello.cbrcalc.controller;

import mello.cbrcalc.entity.ValCodeDaily;
import mello.cbrcalc.service.ServiceDAO;
import mello.cbrcalc.web.ExchangeTransaction;
import mello.cbrcalc.entity.ValRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BaseController {
    @Autowired
    ServiceDAO service;

    @GetMapping("/")
    public String indexPage(Model model) {
        return "index.html";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        return "update_db";
    }

    @GetMapping("/val_code")
    public String valCodePage(Model model) {
        List<ValCodeDaily> valCodeList = service.getValCodeDailys();
        model.addAttribute("valCodeList", valCodeList);
        return "val_code";
    }

    @GetMapping("/exchange")
    public String exchangePage(Model model) {
        List<String> charCodeList = service.getCharCodeList();
        model.addAttribute("charCodes", charCodeList);
        ExchangeTransaction exchangeTransaction = new ExchangeTransaction();
        model.addAttribute(exchangeTransaction);
        return "exchange";
    }

    @PostMapping("/exchangeCurrency")
    public String exchangeCurrency(@ModelAttribute("exchangeTransaction") @Valid ExchangeTransaction et, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<String> charCodeList = service.getCharCodeList();
            model.addAttribute("charCodes", charCodeList);
            return "exchange";
        }

        LocalDate maxDbDate = service.getMaxDate();
        if (maxDbDate.isBefore(LocalDate.now())) service.updateRateDB();

        ValRate valRateFrom = service.findValRateById(et.getCurrencyFrom());
        ValRate valRateTo = service.findValRateById(et.getCurrencyTo());
        double amountFrom = Double.parseDouble(et.getAmountFrom());
        double amountTo = amountFrom * valRateFrom.getVal() / valRateFrom.nominal / valRateTo.getVal() * valRateTo.nominal;
        et.setAmountTo(String.valueOf(amountTo));
        et.setValRateFrom(valRateFrom);
        et.setValRateTo(valRateTo);
        model.addAttribute("exchangeTransaction", et);

        return "exchange_success";
    }

}