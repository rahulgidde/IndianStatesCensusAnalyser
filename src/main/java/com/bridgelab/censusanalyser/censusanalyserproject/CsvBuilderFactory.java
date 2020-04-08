package com.bridgelab.censusanalyser.censusanalyserproject;

public class CsvBuilderFactory {
    public static IcsvBuilder createCsvBuilder() {
        return new CsvBuilder();
    }
}
