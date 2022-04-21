package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import org.junit.Test;

import java.io.*;

// static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class StandardCsvConverterTests {
    @Test
   public void test_StandardCsvConverter() throws IOException {

        Map<String, String> firstMap = new HashMap<>();
        firstMap.put("m1", "s1");
        firstMap.put("m2", "s2");
        Map<String, String> secondMap = new HashMap<>();
        secondMap.put("m1", "s3");
        secondMap.put("m2", "s4");
        List <Map<String,String>> listMap = new ArrayList<>();
        listMap.add(firstMap);
        listMap.add(secondMap);
        StandardConverter standardConverter = new StandardCsvConverter(new CsvConverter());
        OutputStream out = new ByteArrayOutputStream();
        standardConverter.convert(listMap, out);
        String result = out.toString();
        String expected =   "\"m1\",\"m2\"\n" +
                            "\"s1\",\"s2\"\n" +
                            "\"s3\",\"s4\"\n";
         assertEquals(expected, result);
    }

    @Test
    public void test2_StandardCsvConverter() throws IOException {

        Map<String, String> map = new HashMap<>();
        map.put("Заголовок 1", "Значение 1");
        map.put("Заголовок 2", "Значение 2");
        List <Map<String,String>> listMap = new ArrayList<>();
        listMap.add(map);
        StandardConverter standardConverter = new StandardCsvConverter(new CsvConverter());
        OutputStream out = new ByteArrayOutputStream();
        standardConverter.convert(listMap, out);
        String result = out.toString();
        String expected =   "\"Заголовок 1\",\"Заголовок 2\"\n" +
                            "\"Значение 1\",\"Значение 2\"\n";
        assertEquals(expected, result);
    }

    @Test
    public void test3_StandardCsvConverter() throws IOException {

        Map<String, String> map = new HashMap<>();
        map.put(" ", "\\");
        map.put("\"", ",");
        List <Map<String,String>> listMap = new ArrayList<>();
        listMap.add(map);
        StandardConverter standardConverter = new StandardCsvConverter(new CsvConverter());
        OutputStream out = new ByteArrayOutputStream();
        standardConverter.convert(listMap, out);
        String result = out.toString();
        String expected =   "\" \",\"\"\"\"\n" +
                            "\"\\\",\",\"\n";
        assertEquals(expected, result);
    }
}