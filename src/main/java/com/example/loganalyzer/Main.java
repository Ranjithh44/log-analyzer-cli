package com.example.loganalyzer;

import java.io.IOException;
import java.util.Map;

public class Main {

    private static final int DEFAULT_TOP = 3;

    public static void main(String[] args) {

        if (args.length < 1) {
            printUsage();
            return;
        }

        String filePath = args[0];
        int topN = DEFAULT_TOP;

        if (args.length == 3) {
            if (!"--top".equals(args[1])) {
                printUsage();
                return;
            }

            try {
                topN = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                printUsage();
                return;
            }
        } else if (args.length != 1) {
            printUsage();
            return;
        }

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
                    .limit(topN)
                    .forEach(e ->
                            System.out.println(e.getValue() + "x " + e.getKey())
                    );

        } catch (IOException e) {
            System.err.println("Failed to read log file: " + e.getMessage());
        }
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  java -jar log-analyzer-cli.jar <log-file>");
        System.out.println("  java -jar log-analyzer-cli.jar <log-file> --top N");
    }
}
