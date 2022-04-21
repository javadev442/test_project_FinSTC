package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleMessage;
import java.util.*;

public class MyMessage implements ConvertibleMessage {

    private Map<String,String> myMessage = new HashMap<>();

    public MyMessage(){}

    public MyMessage(Map<String, String> myMessage) {
        this.myMessage = myMessage;
    }

    public Collection<String> getHeaders() {
        return myMessage.keySet();
    }

    @Override
    public String getElement(String elementId) {
        return this.myMessage.get(elementId);
    }
}
