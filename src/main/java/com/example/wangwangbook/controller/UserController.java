package com.example.wangwangbook.controller;

import com.example.wangwangbook.entity.User;
import com.example.wangwangbook.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public String profilePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "profile";
    }

    // 新增处理保存修改的 POST 方法
    @PostMapping("/profile/update")
    public String updateGenre(@RequestParam("genre") String genre, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        user.setGenre(genre);
        userRepository.save(user);
        session.setAttribute("loggedInUser", user);

        return "redirect:/";
    }
}

