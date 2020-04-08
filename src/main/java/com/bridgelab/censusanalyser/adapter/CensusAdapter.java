package com.bridgelab.censusanalyser.adapter;

import com.bridgelab.censusanalyser.censusanalyserproject.CsvBuilderFactory;
import com.bridgelab.censusanalyser.censusanalyserproject.IcsvBuilder;
import com.bridgelab.censusanalyser.dao.CensusDAO;
import com.bridgelab.censusanalyser.dto.CSVStatesCensus;
import com.bridgelab.censusanalyser.dto.USCensusCSV;
import com.bridgelab.censusanalyser.exception.CsvBuilderException;
import com.bridgelab.censusanalyser.exception.StateAnalyzerException;
import com.bridgelab.censusanalyser.service.CensusAnalyser;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class CensusAdapter {
    Map<String, CensusDAO> censusMap = new HashMap<>();

    public abstract Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StateAnalyzerException;

    //METHOD TO LOAD THE CSV FILE AND GET RECORDS
    public <E> Map<String, CensusDAO> loadCensusData(Class<E> className, String... csvFilePath) throws StateAnalyzerException {
        String extension = CensusAnalyser.getFileExtension(new File(csvFilePath[0]));
        if (extension.compareTo("csv") != 0) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE,
                    "Invalid file extension");
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<E> stateCensusIterator = csvBuilder.getCSVFileIterator(reader, className);
            Iterable<E> csvIterable = () -> stateCensusIterator;
            if (className.getName().contains("CSVStatesCensus")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(CSVStatesCensus.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.getState(), new CensusDAO(censusCSV)));
                return censusMap;
            } else if (className.getName().contains("USCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(USCensusCSV.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.getState(), new CensusDAO(censusCSV)));
                return censusMap;
            } else {
                throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_COUNTRY, "Wrong country name");
            }
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
        return null;
    }
}