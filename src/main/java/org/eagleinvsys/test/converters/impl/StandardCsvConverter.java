package org.eagleinvsys.test.converters.impl;

import com.opencsv.CSVWriter;
import org.eagleinvsys.test.converters.StandardConverter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StandardCsvConverter implements StandardConverter {

    private final CsvConverter csvConverter;

    public StandardCsvConverter(CsvConverter csvConverter) {
        this.csvConverter = csvConverter;
    }

    /**
     * Converts given {@link List<Map>} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format. All maps must have the same set of keys
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(List<Map<String, String>> collectionToConvert, OutputStream outputStream) throws IOException {
        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(outputStream))) {
              Set<String> headers =  collectionToConvert.get(0).keySet();  //берем headers первой мапы
              String[] h = headers.toArray(new String[headers.size()]);
              writer.writeNext(h);
               for (Map<String, String> map : collectionToConvert) {
                   List<String> rowList = new ArrayList<String>();
                    for (String key : h){
                        rowList.add(map.get(key)) ;
                    }
                    String [] row = rowList.toArray(new String[rowList.size()]);
                    writer.writeNext(row);
               }
        }
    }
}


