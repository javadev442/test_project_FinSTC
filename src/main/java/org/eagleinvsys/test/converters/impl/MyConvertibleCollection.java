package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MyConvertibleCollection implements ConvertibleCollection {

    private List<MyMessage> records = new ArrayList<>();

    public MyConvertibleCollection(List<MyMessage> records) {
        this.records = records;
    }

    @Override
    public Collection<String> getHeaders() {
      try{return this.records.get(0).getHeaders();}
      catch (NullPointerException e) {
          return null;
      }
    }

    @Override
    public Iterable<ConvertibleMessage> getRecords() {
        return new ArrayList<>(records);
    }
}
