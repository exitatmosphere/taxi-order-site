package com.example.taxi.controller;

import com.example.taxi.domain.Message;
import com.example.taxi.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class MessageController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/messages")
    public String messageList(Map<String, Object> model){
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "messageList";
    }

    @PostMapping("delete")
    public String delete(@RequestParam int id){
        if (messageRepo.findById(id) != null) messageRepo.deleteById(id);
        return "redirect:/messages";
    }
}
