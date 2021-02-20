package com.example.restapi.utils;

import com.example.restapi.model.KafkaMessage;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class KafkaStats {

    private int nOfTotalMessagesReceived;
    private final HashMap<String, Integer> nOfMessagesByTypeMap;
    private final List<KafkaMessage> kafkaMessageList;
    private String bonus = "";

    public KafkaStats() {
        this.nOfTotalMessagesReceived = 0;
        this.nOfMessagesByTypeMap = new HashMap<String, Integer>();
        this.kafkaMessageList = new ArrayList<KafkaMessage>();
        this.bonus = "";
    }

    public int getNumberOfTotalMessagesReceived() {
        return nOfTotalMessagesReceived;
    }

    public HashMap<String, Integer> getNumberOfMessagesByTypeMap() {
        return nOfMessagesByTypeMap;
    }

    public String getBonus(){
        return new String(DatatypeConverter.parseBase64Binary(bonus));
    }

    private void bonusHandler(String mMessage, String mSecret){
        if (mMessage.contains("ConcatMe") && (!mMessage.equals("ConcatMe4"))){
            if (!bonus.contains(mSecret)){
                bonus += mSecret;
            }
        }
    }

    public void messageReceived(JSONObject jsonMessage){
        KafkaMessage kafkaMessage;
        nOfTotalMessagesReceived++;
        String mMessage = "";
        String mTypeId = "";
        String mSecret = "";
        if (jsonMessage.has("message")){
            mMessage = jsonMessage.getString("message");
        }
        if (jsonMessage.has("typeId")) {
            mTypeId = jsonMessage.getString("typeId");
        }
        if (jsonMessage.has("secret")) {
            mSecret = jsonMessage.getString("secret");
        }
        // mabye no  need for check

        if (nOfMessagesByTypeMap.containsKey(mTypeId)){
            int countOfTypeId = nOfMessagesByTypeMap.get(mTypeId);
            countOfTypeId++;
            nOfMessagesByTypeMap.put(mTypeId, countOfTypeId);
            //put inplace
        }
        else{
            nOfMessagesByTypeMap.put(mTypeId, 1);
        }
        kafkaMessage = new KafkaMessage(mMessage, mTypeId, mSecret);
        kafkaMessageList.add(kafkaMessage);

        bonusHandler(mMessage, mSecret);
    }
}