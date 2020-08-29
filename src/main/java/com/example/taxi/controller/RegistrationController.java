package com.example.taxi.controller;

import com.example.taxi.domain.Role;
import com.example.taxi.domain.User;
import com.example.taxi.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null){
            model.put("message", "User already exists");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String addAdmin(){
        User userFromDb = userRepo.findByUsername("admin");

        if (userFromDb != null){
            return "redirect:/login";
        }

        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepo.save(user);

        return "redirect:/login";
    }
}
