package com.example.loganalyzer;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java Main <log-file>");
            return;
        }

        String filePath = args[0];
        LogAnalyzer analyzer = new LogAnalyzer();

        try {
            LogStats stats = analyzer.analyze(filePath);

            System.out.println("Total lines: " + stats.getTotalLines());

            for (Map.Entry<String, Integer> entry : stats.getLevelCounts().entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            System.out.println("\nTop errors:");

            stats.getErrorMessages().entrySet().stream()
                    .sorted((a, b) -> b.getValue() - a.getValue())
                    .limit(3)
                    .forEach(e ->
                            System.out.println(e.getValue() + "x " + e.getKey())
                    );

        } catch (IOException e) {
            System.err.println("Failed to read log file: " + e.getMessage());
        }
    }
}
