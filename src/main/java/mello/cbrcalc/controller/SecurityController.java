package mello.cbrcalc.controller;

import mello.cbrcalc.aop.LogExecutionMethod;
import mello.cbrcalc.entity.User;
import mello.cbrcalc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @LogExecutionMethod
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
            model.addAttribute("password_error", "Passwords don't match");
            return "registration";
        }
        if (!userService.saveUser(user)) {
            model.addAttribute("username_error", "User with this username already exists");
            return "registration";
        }
        return "redirect:/";
    }

    @GetMapping("profile")
    public String updateUserProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userService.findUserByUserName(userName);
        model.addAttribute("userForm", user);
        System.out.println(user);
        return "user_profile";
    }

    @PostMapping("/profile")
    public String changeUser(@ModelAttribute("userForm") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) return "user_profile";
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("password_error", "Passwords don't match");
            return "user_profile";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User userFromDb = userService.findUserByUserName(userName);

        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userFromDb.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));

        System.out.println("changeUser end: " + user);
        userService.updateUser(userFromDb);
        model.addAttribute("update_result",true);
        return "user_profile";
    }

}
