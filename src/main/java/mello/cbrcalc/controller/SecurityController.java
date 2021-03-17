package mello.cbrcalc.controller;

import mello.cbrcalc.entity.User;
import mello.cbrcalc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SecurityController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) return "registration";
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("password_error", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(user)) {
            model.addAttribute("username_error", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/";
    }

}
