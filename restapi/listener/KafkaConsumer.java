package com.example.restapi.listener;

import com.example.restapi.model.KafkaMessage;
import com.example.restapi.utils.KafkaStats;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class KafkaConsumer {


    @Autowired
    KafkaStats kafkaStats;
    @KafkaListener(topics = "tdad.sw.interview", groupId = "7D8YBMAOT0")
    public void consumeJson(String message) {
        System.out.println("Consumed JSON Message: " + message);

        //print to log
        try {
            JSONObject jsonMessage = new JSONObject(message);
            kafkaStats.messageReceived(jsonMessage);

        }catch (JSONException err){
            System.out.println("Not a json string, wont convert to json");
        }
    }
}