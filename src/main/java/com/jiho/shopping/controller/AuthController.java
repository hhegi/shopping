package com.jiho.shopping.controller;

import com.jiho.shopping.entity.User;
import com.jiho.shopping.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signin")
    public String signinForm(@RequestParam(value="error", required = false)String error,
                             @RequestParam(value="exception", required = false)String exception,
                             Model model){
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        return "signin";
    }

    @GetMapping("/signup")
    public String signupForm(){
        return "/signup";
    }

    @PostMapping("/signup")
    public String signUp(User user){
        User newUser = user;
        User userEntity = authService.signup(user);
        System.out.println(userEntity);

        return "signin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) throws Exception{
        authService.logout(session);
        return "redirect:/signin";
    }
}
