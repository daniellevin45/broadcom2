package com.example.restapi.controller;

import com.example.restapi.utils.KafkaStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaContoller {

    @Autowired
    KafkaStats kafkaStats;

    @GetMapping("/v1/stats")
    public KafkaStats get(){
        return kafkaStats;
    }
}
