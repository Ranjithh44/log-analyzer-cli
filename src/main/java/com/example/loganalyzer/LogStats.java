package com.example.loganalyzer;

import java.util.HashMap;
import java.util.Map;

public class LogStats {

    private int totalLines;
    private final Map<String, Integer> levelCounts;
    private final Map<String, Integer> errorMessages;

    public LogStats() {
        this.totalLines = 0;
        this.levelCounts = new HashMap<>();
        this.errorMessages = new HashMap<>();
    }

    public void incrementTotalLines() {
        totalLines++;
    }

    public void incrementLevelCount(String level) {
        levelCounts.put(level, levelCounts.getOrDefault(level, 0) + 1);
    }

    public void recordErrorMessage(String message) {
        errorMessages.put(message, errorMessages.getOrDefault(message, 0) + 1);
    }

    public int getTotalLines() {
        return totalLines;
    }

    public Map<String, Integer> getLevelCounts() {
        return levelCounts;
    }

    public Map<String, Integer> getErrorMessages() {
        return errorMessages;
    }
}
