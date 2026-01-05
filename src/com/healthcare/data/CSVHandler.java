package com.healthcare.data;

import java.io.*;
import java.util.*;

public class CSVHandler {
    
    public static List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                // simple split by comma, assuming no commas in data for now
                // if commas exist, we'd need a more robust parser
                data.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void writeCSV(String filePath, String[] header, List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(String.join(",", header));
            bw.newLine();
            for (String[] row : data) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void appendCSV(String filePath, String[] row) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(String.join(",", row));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
