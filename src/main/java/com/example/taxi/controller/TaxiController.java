package com.example.taxi.controller;

import com.example.taxi.domain.Message;
import com.example.taxi.domain.User;
import com.example.taxi.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TaxiController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/taxi")
    public String taxi(Map<String, Object> model) {
        return "taxi";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/taxi")
    public String add(@AuthenticationPrincipal User user, @RequestParam String start, @RequestParam String finish, @RequestParam String type, @RequestParam String phone, @RequestParam String extra, Map<String, Object> model){
        Message message = new Message(start, finish, type, phone, extra, user);
        messageRepo.save(message);
        return "taxi";
    }

    @GetMapping("/orders")
    public String orders(@AuthenticationPrincipal User user, Map<String, Object> model){
        Iterable<Message> messages = messageRepo.findByAuthor(user);
        model.put("messages", messages);
        return "orders";
    }
}
