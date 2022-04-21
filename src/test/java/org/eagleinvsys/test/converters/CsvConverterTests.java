package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.MyConvertibleCollection;
import org.eagleinvsys.test.converters.impl.MyMessage;
import org.junit.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class CsvConverterTests {

    @Test
    public void test_CsvConverter() throws IOException {

        Map<String, String> firstMap = new HashMap<>();
        firstMap.put("m1", "s1");
        firstMap.put("m2", "s2");
        Map<String, String> secondMap = new HashMap<>();
        secondMap.put("m1", "s3");
        secondMap.put("m2", "s4");
        MyMessage record = new MyMessage(firstMap);
        MyMessage record2 = new MyMessage(secondMap);
        List<MyMessage> records = new ArrayList<>();
        records.add(record);
        records.add(record2);
        ConvertibleCollection collectionToConvert = new MyConvertibleCollection(records);
        OutputStream out = new ByteArrayOutputStream();
        CsvConverter converter = new CsvConverter();
        converter.convert(collectionToConvert, out);
        String result = out.toString();
        String expected =   "\"m1\",\"m2\"\n" +
                            "\"s1\",\"s2\"\n" +
                            "\"s3\",\"s4\"\n";
        assertEquals(expected, result);
    }

    @Test
 public void test2_CsvConverter() throws IOException {

        Map<String, String> map = new HashMap<>();
        map.put("Заголовок 1", "Значение 1");
        map.put("Заголовок 2", "Значение 2");
        MyMessage record = new MyMessage(map);
        List<MyMessage> records = new ArrayList<>();
        records.add(record);
        ConvertibleCollection collectionToConvert = new MyConvertibleCollection(records);
        OutputStream out = new ByteArrayOutputStream();
        CsvConverter converter = new CsvConverter();
        converter.convert(collectionToConvert, out);
        String result = out.toString();
        String expected =   "\"Заголовок 1\",\"Заголовок 2\"\n" +
                            "\"Значение 1\",\"Значение 2\"\n";
        assertEquals(expected, result);
    }

    @Test
    public void test3_CsvConverter() throws IOException {

        Map<String, String> map = new HashMap<>();
        map.put(" ", "\\");
        map.put("\"", ",");
        MyMessage record = new MyMessage(map);
        List<MyMessage> records = new ArrayList<>();
        records.add(record);
        ConvertibleCollection collectionToConvert = new MyConvertibleCollection(records);
        OutputStream out = new ByteArrayOutputStream();
        CsvConverter converter = new CsvConverter();
        converter.convert(collectionToConvert, out);
        String result = out.toString();
        String expected =   "\" \",\"\"\"\"\n" +
                            "\"\\\",\",\"\n";
        assertEquals(expected, result);
    }
}