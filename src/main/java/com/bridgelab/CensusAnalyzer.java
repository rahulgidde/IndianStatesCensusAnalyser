package com.bridgelab;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyzer {

    private static final String CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";

    ///METHOD TO LOAD THE CSV FILE AND GET RECORDS
    public int getRecords() {
        int numberOfRecords = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
            CsvToBean<CSVStatesCensus> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStatesCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<CSVStatesCensus> csvStateCensusIterator = csvToBean.iterator();
            while (csvStateCensusIterator.hasNext()) {
                CSVStatesCensus csvStateCensus = csvStateCensusIterator.next();
                System.out.println("State: " + csvStateCensus.getState());
                System.out.println("population: " + csvStateCensus.getPopulation());
                System.out.println("area: " + csvStateCensus.getAreaInSqKm());
                System.out.println("Density: " + csvStateCensus.getDensityPerSqKm());
                System.out.println("-----------------------------");
                numberOfRecords++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfRecords;
    }

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("---------------- Welcome To Indian_States_Census_Analyser_Problem---------------");
    }
}
