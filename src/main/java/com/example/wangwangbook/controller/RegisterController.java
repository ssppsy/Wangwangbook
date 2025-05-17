package com.example.wangwangbook.controller;

import com.example.wangwangbook.entity.User;
import com.example.wangwangbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirm,
            @RequestParam String gender,
            @RequestParam String birthdate,
            @RequestParam String genre,
            Model model
    ) {
        if (!password.equals(confirm)) {
            model.addAttribute("error", "Passwords do not match!");
            return "register";
        }

        if (userRepository.existsByEmail(email)) {
            model.addAttribute("error", "Email already registered.");
            return "register";
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // 🔒 可后续加密
        user.setGender(gender);
        user.setBirthdate(LocalDate.parse(birthdate));
        user.setGenre(genre);

        userRepository.save(user);
        return "/login";
    }


}

