package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.Converter;
import org.eagleinvsys.test.converters.ConvertibleCollection;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.opencsv.CSVWriter;
import org.eagleinvsys.test.converters.ConvertibleMessage;

public class CsvConverter implements Converter {

    /**
     * Converts given {@link ConvertibleCollection} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(ConvertibleCollection collectionToConvert, OutputStream outputStream) throws IOException {
        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(outputStream))) {

            Collection<String> headers =  collectionToConvert.getHeaders();
            String[] h = headers.toArray(new String[headers.size()]);
            writer.writeNext(h);
            Iterable<ConvertibleMessage> messages = collectionToConvert.getRecords();
            for (ConvertibleMessage message : messages) {
                List<String> rowList = new ArrayList<>();
                for (String header : headers) {
                    rowList.add(message.getElement(header));
                }
                String [] row = rowList.toArray(new String[rowList.size()]);
                writer.writeNext(row);
            }
        }

    }

}