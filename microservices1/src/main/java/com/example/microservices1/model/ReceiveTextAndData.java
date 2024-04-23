package com.example.microservices1.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "table_textAndData_microservice1")
public class ReceiveTextAndData
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "received_text_from_microservice2")
    private String text;
    @Column(name = "received_data_from_microservice2")
    private LocalDate data;

    public ReceiveTextAndData(Long id, String text, LocalDate data)
    {
        this.id = id;
        this.text = text;
        this.data = data;
    }
    public ReceiveTextAndData(String text, LocalDate data)
    {
        this.text = text;
        this.data = data;
    }
    public ReceiveTextAndData()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}

