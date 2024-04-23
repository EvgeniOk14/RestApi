package com.example.microservices1.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "table_microservis1")
public class ReceivedData
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data")
    private  String data;

    public ReceivedData(Long id, String data)
    {
        this.id = id;
        this.data = data;
    }
    public ReceivedData(String data)
    {
        this.data = data;
    }
    public ReceivedData()
    {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }
}
