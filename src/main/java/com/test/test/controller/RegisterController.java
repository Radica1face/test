package com.test.test.controller;

import com.test.test.entity.Role;
import com.test.test.entity.User;
import com.test.test.repository.RoleRepository;
import com.test.test.repository.UserRepository;
import com.test.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@AllArgsConstructor
public class RegisterController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registrationForm(User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(User user, Model model) {

        User userFromDb = userService.findByUsername(user.getUsername());

        if(userFromDb != null) {
            model.addAttribute("message", "Пользователь с таким именем уже сущетвует");
            return "registration";
        }

        userService.saveUser(user);
        return "redirect:/login";
    }
}
