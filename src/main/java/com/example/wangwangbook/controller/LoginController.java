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
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    // 登录页面
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // ✅ 登录处理逻辑：校验、保存 session、跳转首页
    @PostMapping("/login")
    public String loginSubmit(@RequestParam String email,
                              @RequestParam String password,
                              HttpSession session,
                              Model model) {

        User user = userRepository.findByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "邮箱或密码错误");
            return "login";
        }

        // 登录成功，保存用户到 Session
        session.setAttribute("loggedInUser", user);

        return "redirect:/";
    }
}