package javacsv;

import java.io.*;

public class JavaCSV {

    private int[][] Data;
    private String[] stringData;
    private String fileName = "H:/test.csv";

    public JavaCSV(int[][] data, String fileName) {
        this.Data = data;
        this.fileName = fileName;
    }

    public JavaCSV(int[][] data) {
        this.Data = data;
    }

    public void save() throws IOException {
        stringData = new String[Data.length];
        for (int i = 0; i < Data.length; i++) {
            stringData[i] = arrayToString(Data[i]);
        }
        
        FileWriter document = new FileWriter(fileName);
        BufferedWriter bufDocument = new BufferedWriter(document);
        
        for (String line : stringData) {
            bufDocument.write(line);
        }
        bufDocument.close();
    }
    
    public String arrayToString(int[] data) {
        StringBuilder builder = new StringBuilder();
        for (int value : data) {
            builder.append(String.format("\"%s\",", value));
        }
        builder.append(System.lineSeparator());
        return builder.toString();
    }
}
