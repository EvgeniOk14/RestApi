package com.example.microservice2.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class MicroServiceService
{
    private RestTemplate restTemplate;

    public MicroServiceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void connectionService(String text, LocalDate data, Model model)
    {
        System.out.println(text);
        System.out.println(data);
        System.out.println();
        String url = "http://localhost:8088/longTextAndOneData"; // Создаем объект-запрос для микросервиса 1
        String requestBody = "text" + " " + text + " " + "data" + " " + data.toString();  // Формируем тело запроса с передаваемыми данными
        //String requestBody = "text: "  + text +  "data: " + data.toString();  // Формируем тело запроса с передаваемыми данными
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestBody, String.class); // Получаем ответ от микросервиса 1
        String responseBody = response.getBody();
        model.addAttribute("message", responseBody);
    }
}
