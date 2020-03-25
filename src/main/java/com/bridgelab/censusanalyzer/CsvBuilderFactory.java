package com.bridgelab.censusanalyzer;

public class CsvBuilderFactory {
    public static IcsvBuilder createCsvBuilder() {
        return new CsvBuilder();
    }
}
