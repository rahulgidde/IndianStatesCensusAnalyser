package com.bridgelab.sensusanalyser.adapter;

public class CsvBuilderFactory {
    public static IcsvBuilder createCsvBuilder() {
        return new CsvBuilder();
    }
}
