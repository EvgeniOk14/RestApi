package com.example.microservices1.controller;

import com.example.microservices1.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@RestController
public class Microservice1Controller
{
    private RestTemplate restTemplate;
    private DataService dataService;

    public Microservice1Controller(RestTemplate restTemplate, DataService dataService)
    {
        this.restTemplate = restTemplate;
        this.dataService = dataService;
    }



    @GetMapping("/send")
    public String sendData()
    {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8089/receive", String.class);
        dataService.saveData(response);
        return "микросервис1 принял ответ от микросервиса2: " + response;
    }

//    @PostMapping("/oneTextAndOneData")
//    public String receiveDataFromMicroservice2(@RequestBody String requestBody)
//    {
//        // Распарсить requestBody для получения данных, например, текста и даты
//        // Например, если данные приходят в формате "текст: текст_данных дата: дата_данных"
//        String[] parts = requestBody.split(" ");
//        String text = parts[1]; // Получаем текст данных
//
//        String dateString = parts[3]; // Получаем строку с датой данных
//        LocalDate date = LocalDate.parse(dateString); // Преобразуем строку в объект LocalDate
//
//        // Далее вы можете делать что-то с полученными данными, например, сохранить их в базу данных
//        // Или передать их сервису обработки, или выполнить другие операции
//
//        // Пример сохранения данных в базу данных с использованием сервиса DataService
//        dataService.saveTextAndData(text, date);
//
//        // Ответить микросервису 2, что данные получены
//        return "Данные успешно получены и сохранены";
//    }

//    @PostMapping("/longTextAndOneData")
//    public String receiveFromForm(@RequestParam("text") String textAndDate)
//    {
//        // Разделить строку по ключу "дата"
//        String[] parts = textAndDate.split("дата:");
//
//        // Получить текст из первой части
//        String text = parts[0].trim();
//
//        // Получить дату из второй части
//        String dateString = parts[1].trim(); // Предполагаем, что дата всегда в конце
//        LocalDate date = LocalDate.parse(dateString);
//
//        dataService.saveTextAndData(text, date);
//
//        return ResponseEntity.ok().build().toString();
//
//    }

    @PostMapping("/longTextAndOneData")
    public String receiveFromForm(@RequestBody String requestBody)
    {
        System.out.println("Request Body: " + requestBody);

        // Разделить строку по ключу "дата"
        String[] parts = requestBody.split("\\s+data\\s+");
        //String[] parts = requestBody.split("(?<=data:)\\s+");


        // Проверка на наличие строки "дата:" в requestBody
        if (parts.length >= 2) {
            // Получить текст из первой части
            String text = parts[0].trim();

            // Получить дату из второй части
            String dateString = parts[1].trim(); // Предполагаем, что дата всегда в конце
            LocalDate date = LocalDate.parse(dateString);

            dataService.saveTextAndData(text, date);

            String message = "Данные успешно получены и сохранены! ";

            dataService.sendSuccessMessageToMicroservice2(message);

            return message;

        }
        else
        {
            String message = "Ошибка: не удалось извлечь данные из запроса! ";

            dataService.sendSuccessMessageToMicroservice2(message);

            return message;
        }
    }
}