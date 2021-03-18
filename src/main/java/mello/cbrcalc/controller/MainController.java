package mello.cbrcalc.controller;

import mello.cbrcalc.entity.LoggingEvent;
import mello.cbrcalc.entity.LoggingType;
import mello.cbrcalc.entity.ValCodeDaily;
import mello.cbrcalc.service.LoggingService;
import mello.cbrcalc.service.ServiceDAO;
import mello.cbrcalc.service.UserService;
import mello.cbrcalc.web.ExchangeTransaction;
import mello.cbrcalc.entity.ValRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class MainController {
    @Autowired
    ServiceDAO service;
    @Autowired
    UserService userService;
    @Autowired
    LoggingService loggingService;

    @GetMapping("/")
    public String indexPage(Model model) {
        return "index.html";
    }

    @GetMapping("/admin/admin")
    public String adminPage(Model model) {
        return "/admin/admin";
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

    @GetMapping("/history")
    public String userHistoryPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        List<LoggingEvent> list = loggingService.findAllByUsernameAndLoggingTypeOrderByLocalDateTimeDesc(userName, LoggingType.EXCHANGECURRENCY);
        model.addAttribute("transactions", list);
        return "history";
    }

    @GetMapping("/admin/all_history")
    public String allUserHistoryPage(Model model) {
        List<LoggingEvent> list = loggingService.findAllByLoggingTypeOrderByLocalDateTimeDesc(LoggingType.EXCHANGECURRENCY);
        model.addAttribute("transactions", list);
        return "/admin/all_history";
    }

}