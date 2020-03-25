package com.bridgelab;

import Exception.CsvBuilderException;
import Exception.StateAnalyzerException;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyzer {

    //METHOD TO LOAD THE CSV FILE AND GET RECORDS
    public int getStateCensusRecords(String CSV_FILE_PATH) throws StateAnalyzerException {
        String extension = getFileExtension(new File(CSV_FILE_PATH));
        if (extension.compareTo("csv") != 0) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE,
                    "Invalid file extension");
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            List<CSVStatesCensus> csvFileList = csvBuilder.getCSVFileList(reader, CSVStatesCensus.class);
            return csvFileList.size();
        } catch (NoSuchFileException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, "File Not Found");
        } catch (RuntimeException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER,
                    "Wrong Delimiter Or Header");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getStateCodeRecords(String CSV_FILE_PATH) throws StateAnalyzerException {

        //METHOD TO LOAD THE CSV FILE AND GET RECORDS
        String extension = getFileExtension(new File(CSV_FILE_PATH));
        if (extension.compareTo("csv") != 0) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE,
                    "Invalid file extension");
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            List<CSVStatesCode> csvFileList = csvBuilder.getCSVFileList(reader, CSVStatesCode.class);
            return csvFileList.size();
        } catch (NoSuchFileException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, "File Not Found");
        } catch (RuntimeException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER,
                    "Wrong Delimiter Or Header");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //METHOD TO GET COUNT OF RECORDS
    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> iterable = () -> iterator;
        int recordCount = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return recordCount;
    }

    //METHOD TO GET FILE EXTENSION
    public String getFileExtension(File file) {
        String fileExtension = "";
        // GET FILE NAME FIRST
        String fileName = file.getName();
        fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileExtension;
    }
}