package com.bridgelab;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyzer {

    //METHOD TO LOAD THE CSV FILE AND GET RECORDS
    public int getStateCensusRecords(String CSV_FILE_PATH) throws StateAnalyzerException {
        int numberOfRecords = 0;
        String extension = getFileExtension(new File(CSV_FILE_PATH));
        if (extension.compareTo("csv") != 0) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE,
                    "Invalid file extension");
        }
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
        } catch (NoSuchFileException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, "File Not Found");
        } catch (RuntimeException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER,
                    "Wrong Delimiter Or Header");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfRecords;
    }

    public int getStateCodeRecords(String CSV_FILE_PATH) throws StateAnalyzerException {

        //METHOD TO LOAD THE CSV FILE AND GET RECORDS
        int numberOfRecords = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
            CsvToBean<CSVStatesCode> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStatesCode.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<CSVStatesCode> csvStatesCodeIterator = csvToBean.iterator();
            while (csvStatesCodeIterator.hasNext()) {
                CSVStatesCode csvStatesCode = csvStatesCodeIterator.next();
                System.out.println("SR.NO: " + csvStatesCode.getSrNo());
                System.out.println("State: " + csvStatesCode.getState());
                System.out.println("Name: " + csvStatesCode.getName());
                System.out.println("TIN: " + csvStatesCode.getTIN());
                System.out.println("StateCode: " + csvStatesCode.getStateCode());
                System.out.println("-----------------------------");
                numberOfRecords++;
            }
        } catch (NoSuchFileException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, "File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numberOfRecords;
    }

    //METHOD TO GET FILE EXTENSION
    public String getFileExtension(File file) {
        String fileExtension = "";
        // GET FILE NAME FIRST
        String fileName = file.getName();
        fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileExtension;
    }

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("---------------- Welcome To Indian_States_Census_Analyser_Problem---------------");
    }
}
