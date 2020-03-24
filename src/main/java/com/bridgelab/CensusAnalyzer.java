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
import java.util.stream.StreamSupport;

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
            Iterator<CSVStatesCensus> censusAnalyserIterator = this.getCSVIterator(reader, CSVStatesCensus.class);
            return this.getCount(censusAnalyserIterator);
        } catch (NoSuchFileException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, "File Not Found");
        } catch (RuntimeException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER,
                    "Wrong Delimiter Or Header");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numberOfRecords;
    }

    public int getStateCodeRecords(String CSV_FILE_PATH) throws StateAnalyzerException {

        //METHOD TO LOAD THE CSV FILE AND GET RECORDS
        int numberOfRecords = 0;
        String extension = getFileExtension(new File(CSV_FILE_PATH));
        if (extension.compareTo("csv") != 0) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE,
                    "Invalid file extension");
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
            Iterator<CSVStatesCode> censusAnalyserIterator = this.getCSVIterator(reader, CSVStatesCode.class);
            return this.getCount(censusAnalyserIterator);
        } catch (NoSuchFileException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, "File Not Found");
        } catch (RuntimeException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER,
                    "Wrong Delimiter Or Header");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numberOfRecords;
    }

    //METHOD TO GET NUMBER OF RECORD COUNT
    private int getCount(Iterator iterator) {
        Iterable iterable = () -> iterator;
        int count = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return count;
    }

    //METHOD TO GET CSV ITERATOR
    private <E> Iterator<E> getCSVIterator(Reader reader, Class<E> csvClass) {
        CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
        csvToBeanBuilder.withType(csvClass);
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
        CsvToBean<E> csvToBean = csvToBeanBuilder.build();
        return csvToBean.iterator();
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
