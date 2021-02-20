package com.example.restapi.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class KafkaMessage {
    @NonNull
    private String message;
    @NonNull
    private String typeId;
    @NonNull
    private String secret;

}