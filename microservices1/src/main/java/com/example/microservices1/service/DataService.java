package com.example.microservices1.service;

import com.example.microservices1.model.ReceiveTextAndData;
import com.example.microservices1.model.ReceivedData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;


import java.time.LocalDate;

@Service
@Transactional
public class DataService
{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RestTemplate restTemplate;



    public void saveData(String data)
    {
        ReceivedData receivedData = new ReceivedData(data);
        entityManager.persist(receivedData);
    }

    public  void saveTextAndData(String text, LocalDate data)
    {
        ReceiveTextAndData receiveTextAndData = new ReceiveTextAndData(text, data);
        entityManager.persist(receiveTextAndData);
    }


       public void sendSuccessMessageToMicroservice2(String message)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Указываем адрес микросервиса2
        String url = "http://localhost:8089/messageFromMicroservice1";


        // Создаем объект запроса
        HttpEntity<String> requestEntity = new HttpEntity<>(message, headers);

        // Отправляем запрос
        restTemplate.postForObject(url, requestEntity, String.class);
    }

}
