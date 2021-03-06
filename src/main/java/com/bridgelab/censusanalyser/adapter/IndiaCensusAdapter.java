package com.bridgelab.censusanalyser.adapter;

import com.bridgelab.censusanalyser.censusanalyserproject.CsvBuilderFactory;
import com.bridgelab.censusanalyser.censusanalyserproject.IcsvBuilder;
import com.bridgelab.censusanalyser.dao.CensusDAO;
import com.bridgelab.censusanalyser.dto.CSVStatesCensus;
import com.bridgelab.censusanalyser.dto.CSVStatesCode;
import com.bridgelab.censusanalyser.exception.CsvBuilderException;
import com.bridgelab.censusanalyser.exception.StateAnalyzerException;
import com.bridgelab.censusanalyser.service.CensusAnalyser;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter {

    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StateAnalyzerException {
        Map<String, CensusDAO> censusMap = super.loadCensusData(CSVStatesCensus.class, csvFilePath[0]);
        if (csvFilePath.length == 1)
            return censusMap;
        return this.loadIndiaStateCode(censusMap, csvFilePath[1]);
    }

    //METHOD TO LOAD STATE CODE DATA
    private <E> Map<String, CensusDAO> loadIndiaStateCode(Map<String, CensusDAO> censusMap, String csvFilePath) throws StateAnalyzerException {
        String extension = CensusAnalyser.getFileExtension(new File(csvFilePath));
        if (extension.compareTo("csv") != 0) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE,
                    "Invalid file extension");
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<CSVStatesCode> stateCensusIterator = csvBuilder.getCSVFileIterator(reader, CSVStatesCode.class);
            Iterator<CSVStatesCode> finalStateCensusIterator = stateCensusIterator;
            Iterable<CSVStatesCode> csvIterable = () -> finalStateCensusIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> censusMap.get(csvState.getState()) != null)
                    .forEach(csvState -> censusMap.get(csvState.getState()).stateCode = csvState.getStateCode());
            return censusMap;
        } catch (RuntimeException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER,
                    "Incorrect delimiter or header.");
        } catch (NoSuchFileException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, "Incorrect file.");
        } catch (IOException e) {
            e.getStackTrace();
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        }
        return null;
    }
}
