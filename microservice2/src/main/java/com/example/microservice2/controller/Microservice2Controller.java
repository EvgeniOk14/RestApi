package com.example.microservice2.controller;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@RestController
public class Microservice2Controller
{

    @GetMapping("/receive")
    public String receiveData()
    {
        return "Эти данные вернул (return) микросервис2";
    }

    @PostMapping("/messageFromMicroservice1")
    public String receiveMessageFromMicroservice1(@RequestBody String message, Model model)
    {
        System.out.println("Это message из микросервиса1 : " + message);
        model.addAttribute("message", message);
        return message;
    }
}
