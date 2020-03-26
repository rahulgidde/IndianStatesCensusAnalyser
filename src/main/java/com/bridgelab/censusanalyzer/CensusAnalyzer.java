package com.bridgelab.censusanalyzer;

import com.bridgelab.exception.CsvBuilderException;
import com.bridgelab.exception.StateAnalyzerException;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;


public class CensusAnalyzer {

    List<CSVStatesCensus> csvFileList = null;
    List<CSVStatesCode> csvCodeFileList = null;

    //METHOD TO LOAD THE CSV FILE AND GET RECORDS
    public int loadIndianCensusData(String csvFilePath) throws StateAnalyzerException {
        String extension = getFileExtension(new File(csvFilePath));
        if (extension.compareTo("csv") != 0) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE,
                    "Invalid file extension");
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            csvFileList = csvBuilder.getCSVFileList(reader, CSVStatesCensus.class);
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

    //TO METHOD LOAD THE CSV FILE AND GET RECORDS
    public int loadIndianStateCodeData(String csvFilePath) throws StateAnalyzerException {
        String extension = getFileExtension(new File(csvFilePath));
        if (extension.compareTo("csv") != 0) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE,
                    "Invalid file extension");
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            csvCodeFileList = csvBuilder.getCSVFileList(reader, CSVStatesCode.class);
            return csvCodeFileList.size();
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

    //METHOD FOR STATE CENSUS COMPARATOR
    public String getSortedCensusData() throws StateAnalyzerException {
        if (csvFileList == null || csvFileList.size() == 0)
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_CENSUS_DATA, "Data not found");
        Comparator<CSVStatesCensus> comparator = Comparator.comparing(stateCensus -> stateCensus.getState());
        this.sort(comparator, csvFileList);
        String toJson = new Gson().toJson(csvFileList);
        return toJson;
    }

    //METHOD FOR STATE CODE COMPARATOR
    public String getSortedCodeData() throws StateAnalyzerException {
        if (csvCodeFileList == null || csvCodeFileList.size() == 0)
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_CENSUS_DATA, "Data not found");
        Comparator<CSVStatesCode> comparator = Comparator.comparing(CSVStatesCode -> CSVStatesCode.getState());
        this.sort(comparator, csvCodeFileList);
        String toJson = new Gson().toJson(csvCodeFileList);
        return toJson;
    }

    //METHOD FOR SORTING THE STATE CENSUS CSV
    private <T> void sort(Comparator<T> csvComparator, List<T> csvList) {
        for (int index1 = 0; index1 < csvList.size() - 1; index1++) {
            for (int index2 = 0; index2 < csvList.size() - index1 - 1; index2++) {
                T census1 = csvList.get(index1);
                T census2 = csvList.get(index2 + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    csvList.set(index2, census2);
                    csvList.set(index2 + 1, census1);
                }
            }
        }
    }
}