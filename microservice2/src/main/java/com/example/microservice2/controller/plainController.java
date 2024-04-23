package com.example.microservice2.controller;


import com.example.microservice2.service.MicroServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Controller
public class plainController
{
    private MicroServiceService microServiceService;
    private RestTemplate restTemplate;

    public plainController(RestTemplate restTemplate, MicroServiceService microServiceService)
    {
        this.restTemplate = restTemplate;
        this.microServiceService= microServiceService;
    }

    @GetMapping("/form")
    public String getForm()
    {
        return "receiveMessage";
    }


    @PostMapping("/receiveData")
    public String receiveFromForm(@RequestParam("text") String text, @RequestParam("data") LocalDate data, Model model)
    {
        microServiceService.connectionService(text, data, model);

//        System.out.println(text);
//        System.out.println(data);
//        System.out.println();
//        String url = "http://localhost:8088/longTextAndOneData"; // Создаем объект-запрос для микросервиса 1
//        String requestBody = "text" + " " + text + " " + "data" + " " + data.toString();  // Формируем тело запроса с передаваемыми данными
//        ResponseEntity<String> response = restTemplate.postForEntity(url, requestBody, String.class); // Получаем ответ от микросервиса 1
//        String responseBody = response.getBody();
//        model.addAttribute("message", responseBody);
        return "responseFromMicroservice1"; // Возвращаем ответ в качестве результата
    }

}
