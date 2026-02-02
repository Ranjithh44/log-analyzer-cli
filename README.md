# Log Analyzer CLI

A simple command-line tool written in Java that analyzes application
log files and prints a concise summary.

---

## What this tool does

• Counts total log entries  
• Breaks down entries by log level (ERROR, WARN, INFO)  
• Shows the most frequent error messages  

---

## Assumed log format

Each log line is expected to start with a log level:

ERROR Something bad happened  
WARN Something looks off  
INFO Application started  

Lines that do not match this format are ignored.

---

## How to run

Compile:
javac src/main/java/com/example/loganalyzer/Main.java

Run:
java com.example.loganalyzer.Main path/to/logfile.log

