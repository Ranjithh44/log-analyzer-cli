package com.example.loganalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogAnalyzer {

    public LogStats analyze(String filePath) throws IOException {
        LogStats stats = new LogStats();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                stats.incrementTotalLines();

                if (line.startsWith("ERROR")) {
                    stats.incrementLevelCount("ERROR");
                    stats.recordErrorMessage(line.substring(5).trim());
                } else if (line.startsWith("WARN")) {
                    stats.incrementLevelCount("WARN");
                } else if (line.startsWith("INFO")) {
                    stats.incrementLevelCount("INFO");
                }
            }
        }

        return stats;
    }
}
