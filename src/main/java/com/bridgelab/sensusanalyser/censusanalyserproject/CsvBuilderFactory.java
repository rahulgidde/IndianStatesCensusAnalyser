package com.bridgelab.sensusanalyser.censusanalyserproject;

public class CsvBuilderFactory {
    public static IcsvBuilder createCsvBuilder() {
        return new CsvBuilder();
    }
}
